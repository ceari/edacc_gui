/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCManageInstanceColumnSelection.java
 *
 * Created on 02.05.2011, 18:51:54
 */
package edacc;

import edacc.manageDB.InstanceTableModel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author rretz
 */
public class EDACCManageInstanceColumnSelection extends javax.swing.JDialog {

    private InstanceTableModel instancesTableModel;
    private JCheckBox[] checkboxes;

    /** Creates new form EDACCManageInstanceColumnSelection */
    public EDACCManageInstanceColumnSelection(JFrame mainFrame, boolean modal, InstanceTableModel instancesTableModel) {
        super(mainFrame, modal);
        this.instancesTableModel = instancesTableModel;
        initComponents();
        boolean[] visibility = instancesTableModel.getColumnVisibility();
        checkboxes = new JCheckBox[visibility.length];
        pnlBasicColumns.setLayout(new GridBagLayout());
        GridBagConstraints basicColumnsConstraints = new GridBagConstraints();
        basicColumnsConstraints.gridx = 0;
        basicColumnsConstraints.weightx = 1;
        basicColumnsConstraints.fill = GridBagConstraints.HORIZONTAL;
        basicColumnsConstraints.gridy = 0;
        basicColumnsConstraints.gridheight = 1;
        basicColumnsConstraints.gridwidth = 1;
        basicColumnsConstraints.anchor = GridBagConstraints.WEST;

        GridBagConstraints instancePropertyColumnsConstraints = new GridBagConstraints();
        instancePropertyColumnsConstraints.gridx = 0;
        instancePropertyColumnsConstraints.weightx = 1;
        instancePropertyColumnsConstraints.fill = GridBagConstraints.HORIZONTAL;
        instancePropertyColumnsConstraints.gridy = 0;
        instancePropertyColumnsConstraints.gridheight = 1;
        instancePropertyColumnsConstraints.gridwidth = 1;
        instancePropertyColumnsConstraints.anchor = GridBagConstraints.WEST;
        pnlInstancePropertyColumns.setLayout(new GridBagLayout());

         for (int i = 0; i < checkboxes.length; i++) {
            checkboxes[i] = new JCheckBox(instancesTableModel.getAllColumnNames()[i], visibility[i]);
            checkboxes[i].setVisible(true);
            if (i < edacc.manageDB.InstanceTableModel.COL_PROP) {
                pnlBasicColumns.add(checkboxes[i], basicColumnsConstraints);
                basicColumnsConstraints.gridy++;
            } else {
                pnlInstancePropertyColumns.add(checkboxes[i], instancePropertyColumnsConstraints);
                instancePropertyColumnsConstraints.gridy++;
            }
        }
        jPanel1.setPreferredSize(new Dimension(0, pnlBasicColumns.getPreferredSize().height + pnlInstancePropertyColumns.getPreferredSize().height));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlBasicColumns = new javax.swing.JPanel();
        pnlInstancePropertyColumns = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 100));
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 300));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCManageInstanceColumnSelection.class);
        pnlBasicColumns.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("pnlBasicColumns.border.title"))); // NOI18N
        pnlBasicColumns.setName("pnlBasicColumns"); // NOI18N

        javax.swing.GroupLayout pnlBasicColumnsLayout = new javax.swing.GroupLayout(pnlBasicColumns);
        pnlBasicColumns.setLayout(pnlBasicColumnsLayout);
        pnlBasicColumnsLayout.setHorizontalGroup(
            pnlBasicColumnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );
        pnlBasicColumnsLayout.setVerticalGroup(
            pnlBasicColumnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(pnlBasicColumns, gridBagConstraints);

        pnlInstancePropertyColumns.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("pnlInstancePropertyColumns.border.title"))); // NOI18N
        pnlInstancePropertyColumns.setName("pnlInstancePropertyColumns"); // NOI18N

        javax.swing.GroupLayout pnlInstancePropertyColumnsLayout = new javax.swing.GroupLayout(pnlInstancePropertyColumns);
        pnlInstancePropertyColumns.setLayout(pnlInstancePropertyColumnsLayout);
        pnlInstancePropertyColumnsLayout.setHorizontalGroup(
            pnlInstancePropertyColumnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );
        pnlInstancePropertyColumnsLayout.setVerticalGroup(
            pnlInstancePropertyColumnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(pnlInstancePropertyColumns, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(399, 50));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCManageInstanceColumnSelection.class, this);
        jButton1.setAction(actionMap.get("btnSelect")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setAction(actionMap.get("btnAbort")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         boolean[] visibility = new boolean[checkboxes.length];
        for (int i = 0; i < visibility.length; i++) {
            visibility[i] = checkboxes[i].isSelected();
        }
        instancesTableModel.setColumnVisibility(visibility, true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlBasicColumns;
    private javax.swing.JPanel pnlInstancePropertyColumns;
    // End of variables declaration//GEN-END:variables
}
