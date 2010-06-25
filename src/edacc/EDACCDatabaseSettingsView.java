/*
 * EDACCGridSettingsView.java
 *
 * Created on Nov 26, 2009, 7:54:46 PM
 */
package edacc;

import edacc.model.DatabaseConnector;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel D.
 */
public class EDACCDatabaseSettingsView extends javax.swing.JDialog {

    /** Creates new form EDACCGridSettingsView */
    public EDACCDatabaseSettingsView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //btnConnect.requestFocus();
        getRootPane().setDefaultButton(btnConnect);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConnect = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JPasswordField();
        lblPort = new javax.swing.JLabel();
        lblHostname = new javax.swing.JLabel();
        txtHostname = new javax.swing.JTextField();
        lblDatabase = new javax.swing.JLabel();
        txtDatabase = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCDatabaseSettingsView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setIconImage(null);
        setMinimumSize(new java.awt.Dimension(400, 220));
        setName("Form"); // NOI18N
        setResizable(false);

        btnConnect.setText(resourceMap.getString("btnConnect.text")); // NOI18N
        btnConnect.setToolTipText(resourceMap.getString("btnConnect.toolTipText")); // NOI18N
        btnConnect.setName("btnConnect"); // NOI18N
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        btnConnect.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnConnectKeyPressed(evt);
            }
        });

        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setToolTipText(resourceMap.getString("btnCancel.toolTipText")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        txtPassword.setText(resourceMap.getString("txtPassword.text")); // NOI18N
        txtPassword.setToolTipText(resourceMap.getString("txtPassword.toolTipText")); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        lblPort.setLabelFor(txtPort);
        lblPort.setText(resourceMap.getString("lblPort.text")); // NOI18N
        lblPort.setMaximumSize(new java.awt.Dimension(100, 17));
        lblPort.setMinimumSize(new java.awt.Dimension(100, 17));
        lblPort.setName("lblPort"); // NOI18N
        lblPort.setPreferredSize(new java.awt.Dimension(100, 17));

        lblHostname.setLabelFor(txtHostname);
        lblHostname.setText(resourceMap.getString("lblHostname.text")); // NOI18N
        lblHostname.setMaximumSize(new java.awt.Dimension(100, 17));
        lblHostname.setMinimumSize(new java.awt.Dimension(100, 17));
        lblHostname.setName("lblHostname"); // NOI18N
        lblHostname.setPreferredSize(new java.awt.Dimension(100, 17));

        txtHostname.setText(resourceMap.getString("txtHostname.text")); // NOI18N
        txtHostname.setToolTipText(resourceMap.getString("txtHostname.toolTipText")); // NOI18N
        txtHostname.setName("txtHostname"); // NOI18N
        txtHostname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHostnameKeyPressed(evt);
            }
        });

        lblDatabase.setLabelFor(txtDatabase);
        lblDatabase.setText(resourceMap.getString("lblDatabase.text")); // NOI18N
        lblDatabase.setName("lblDatabase"); // NOI18N
        lblDatabase.setPreferredSize(new java.awt.Dimension(100, 17));

        txtDatabase.setText(resourceMap.getString("txtDatabase.text")); // NOI18N
        txtDatabase.setToolTipText(resourceMap.getString("txtDatabase.toolTipText")); // NOI18N
        txtDatabase.setName("txtDatabase"); // NOI18N
        txtDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatabaseActionPerformed(evt);
            }
        });
        txtDatabase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDatabaseKeyPressed(evt);
            }
        });

        lblUsername.setLabelFor(txtUsername);
        lblUsername.setText(resourceMap.getString("lblUsername.text")); // NOI18N
        lblUsername.setName("lblUsername"); // NOI18N
        lblUsername.setPreferredSize(new java.awt.Dimension(100, 17));

        txtUsername.setText(resourceMap.getString("txtUsername.text")); // NOI18N
        txtUsername.setToolTipText(resourceMap.getString("txtUsername.toolTipText")); // NOI18N
        txtUsername.setName("txtUsername"); // NOI18N
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        txtPort.setText(resourceMap.getString("txtPort.text")); // NOI18N
        txtPort.setToolTipText(resourceMap.getString("txtPort.toolTipText")); // NOI18N
        txtPort.setName("txtPort"); // NOI18N
        txtPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPortKeyPressed(evt);
            }
        });

        lblPassword.setLabelFor(txtPassword);
        lblPassword.setText(resourceMap.getString("lblPassword.text")); // NOI18N
        lblPassword.setName("lblPassword"); // NOI18N
        lblPassword.setPreferredSize(new java.awt.Dimension(100, 17));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(lblDatabase, 0, 0, Short.MAX_VALUE)
                    .addComponent(lblUsername, 0, 0, Short.MAX_VALUE)
                    .addComponent(lblPassword, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPort, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblDatabase, lblHostname, lblPassword, lblUsername});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnConnect))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        try {
            DatabaseConnector.getInstance().connect(txtHostname.getText(), Integer.parseInt(txtPort.getText()), txtUsername.getText(), txtDatabase.getText(), txtPassword.getText());
            this.setVisible(false);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Couldn't find the MySQL jdbc driver Connector-J. Make sure it's in your Java class path", "Database driver not found", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the database: \n\n" + e.getMessage(), "Connection error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnConnectActionPerformed

    private void txtHostnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHostnameKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtHostname.select(0, 0);
//            txtPort.requestFocus();
//            txtPort.selectAll();
//        }
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) // connect to DB
        {
            btnConnectActionPerformed(null);
        }

    }//GEN-LAST:event_txtHostnameKeyPressed

    private void txtPortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPortKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtPort.select(0, 0);
//            txtDatabase.requestFocus();
//            txtDatabase.selectAll();
//        }
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) // connect to DB
        {
            btnConnectActionPerformed(null);
        }
    }//GEN-LAST:event_txtPortKeyPressed

    private void txtDatabaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatabaseKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtDatabase.select(0, 0);
//            txtUsername.requestFocus();
//            txtUsername.selectAll();
//        }
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) // connect to DB
        {
            btnConnectActionPerformed(null);
        }
    }//GEN-LAST:event_txtDatabaseKeyPressed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//            txtUsername.select(0, 0);
//            txtPassword.requestFocus();
//            txtPassword.selectAll();
//        }
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) // connect to DB
        {
            btnConnectActionPerformed(null);
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) // connect to DB
        {
            btnConnectActionPerformed(null);
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnConnectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConnectKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) // connect to DB
        {
            btnConnectActionPerformed(null);
        }
    }//GEN-LAST:event_btnConnectKeyPressed

    private void btnCancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) // connect to DB
        {
        btnCancelActionPerformed(null);
        }

    }//GEN-LAST:event_btnCancelKeyPressed

    private void txtDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatabaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatabaseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConnect;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDatabase;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtDatabase;
    private javax.swing.JTextField txtHostname;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
