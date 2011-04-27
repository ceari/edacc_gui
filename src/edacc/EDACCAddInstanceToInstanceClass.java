/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCAddInstanceToInstanceClass.java
 *
 * Created on 22.03.2010, 17:22:57
 */

package edacc;

import edacc.model.InstanceClass;
import edacc.model.InstanceClassDAO;
import edacc.model.NoConnectionToDBException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author rretz
 */
public class EDACCAddInstanceToInstanceClass extends javax.swing.JDialog {
    InstanceClass input;
    /** Creates new form EDACCAddInstanceToInstanceClass */
    public EDACCAddInstanceToInstanceClass(java.awt.Frame parent, boolean modal) throws NoConnectionToDBException, SQLException {
        super(parent, modal);
        initComponents();
        jTreeClass.setModel(new DefaultTreeModel(InstanceClassDAO.getAllAsTreeFast()));
        jTreeClass.setRootVisible(false);
        jTreeClass.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);     
        jTreeClass.setRootVisible(false);
    }

    public InstanceClass getInput(){
        return input;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSourceOrUser = new javax.swing.ButtonGroup();
        jPanelButton = new javax.swing.JPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTreeClass = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCAddInstanceToInstanceClass.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(350, 500));
        setModal(true);
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanelButton.setName("jPanelButton"); // NOI18N

        jButtonCancel.setText(resourceMap.getString("jButtonCancel.text")); // NOI18N
        jButtonCancel.setToolTipText(resourceMap.getString("jButtonCancel.toolTipText")); // NOI18N
        jButtonCancel.setName("jButtonCancel"); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonOk.setText(resourceMap.getString("jButtonOk.text")); // NOI18N
        jButtonOk.setToolTipText(resourceMap.getString("jButtonOk.toolTipText")); // NOI18N
        jButtonOk.setName("jButtonOk"); // NOI18N
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonLayout = new javax.swing.GroupLayout(jPanelButton);
        jPanelButton.setLayout(jPanelButtonLayout);
        jPanelButtonLayout.setHorizontalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonLayout.createSequentialGroup()
                .addComponent(jButtonOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                .addComponent(jButtonCancel))
        );

        jPanelButtonLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancel, jButtonOk});

        jPanelButtonLayout.setVerticalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOk)))
        );

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTreeClass.setName("jTreeClass"); // NOI18N
        jScrollPane2.setViewportView(jTreeClass);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(jPanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        input = new InstanceClass();
        if(jTreeClass.getSelectionCount() != 1){
             JOptionPane.showMessageDialog(this,
                    "Select a instance class",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            input = (InstanceClass) ((DefaultMutableTreeNode)jTreeClass.getSelectionPath().getLastPathComponent()).getUserObject();
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        input = null;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            jTreeClass.setModel(new DefaultTreeModel(InstanceClassDAO.getAllAsTree()));
         } catch (NoConnectionToDBException ex) {
            JOptionPane.showMessageDialog(this.rootPane,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(this.rootPane,
                    "There is a Problem with the database: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowActivated


    private void jTableSourceClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSourceClassMouseClicked
    
    }//GEN-LAST:event_jTableSourceClassMouseClicked

    private void jTableUserClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUserClassMouseClicked
     
    }//GEN-LAST:event_jTableUserClassMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSourceOrUser;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTreeClass;
    // End of variables declaration//GEN-END:variables

}
