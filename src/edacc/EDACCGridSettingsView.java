/*
 * EDACCGridSettingsView.java
 *
 * Created on Nov 26, 2009, 9:34:21 PM
 */
package edacc;

import edacc.gridqueues.GridPropertiesTableModel;
import edacc.gridqueues.GridQueuesController;
import edacc.model.GridQueue;
import edacc.model.GridQueueDAO;
import edacc.model.NoConnectionToDBException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;
import org.jdesktop.application.Action;

/**
 *
 * @author Daniel D.
 */
public class EDACCGridSettingsView extends javax.swing.JDialog {

    private EDACCManageGridQueuesDialog manageGridQueuesDialog;
    private GridQueue currentQueue;
    private final Color BAD = new Color(255, 102, 102);
    private final Color GOOD = Color.white;
    private final GridPropertiesTableModel tableModel;

    /** Creates new form EDACCGridSettingsView */
    public EDACCGridSettingsView(java.awt.Frame parent, boolean modal, EDACCManageGridQueuesDialog manageGridQueuesDialog) {
        super(parent, modal);
        this.tableModel = new GridPropertiesTableModel(currentQueue);
        initComponents();

        TableCellRenderer renderer = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel && value instanceof String) {
                    JLabel label = (JLabel) c;
                    String text = "<html>" + ((String) value).replaceAll("\n", "<br/>") + "</html>";
                    label.setText(text);
                    int max = Math.max(label.getPreferredSize().height, table.getRowHeight(row));
                    if (table.getRowHeight(row) != max) {
                        table.setRowHeight(row, Math.max(label.getPreferredSize().height, table.getRowHeight(row)));
                    }

                }
                if (c instanceof JLabel) {
                    JLabel label = ((JLabel) c);
                    label.setVerticalAlignment(SwingConstants.TOP);
                }
                return c;

            }
        };
        tQueueProperties.setDefaultRenderer(String.class, renderer);
        tQueueProperties.setDefaultRenderer(Object.class, renderer);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(15);
        this.manageGridQueuesDialog = manageGridQueuesDialog;
        setLocationRelativeTo(manageGridQueuesDialog);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        lblNumCPUs = new javax.swing.JLabel();
        txtNumCPUs = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tQueueProperties = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNumCPUsPerJob = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCGridSettingsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(350, 400));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setName("Form"); // NOI18N

        lblName.setLabelFor(txtName);
        lblName.setText(resourceMap.getString("lblName.text")); // NOI18N
        lblName.setName("lblName"); // NOI18N

        lblLocation.setLabelFor(txtLocation);
        lblLocation.setText(resourceMap.getString("lblLocation.text")); // NOI18N
        lblLocation.setName("lblLocation"); // NOI18N

        txtName.setText(resourceMap.getString("txtName.text")); // NOI18N
        txtName.setToolTipText(resourceMap.getString("txtName.toolTipText")); // NOI18N
        txtName.setName("txtName"); // NOI18N
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        txtLocation.setText(resourceMap.getString("txtLocation.text")); // NOI18N
        txtLocation.setToolTipText(resourceMap.getString("txtLocation.toolTipText")); // NOI18N
        txtLocation.setName("txtLocation"); // NOI18N
        txtLocation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });

        lblNumCPUs.setText(resourceMap.getString("lblNumCPUs.text")); // NOI18N
        lblNumCPUs.setName("lblNumCPUs"); // NOI18N

        txtNumCPUs.setText(resourceMap.getString("txtNumCPUs.text")); // NOI18N
        txtNumCPUs.setToolTipText(resourceMap.getString("txtNumCPUs.toolTipText")); // NOI18N
        txtNumCPUs.setName("txtNumCPUs"); // NOI18N
        txtNumCPUs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        txtNumCPUs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EDACCGridSettingsView.this.keyReleased(evt);
            }
        });

        lblDescription.setLabelFor(taDescription);
        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        taDescription.setColumns(20);
        taDescription.setRows(5);
        taDescription.setToolTipText(resourceMap.getString("taDescription.toolTipText")); // NOI18N
        taDescription.setName("taDescription"); // NOI18N
        taDescription.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcSelectOnFocus(evt);
            }
        });
        jScrollPane1.setViewportView(taDescription);

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCGridSettingsView.class, this);
        btnOk.setAction(actionMap.get("btnOk")); // NOI18N
        btnOk.setText(resourceMap.getString("btnOk.text")); // NOI18N
        btnOk.setToolTipText(resourceMap.getString("btnOk.toolTipText")); // NOI18N
        btnOk.setName("btnOk"); // NOI18N

        btnCancel.setAction(actionMap.get("btnCancel")); // NOI18N
        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setToolTipText(resourceMap.getString("btnCancel.toolTipText")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk)))
        );

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tQueueProperties.setModel(tableModel);
        tQueueProperties.setName("tQueueProperties"); // NOI18N
        jScrollPane2.setViewportView(tQueueProperties);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtNumCPUsPerJob.setText(resourceMap.getString("txtNumCPUsPerJob.text")); // NOI18N
        txtNumCPUsPerJob.setName("txtNumCPUsPerJob"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblLocation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblNumCPUs)
                                    .addComponent(lblDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(22, 22, 22)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumCPUsPerJob, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtNumCPUs, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblDescription, lblLocation, lblName, lblNumCPUs});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblNumCPUs)
                            .addComponent(txtNumCPUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblLocation)
                            .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumCPUsPerJob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private JFileChooser pbsFileChooser;
    private void keyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyReleased
        checkInputs();
    }//GEN-LAST:event_keyReleased

    /**
     * Selects the complete text of the src-TextField/TextArea on the
     * focusGained event respectively deselects the text on the focusLost event.
     * Add this method to the focusGained and focusLost events of all textfields
     * which should select all text on focusGained and deselect it on focusLost.
     * The source of this event must be from the supertype JTextComponent!
     * @param evt
     */
    private void tcSelectOnFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tcSelectOnFocus
        if (!(evt.getSource() instanceof JTextComponent)) {
            return;
        }
        JTextComponent src = (JTextComponent) evt.getSource();
        if (evt.getID() == FocusEvent.FOCUS_GAINED) {
            src.selectAll();
        } else if (evt.getID() == FocusEvent.FOCUS_LOST) {
            src.setSelectionEnd(0);
        }
    }//GEN-LAST:event_tcSelectOnFocus

    /**
     * Loads the given grid queue settings to the dialog's elements.
     * If q is <code>null</code>, the dialog starts in creation mode and a new
     * queue will be created. Otherwise the given queue will be updated.
     * @param q
     * @throws SQLException
     */
    public void loadSettings(GridQueue q) throws SQLException {
        tableModel.setGridQueue(q);
        if (q != null) {
            currentQueue = q;
            btnOk.setText("Save Queue");
        } else {
            currentQueue = new GridQueue();
            btnOk.setText("Create Queue");
        }
        showQueue(currentQueue);
    }

    private void showQueue(GridQueue q) {
        if (q == null) {
            return;
        }

        // if (q != null) {
        txtName.setText(q.getName());
        txtLocation.setText(q.getLocation());
        txtNumCPUs.setText(String.valueOf(q.getNumCPUs()));
        txtNumCPUsPerJob.setText(String.valueOf(q.getNumCPUsPerJob()));
        taDescription.setText(q.getDescription());
        /*  } else {
        txtName.setText("");
        txtLocation.setText("");
        txtNumNodes.setText("");
        txtNumCPUs.setText("");
        txtWalltime.setText("");
        txtAvailNodes.setText("");
        txtMaxJobsInQueue.setText("");
        taDescription.setText("");
        setDialogMode(DialogMode.Create);
        }*/
        checkInputs();
        txtName.requestFocus();
    }

    @Action
    public void btnOk() {
        try {
            String name = txtName.getText();
            String location = txtLocation.getText();
            int numCPUs = Integer.parseInt(txtNumCPUs.getText());
            int numCPUsPerJob = Integer.parseInt(txtNumCPUsPerJob.getText());
            String description = taDescription.getText();

            currentQueue.setName(name);
            currentQueue.setLocation(location);
            currentQueue.setNumCPUs(numCPUs);
            currentQueue.setNumCPUsPerJob(numCPUsPerJob);
            currentQueue.setDescription(description);

            GridQueuesController.getInstance().createNewGridQueue(currentQueue);
            this.setVisible(false);
            manageGridQueuesDialog.refreshView();
        } catch (NoConnectionToDBException e) {
            JOptionPane.showMessageDialog(this, "Couldn't save settings. No connection to database", "No database connection", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error while saving settings: \n" + e.getMessage(), "Error saving settings", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error saving settings, integers expected", "Integers expected", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error saving settings: The specified PBS script couldn't be found!", "File not found", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving settings: " + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Action
    public void btnCancel() {
        this.setVisible(false);
    }

    public void checkInputs() {
        boolean error = false;
        GridQueue queueWithSameName = GridQueueDAO.queueWithSameNameExistsInCache(txtName.getText());

        if (txtName.getText().equals("") || (queueWithSameName != null && queueWithSameName != currentQueue)) {
            txtName.setBackground(BAD);
            error = true;
        } else {
            txtName.setBackground(GOOD);
        }

        if (!checkIntegerTextField(txtNumCPUs)) {
            error = true;
        }

        btnOk.setEnabled(!error);
    }

    /**
     * Checks if a TextField expecting an int has a valid int in it and
     * modifies the colors of the textfield (Color.BAD for invalid ints and
     * Color.GOOD for valid ints).
     * A string representation of an int is valid if it is != 0 and if it can
     * be converted to a valid int.
     * @param tf
     * @return if the text of the TextField is a valid int.
     */
    private boolean checkIntegerTextField(JTextField tf) {
        try {
            if (Integer.parseInt(tf.getText()) == 0) {
                tf.setBackground(BAD);
                return false;
            } else {
                tf.setBackground(GOOD);
                return true;
            }
        } catch (NumberFormatException e) {
            tf.setBackground(BAD);
            return false;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumCPUs;
    private javax.swing.JTable tQueueProperties;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumCPUs;
    private javax.swing.JTextField txtNumCPUsPerJob;
    // End of variables declaration//GEN-END:variables
}
