/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCVersionInformation.java
 *
 * Created on Mar 9, 2012, 12:38:55 PM
 */
package edacc;

import edacc.model.TaskRunnable;
import edacc.model.Tasks;
import edacc.updates.UpdateController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author simon
 */
public class EDACCVersionInformation extends javax.swing.JDialog {

    private Version appVersion;
    private Version developerVersion;
    private Version currentVersion;

    /** Creates new form EDACCVersionInformation */
    public EDACCVersionInformation(java.awt.Frame parent, boolean modal, Version appVersion, Version currentVersion, Version developerVersion) {
        super(parent, modal);
        initComponents();
        this.appVersion = appVersion;
        this.developerVersion = developerVersion;
        this.currentVersion = currentVersion;

        btnUpdate.setEnabled(false);
        btnChangeVersion.setText("Developer Version");
        lblVersion.setText(appVersion.toString());
        if (appVersion.isDeveloperVersion()) {
            btnChangeVersion.setText("Release Version");
            if (developerVersion != null && !appVersion.getMd5().equals(developerVersion.getMd5())) {
                lblStatus.setText("A new developer version is available. Use the update button to update the EDACC application.");
                btnUpdate.setEnabled(true);
            } else {
                lblStatus.setText("No updates available.");
            }
        } else {
            if (developerVersion == null) {
                // should never happen
                btnChangeVersion.setEnabled(false);
            }
            if (appVersion.compareTo(currentVersion) < 0) {
                lblStatus.setText("EDACC application " + currentVersion + " is available. Use the update button to update the EDACC application.");
                btnUpdate.setEnabled(true);
            } else {
                lblStatus.setText("No updates available.");
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnChangeVersion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCVersionInformation.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        lblStatus.setText(resourceMap.getString("lblStatus.text")); // NOI18N
        lblStatus.setName("lblStatus"); // NOI18N

        lblVersion.setText(resourceMap.getString("lblVersion.text")); // NOI18N
        lblVersion.setName("lblVersion"); // NOI18N

        btnClose.setText(resourceMap.getString("btnClose.text")); // NOI18N
        btnClose.setName("btnClose"); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnUpdate.setText(resourceMap.getString("btnUpdate.text")); // NOI18N
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnChangeVersion.setText(resourceMap.getString("btnChangeVersion.text")); // NOI18N
        btnChangeVersion.setName("btnChangeVersion"); // NOI18N
        btnChangeVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeVersionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(68, 68, 68)
                        .addComponent(lblVersion))
                    .addComponent(lblStatus)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnChangeVersion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblVersion))
                .addGap(26, 26, 26)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnUpdate)
                    .addComponent(btnChangeVersion))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnChangeVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeVersionActionPerformed
        Version v = null;
        if (appVersion.isDeveloperVersion()) {
            if (JOptionPane.showConfirmDialog(this, "Do you really want to update to the current release version?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                v = currentVersion;
            }
        } else {
            if (JOptionPane.showConfirmDialog(this, "Do you really want to update to the current developer version?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                v = developerVersion;
            }
        }
        if (v != null) {
            final Version fVersion = v;
            Tasks.startTask(new TaskRunnable() {

                @Override
                public void run(Tasks task) {
                    try {
                        UpdateController.getInstance().download(task, fVersion);
                    } catch (MalformedURLException ex) {
                        processDownloadException(ex);
                        return;
                    } catch (IOException ex) {
                        processDownloadException(ex);
                        return;
                    } catch (VersionException ex) {
                        processDownloadException(ex);
                        return;
                    }

                    try {
                        UpdateController.getInstance().startUpdater();

                    } catch (FileNotFoundException ex) {
                        processStartUpdaterException(ex);
                        return;
                    } catch (IOException ex) {
                        processStartUpdaterException(ex);
                        return;
                    } catch (ClassNotFoundException ex) {
                        processStartUpdaterException(ex);
                        return;
                    }
                }

                private void processStartUpdaterException(Exception ex) {
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "Couldn't start updater.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }

                private void processDownloadException(final Exception ex) {
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(EDACCApp.getApplication().getMainFrame(), "Error while downloading update: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
            });
        }

    }//GEN-LAST:event_btnChangeVersionActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeVersion;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblVersion;
    // End of variables declaration//GEN-END:variables
}
