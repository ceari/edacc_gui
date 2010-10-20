/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCComputeInstancePropertyDialog.java
 *
 * Created on 19.10.2010, 15:47:29
 */

package edacc;

import edacc.manageDB.ManageDBInstances;
import edacc.model.Instance;
import edacc.model.Property;
import java.util.Vector;

/**
 *
 * @author dgall
 */
public class EDACCComputeInstancePropertyDialog extends javax.swing.JDialog {

    private Vector<Instance> instances;
    private ManageDBInstances manageDBInstances;

    /** Creates new form EDACCComputeInstancePropertyDialog */
    public EDACCComputeInstancePropertyDialog(java.awt.Frame parent, ManageDBInstances manageDBInstances, Vector<Instance> instances) {
        super(parent, true);
        initComponents();
        this.instances = instances;
        this.manageDBInstances = manageDBInstances;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        bCompute = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCComputeInstancePropertyDialog.class);
        bCompute.setText(resourceMap.getString("bCompute.text")); // NOI18N
        bCompute.setName("bCompute"); // NOI18N
        bCompute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bComputeActionPerformed(evt);
            }
        });

        bCancel.setText(resourceMap.getString("bCancel.text")); // NOI18N
        bCancel.setName("bCancel"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bCompute)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(bCancel))
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bCompute)
                    .addComponent(bCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComputeActionPerformed
        Vector<Property> properties = new Vector<Property>();
        manageDBInstances.computeProperties(instances, properties);
    }//GEN-LAST:event_bComputeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bCompute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
