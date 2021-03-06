/*
 * EDACCView.java
 */
package edacc;

import edacc.model.DatabaseConnector;
import edacc.model.NoConnectionToDBException;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Observable;
import javax.xml.parsers.ParserConfigurationException;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import edacc.manageDB.Util;
import edacc.model.TaskRunnable;
import edacc.model.Tasks;
import edacc.updates.UpdateController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.help.HelpSet;
import javax.help.JHelp;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.xml.sax.SAXException;

/**
 * The application's main frame.
 */
public class EDACCView extends FrameView implements Observer {

    private EDACCExperimentMode experimentMode;
    private EDACCManageDBMode manageDBMode;
    private EDACCNoMode noMode;
    private Component mode;
    private javax.swing.GroupLayout mainPanelLayout;
    private String noModeIdentifier = "";
    private String manageDBModeIdentifier = "MANAGE DB MODE";
    private String experimentModeIdentifier = "MANAGE EXPERIMENT MODE";

    public EDACCView(SingleFrameApplication app) {
        super(app);
        initComponents();

        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        DatabaseConnector.getInstance().addObserver(this);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        this.getFrame().setIconImage(resourceMap.getImageIcon("edacc.icon").getImage());

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getSource() instanceof Tasks) {
                    Tasks task = (Tasks) evt.getSource();
                    if (Tasks.getTaskView() != null) {
                        return;
                    }
                }
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        experimentMode = new EDACCExperimentMode();
        manageDBMode = new EDACCManageDBMode();
        noMode = new EDACCNoMode();
        mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(noMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(noMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        mode = noMode;
        updateConnectionStateView();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                btnConnectToDB();
            }
        });
    }

    public void setStatusText(Component src, String text) {
        if (src != mode) {
            return;
        }
        String status;
        if (mode == this.manageDBMode) {
            status = manageDBModeIdentifier + " " + text;
        } else if (src == this.experimentMode) {
            status = experimentModeIdentifier + " " + text;
        } else if (mode == this.noMode) {
            status = (noModeIdentifier.equals("") ? text : noModeIdentifier + " " + text);
        } else {
            status = text;
        }
        if (!"".equals(status)) {
            status += " - ";
        }
        if (DatabaseConnector.getInstance().isConnected()) {
            status += "Connected to database: " + DatabaseConnector.getInstance().getDatabase() + " on host: " + DatabaseConnector.getInstance().getHostname();
        } else {
            status += "Not connected.";
        }
        statusMessageLabel.setText(status);
    }

    private void createDatabaseErrorMessage(SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "There was an error while communicating with the database: " + e, "Connection error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = EDACCApp.getApplication().getMainFrame();
            aboutBox = new EDACCAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        EDACCApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        connectToDBMenuItem = new javax.swing.JMenuItem();
        disconnectMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        importMenuItem = new javax.swing.JMenuItem();
        exportMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        generateDBMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        gridMenu = new javax.swing.JMenu();
        settingsMenuItem = new javax.swing.JMenuItem();
        modusMenu = new javax.swing.JMenu();
        manageDBModeMenuItem = new javax.swing.JRadioButtonMenuItem();
        manageExperimentModeMenuItem = new javax.swing.JRadioButtonMenuItem();
        propertyMenu = new javax.swing.JMenu();
        ImportPropertyCSVMenuItem = new javax.swing.JMenuItem();
        ManagePropertyMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        updatesMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jFilechooserCSV = new javax.swing.JFileChooser();

        mainPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1006, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 687, Short.MAX_VALUE)
        );

        menuBar.setAutoscrolls(true);
        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('F');
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCView.class, this);
        connectToDBMenuItem.setAction(actionMap.get("btnConnectToDB")); // NOI18N
        connectToDBMenuItem.setText(resourceMap.getString("connectToDBMenuItem.text")); // NOI18N
        connectToDBMenuItem.setName("connectToDBMenuItem"); // NOI18N
        fileMenu.add(connectToDBMenuItem);

        disconnectMenuItem.setAction(actionMap.get("btnDisconnect")); // NOI18N
        disconnectMenuItem.setText(resourceMap.getString("disconnectMenuItem.text")); // NOI18N
        disconnectMenuItem.setName("disconnectMenuItem"); // NOI18N
        fileMenu.add(disconnectMenuItem);

        jSeparator3.setName("jSeparator3"); // NOI18N
        fileMenu.add(jSeparator3);

        importMenuItem.setText(resourceMap.getString("importMenuItem.text")); // NOI18N
        importMenuItem.setName("importMenuItem"); // NOI18N
        importMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(importMenuItem);

        exportMenuItem.setText(resourceMap.getString("exportMenuItem.text")); // NOI18N
        exportMenuItem.setName("exportMenuItem"); // NOI18N
        exportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exportMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        fileMenu.add(jSeparator2);

        generateDBMenuItem.setAction(actionMap.get("btnGenerateTables")); // NOI18N
        generateDBMenuItem.setText(resourceMap.getString("generateDBMenuItem.text")); // NOI18N
        generateDBMenuItem.setToolTipText(resourceMap.getString("generateDBMenuItem.toolTipText")); // NOI18N
        generateDBMenuItem.setName("generateDBMenuItem"); // NOI18N
        fileMenu.add(generateDBMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        gridMenu.setAction(actionMap.get("btnGridSettings")); // NOI18N
        gridMenu.setText(resourceMap.getString("gridMenu.text")); // NOI18N
        gridMenu.setName("gridMenu"); // NOI18N

        settingsMenuItem.setAction(actionMap.get("btnGridSettings")); // NOI18N
        settingsMenuItem.setText(resourceMap.getString("settingsMenuItem.text")); // NOI18N
        settingsMenuItem.setToolTipText(resourceMap.getString("settingsMenuItem.toolTipText")); // NOI18N
        settingsMenuItem.setName("settingsMenuItem"); // NOI18N
        gridMenu.add(settingsMenuItem);

        menuBar.add(gridMenu);

        modusMenu.setMnemonic('M');
        modusMenu.setText(resourceMap.getString("modusMenu.text")); // NOI18N
        modusMenu.setName("modusMenu"); // NOI18N

        manageDBModeMenuItem.setAction(actionMap.get("manageDBMode")); // NOI18N
        manageDBModeMenuItem.setText(resourceMap.getString("manageDBModeMenuItem.text")); // NOI18N
        manageDBModeMenuItem.setToolTipText(resourceMap.getString("manageDBModeMenuItem.toolTipText")); // NOI18N
        manageDBModeMenuItem.setName("manageDBModeMenuItem"); // NOI18N
        modusMenu.add(manageDBModeMenuItem);

        manageExperimentModeMenuItem.setAction(actionMap.get("manageExperimentMode")); // NOI18N
        manageExperimentModeMenuItem.setText(resourceMap.getString("manageExperimentModeMenuItem.text")); // NOI18N
        manageExperimentModeMenuItem.setToolTipText(resourceMap.getString("manageExperimentModeMenuItem.toolTipText")); // NOI18N
        manageExperimentModeMenuItem.setName("manageExperimentModeMenuItem"); // NOI18N
        modusMenu.add(manageExperimentModeMenuItem);

        menuBar.add(modusMenu);

        propertyMenu.setText(resourceMap.getString("propertyMenu.text")); // NOI18N
        propertyMenu.setName("propertyMenu"); // NOI18N

        ImportPropertyCSVMenuItem.setText(resourceMap.getString("ImportPropertyCSVMenuItem.text")); // NOI18N
        ImportPropertyCSVMenuItem.setName("ImportPropertyCSVMenuItem"); // NOI18N
        ImportPropertyCSVMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportPropertyCSVMenuItemActionPerformed(evt);
            }
        });
        propertyMenu.add(ImportPropertyCSVMenuItem);

        ManagePropertyMenuItem.setAction(actionMap.get("btnSolverProperties")); // NOI18N
        ManagePropertyMenuItem.setText(resourceMap.getString("ManagePropertyMenuItem.text")); // NOI18N
        ManagePropertyMenuItem.setName("ManagePropertyMenuItem"); // NOI18N
        ManagePropertyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManagePropertyMenuItemActionPerformed(evt);
            }
        });
        propertyMenu.add(ManagePropertyMenuItem);

        menuBar.add(propertyMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        helpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        helpMenuItem.setMnemonic('H');
        helpMenuItem.setText(resourceMap.getString("helpMenuItem.text")); // NOI18N
        helpMenuItem.setName("helpMenuItem"); // NOI18N
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        updatesMenuItem.setText(resourceMap.getString("updatesMenuItem.text")); // NOI18N
        updatesMenuItem.setName("updatesMenuItem"); // NOI18N
        updatesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(updatesMenuItem);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
            .add(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(statusMessageLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 836, Short.MAX_VALUE)
                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelLayout.createSequentialGroup()
                .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(statusMessageLabel)
                    .add(statusAnimationLabel)
                    .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(3, 3, 3))
        );

        jFilechooserCSV.setName("jFilechooserCSV"); // NOI18N

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        JHelp helpViewer = null;
        try {
            // TODO add your handling code here:
            ClassLoader cl = EDACCView.class.getClassLoader();
            URL url = HelpSet.findHelpSet(cl, "jhelpset.hs");
            helpViewer = new JHelp(new HelpSet(cl, url));
        } catch (Exception ex) {
            System.err.println("API HelpSet not found");
        }
        JFrame edacc = new JFrame();
        edacc.setTitle("EDACC Help");
        edacc.setSize(800, 600);
        edacc.getContentPane().add(helpViewer);
        edacc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        edacc.setVisible(true);
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void ManagePropertyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManagePropertyMenuItemActionPerformed
        if (manageSolverProperties == null) {
            JFrame mainFrame = EDACCApp.getApplication().getMainFrame();
            manageSolverProperties = new EDACCManagePropertyDialog(mainFrame, experimentMode, true);
            manageSolverProperties.setLocationRelativeTo(mainFrame);
        }
        manageSolverProperties.initialize();
        manageSolverProperties.setVisible(true);
    }//GEN-LAST:event_ManagePropertyMenuItemActionPerformed

    private void showVersionInformations(Version appVersion, Version currentVersion, Version developerVersion) {
        EDACCVersionInformation dialog = new EDACCVersionInformation(EDACCApp.getApplication().getMainFrame(), true, appVersion, currentVersion, developerVersion);
        EDACCApp.getApplication().show(dialog);
    }
    
    private void updatesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesMenuItemActionPerformed
        Tasks.startTask(new TaskRunnable() {

            @Override
            public void run(Tasks task) {
                Version appVersion = new Version();
                Version currentVersion = new Version();
                Version developerVersion = null;
                try {
                    currentVersion = UpdateController.getInstance().getNewestVersion();
                    developerVersion = UpdateController.getInstance().getDeveloperVersion();
                } catch (ParserConfigurationException ex) {
                    processGetNewestVersionException(ex);
                    return;
                } catch (MalformedURLException ex) {
                    processGetNewestVersionException(ex);
                    return;
                } catch (IOException ex) {
                    processGetNewestVersionException(ex);
                    return;
                } catch (SAXException ex) {
                    processGetNewestVersionException(ex);
                    return;
                }
                final Version fAppVersion = appVersion;
                final Version fCurrentVersion = currentVersion;
                final Version fDeveloperVersion = developerVersion;
                
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        showVersionInformations(fAppVersion, fCurrentVersion, fDeveloperVersion);
                    }
                    
                });
            }

            private void processGetNewestVersionException(final Exception ex) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (ex instanceof ParserConfigurationException || ex instanceof SAXException) {
                            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "Couldn't initialize XML parser.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (ex instanceof MalformedURLException) {
                            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "Invalid update URL.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (ex instanceof IOException) {
                            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "I/O-Exception: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

            }
        });

    }//GEN-LAST:event_updatesMenuItemActionPerformed

    private void importMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importMenuItemActionPerformed
        if (!unloadModes(null)) {
            return;
        }

        final File file = EDACCImportExport.getImportFile();
        if (file == null) {
            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "No file has been selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tasks.startTask(new TaskRunnable() {

            @Override
            public void run(Tasks task) {
                try {
                    final EDACCImportExport importPanel = new EDACCImportExport(file);
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            mainPanelLayout.replace(mode, importPanel);
                            mode = importPanel;
                            manageDBModeMenuItem.setSelected(false);
                            manageExperimentModeMenuItem.setSelected(false);
                            setStatusText(mode, "IMPORT");
                        }
                    });
                } catch (final Exception e) {
                    if (!SwingUtilities.isEventDispatchThread()) {
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                handleModeChangeError(e);
                            }
                        });
                    } else {
                        handleModeChangeError(e);
                    }
                }
            }
        });


    }//GEN-LAST:event_importMenuItemActionPerformed

    private void exportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportMenuItemActionPerformed
        if (!unloadModes(null)) {
            return;
        }

        Tasks.startTask(new TaskRunnable() {

            @Override
            public void run(Tasks task) {
                try {

                    final EDACCImportExport exportPanel = new EDACCImportExport();
                    SwingUtilities.invokeAndWait(new Runnable() {

                        @Override
                        public void run() {
                            mainPanelLayout.replace(mode, exportPanel);
                            mode = exportPanel;
                            manageDBModeMenuItem.setSelected(false);
                            manageExperimentModeMenuItem.setSelected(false);
                            setStatusText(mode, "EXPORT");
                        }
                    });

                } catch (final Exception e) {
                    if (!SwingUtilities.isEventDispatchThread()) {
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                handleModeChangeError(e);
                            }
                        });
                    } else {
                        handleModeChangeError(e);
                    }
                }
            }
        });

    }//GEN-LAST:event_exportMenuItemActionPerformed

    private void ImportPropertyCSVMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportPropertyCSVMenuItemActionPerformed
        // Select csv file with propertiy data 
        int returnVal = jFilechooserCSV.showOpenDialog(mainPanel);
        File csvFile = jFilechooserCSV.getSelectedFile();
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            JFrame mainFrame = EDACCApp.getApplication().getMainFrame();
            EDACCImportPropertyCSV importCSVProps = new EDACCImportPropertyCSV(mainFrame, experimentMode, true);
            importCSVProps.setLocationRelativeTo(mainFrame);

            importCSVProps.initialize(csvFile);
            importCSVProps.setVisible(true);
        }

    }//GEN-LAST:event_ImportPropertyCSVMenuItemActionPerformed

    @Action
    public void btnConnectToDB() {
        if (databaseSettings == null) {
            JFrame mainFrame = EDACCApp.getApplication().getMainFrame();
            databaseSettings = new EDACCDatabaseSettingsView(mainFrame, true);
            databaseSettings.setLocationRelativeTo(mainFrame);
        }

        EDACCApp.getApplication().show(databaseSettings);
        if (DatabaseConnector.getInstance().isConnected()) {
            statusMessageLabel.setText("Connected to database: " + DatabaseConnector.getInstance().getDatabase() + " on host: " + DatabaseConnector.getInstance().getHostname());
            manageDBMode();
        }
    }

    @Action
    public void btnDisconnect() {
        if (experimentMode.hasUnsavedChanges() || manageDBMode.unsavedChanges) {
            if (JOptionPane.showConfirmDialog(mode,
                    "Any unsaved changes will be lost, are you sure you want to disconnect from the database?",
                    "Warning!",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

                Util.clearCaches();
                DatabaseConnector.getInstance().disconnect();
                manageDBModeMenuItem.setSelected(false);
                manageExperimentModeMenuItem.setSelected(false);
                noMode();
            }
        } else {
            Util.clearCaches();
            DatabaseConnector.getInstance().disconnect();
            manageDBModeMenuItem.setSelected(false);
            manageExperimentModeMenuItem.setSelected(false);
            noMode();
        }
    }

    @Action
    public void btnGenerateTables() {
        if (JOptionPane.showConfirmDialog(mode,
                "This will destroy the EDACC tables of your DB an create new ones. Do you wish to continue?",
                "Warning!",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            noMode();
            // User clicked on "Yes"
            Tasks.startTask(new TaskRunnable() {

                @Override
                public void run(Tasks task) {
                    try {
                        DatabaseConnector.getInstance().createDBSchema(task);

                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                manageDBMode();
                            }
                        });
                    } catch (NoConnectionToDBException ex) {
                        JOptionPane.showMessageDialog(mode,
                                "Couldn't generate the EDACC tables: No connection to database. Please connect to a database first.",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(mode,
                                "An error occured while trying to generate the EDACC tables: " + ex.getMessage(),
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mode,
                                "An error occured while trying to generate the EDACC tables: " + ex.getMessage(),
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        noMode();
                    }
                }
            });


        }
    }

    private boolean unloadModes(Component newMode) {
        if (manageExperimentModeMenuItem.isSelected()) {
            if (experimentMode.hasUnsavedChanges()) {
                String text = "switch the mode?";
                if (newMode == manageDBMode) {
                    text = "switch to Manage DB mode?";
                }
                if (JOptionPane.showConfirmDialog(mode,
                        "Any unsaved changes will be lost, are you sure you want to " + text,
                        "Warning!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

                    Util.clearCaches();
                } else {
                    manageDBModeMenuItem.setSelected(false);
                    return false;
                }
            }
            experimentMode.deinitialize();
        }
        if (manageDBModeMenuItem.isSelected()) {
            if (manageDBMode.unsavedChanges) {
                String text = "switch the mode?";
                if (newMode == experimentMode) {
                    text = "switch to Experiment mode?";
                }
                if (JOptionPane.showConfirmDialog(mode,
                        "Any unsaved changes will be lost, are you sure you want to " + text,
                        "Warning!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

                    Util.clearCaches();
                } else {
                    manageExperimentModeMenuItem.setSelected(false);
                    return false;
                }
            }
            manageDBMode.reinitialize();
        }
        noMode();
        return true;
    }

    public void noMode() {
        manageExperimentModeMenuItem.setSelected(false);
        manageDBModeMenuItem.setSelected(false);
        if (mode != noMode) {
            mainPanelLayout.replace(mode, noMode);
            mode = noMode;
        }
        setStatusText(mode, "");
    }

    @Action
    public void manageDBMode() {
        if (!unloadModes(manageDBMode)) {
            return;
        }

        try {
            manageDBMode.initialize();
            mainPanelLayout.replace(mode, manageDBMode);
            mode = manageDBMode;
            manageDBModeMenuItem.setSelected(true);
            manageExperimentModeMenuItem.setSelected(false);
            setStatusText(mode, "");
        } catch (final Exception e) {
            if (!SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        handleModeChangeError(e);
                    }
                });
            } else {
                handleModeChangeError(e);
            }
        }
    }

    @Action
    public void manageExperimentMode() {
        if (!unloadModes(experimentMode)) {
            return;
        }

        Tasks.startTask(new TaskRunnable() {

            @Override
            public void run(Tasks task) {
                try {
                    experimentMode.initialize();
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            mainPanelLayout.replace(mode, experimentMode);
                            mode = experimentMode;
                            manageExperimentModeMenuItem.setSelected(true);
                            manageDBModeMenuItem.setSelected(false);
                            setStatusText(mode, "");
                        }
                    });
                } catch (final Exception e) {
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            handleModeChangeError(e);
                        }
                    });
                }

            }
        }, true);

    }

    public void handleModeChangeError(Throwable e) {
        if (e instanceof java.io.InvalidClassException) {
            JOptionPane.showMessageDialog(EDACCView.this.getComponent(), "Invalid class.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else if (e instanceof NoConnectionToDBException) {
            JOptionPane.showMessageDialog(EDACCView.this.getComponent(), "You have to connect to the database before switching modes", "No database connection", JOptionPane.ERROR_MESSAGE);
            noMode();
        } else if (e instanceof SQLException) {
            createDatabaseErrorMessage((SQLException) e);
            noMode();
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }

    @Action
    public void btnGridSettings() {
        try {
            JFrame mainFrame = EDACCApp.getApplication().getMainFrame();
            EDACCManageGridQueuesDialog manageGridQueues = new EDACCManageGridQueuesDialog(mainFrame, true);
            manageGridQueues.setLocationRelativeTo(mainFrame);
            manageGridQueues.setVisible(true);
        } catch (NoConnectionToDBException ex) {
            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "You have to establish a connection to the database first!", "Error!", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "A database error occured while loading the dialog: " + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Action
    public void btnProperties() {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ImportPropertyCSVMenuItem;
    private javax.swing.JMenuItem ManagePropertyMenuItem;
    private javax.swing.JMenuItem connectToDBMenuItem;
    private javax.swing.JMenuItem disconnectMenuItem;
    private javax.swing.JMenuItem exportMenuItem;
    private javax.swing.JMenuItem generateDBMenuItem;
    private javax.swing.JMenu gridMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuItem importMenuItem;
    private javax.swing.JFileChooser jFilechooserCSV;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButtonMenuItem manageDBModeMenuItem;
    private javax.swing.JRadioButtonMenuItem manageExperimentModeMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu modusMenu;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenu propertyMenu;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenuItem updatesMenuItem;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private JDialog databaseSettings;
    private EDACCManagePropertyDialog manageSolverProperties;

    @Override
    public void update(Observable o, Object arg) {
        // watch connection state
        updateConnectionStateView();
    }

    /**
     * Updates the GUI components which are sensitive on the DB connection state.
     */
    private void updateConnectionStateView() {
        boolean state = DatabaseConnector.getInstance().isConnected();
        connectToDBMenuItem.setEnabled(!state);
        disconnectMenuItem.setEnabled(state);
        generateDBMenuItem.setEnabled(state);
        importMenuItem.setEnabled(state);
        exportMenuItem.setEnabled(state);
    }

    public Component getMode() {
        return mode;
    }
}
