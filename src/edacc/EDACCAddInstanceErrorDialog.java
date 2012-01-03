/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCAddInstanceErrorDialog.java
 *
 * Created on 14-Nov-2011, 15:18:24
 */
package edacc;

import edacc.manageDB.AddInstanceErrorController;
import edacc.manageDB.InstanceDupErrorFilter;
import edacc.manageDB.InstanceDupErrorTableModel;
import edacc.manageDB.InstanceErrorTableModel;
import edacc.manageDB.InstancesToAddSelectionListener;
import edacc.model.Instance;
import edacc.model.InstanceClass;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rretz
 */
public class EDACCAddInstanceErrorDialog extends javax.swing.JDialog {

    private AddInstanceErrorController controller;
    private InstanceDupErrorFilter dupErrorFilter;
    private InstanceDupErrorFilter filter;
    private InstanceDupErrorTableModel dupErrorModel;
    private InstanceErrorTableModel toAddModel;
    private TableRowSorter rowSorter;

    /** Creates new form EDACCAddInstanceErrorDialog */
    public EDACCAddInstanceErrorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlInstancesToAdd = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInstancesToAdd = new javax.swing.JTable();
        jPnlProblemCausing = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProblemCausing = new javax.swing.JTable();
        jPnlToolBtns = new javax.swing.JPanel();
        jBtnAdd = new javax.swing.JButton();
        jBtnLink = new javax.swing.JButton();
        jBtnDrop = new javax.swing.JButton();
        jPnlDialogBtns = new javax.swing.JPanel();
        jBtnDone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 405));
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCAddInstanceErrorDialog.class);
        jPnlInstancesToAdd.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPnlInstancesToAdd.border.title"))); // NOI18N
        jPnlInstancesToAdd.setName("jPnlInstancesToAdd"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTableInstancesToAdd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableInstancesToAdd.setName("jTableInstancesToAdd"); // NOI18N
        jScrollPane1.setViewportView(jTableInstancesToAdd);

        javax.swing.GroupLayout jPnlInstancesToAddLayout = new javax.swing.GroupLayout(jPnlInstancesToAdd);
        jPnlInstancesToAdd.setLayout(jPnlInstancesToAddLayout);
        jPnlInstancesToAddLayout.setHorizontalGroup(
            jPnlInstancesToAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
            .addGroup(jPnlInstancesToAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPnlInstancesToAddLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPnlInstancesToAddLayout.setVerticalGroup(
            jPnlInstancesToAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
            .addGroup(jPnlInstancesToAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPnlInstancesToAddLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPnlProblemCausing.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPnlProblemCausing.border.title"))); // NOI18N
        jPnlProblemCausing.setName("jPnlProblemCausing"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTableProblemCausing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableProblemCausing.setName("jTableProblemCausing"); // NOI18N
        jScrollPane2.setViewportView(jTableProblemCausing);

        javax.swing.GroupLayout jPnlProblemCausingLayout = new javax.swing.GroupLayout(jPnlProblemCausing);
        jPnlProblemCausing.setLayout(jPnlProblemCausingLayout);
        jPnlProblemCausingLayout.setHorizontalGroup(
            jPnlProblemCausingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlProblemCausingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnlProblemCausingLayout.setVerticalGroup(
            jPnlProblemCausingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlProblemCausingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPnlToolBtns.setName("jPnlToolBtns"); // NOI18N

        jBtnAdd.setText(resourceMap.getString("jBtnAdd.text")); // NOI18N
        jBtnAdd.setName("jBtnAdd"); // NOI18N
        jBtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddActionPerformed(evt);
            }
        });

        jBtnLink.setText(resourceMap.getString("jBtnLink.text")); // NOI18N
        jBtnLink.setName("jBtnLink"); // NOI18N
        jBtnLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLinkActionPerformed(evt);
            }
        });

        jBtnDrop.setText(resourceMap.getString("jBtnDrop.text")); // NOI18N
        jBtnDrop.setName("jBtnDrop"); // NOI18N
        jBtnDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDropActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlToolBtnsLayout = new javax.swing.GroupLayout(jPnlToolBtns);
        jPnlToolBtns.setLayout(jPnlToolBtnsLayout);
        jPnlToolBtnsLayout.setHorizontalGroup(
            jPnlToolBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlToolBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnLink, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnDrop)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPnlToolBtnsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtnAdd, jBtnDrop, jBtnLink});

        jPnlToolBtnsLayout.setVerticalGroup(
            jPnlToolBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlToolBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jBtnAdd)
                .addComponent(jBtnLink)
                .addComponent(jBtnDrop))
        );

        jPnlToolBtnsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtnAdd, jBtnDrop, jBtnLink});

        jPnlDialogBtns.setName("jPnlDialogBtns"); // NOI18N

        jBtnDone.setText(resourceMap.getString("jBtnDone.text")); // NOI18N
        jBtnDone.setName("jBtnDone"); // NOI18N
        jBtnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlDialogBtnsLayout = new javax.swing.GroupLayout(jPnlDialogBtns);
        jPnlDialogBtns.setLayout(jPnlDialogBtnsLayout);
        jPnlDialogBtnsLayout.setHorizontalGroup(
            jPnlDialogBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlDialogBtnsLayout.createSequentialGroup()
                .addContainerGap(477, Short.MAX_VALUE)
                .addComponent(jBtnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPnlDialogBtnsLayout.setVerticalGroup(
            jPnlDialogBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlDialogBtnsLayout.createSequentialGroup()
                .addComponent(jBtnDone)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPnlToolBtns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPnlInstancesToAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPnlProblemCausing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPnlDialogBtns, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPnlInstancesToAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPnlToolBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPnlProblemCausing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlDialogBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddActionPerformed
        int[] rows = jTableInstancesToAdd.getSelectedRows();
        jTableInstancesToAdd.clearSelection();
        for (int i = 0; i < rows.length; i++) {
            rows[i] = jTableInstancesToAdd.convertRowIndexToModel(rows[i]);
        }
        controller.add(rows);
        //controller.remove(rows);
        dupErrorModel.fireTableDataChanged();
        toAddModel.fireTableDataChanged();
    }//GEN-LAST:event_jBtnAddActionPerformed

    private void jBtnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDoneActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnDoneActionPerformed

    private void jBtnDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDropActionPerformed
        int[] rows = jTableInstancesToAdd.getSelectedRows();      
        jTableInstancesToAdd.clearSelection();
        for (int i = 0; i < rows.length; i++) {
            rows[i] = jTableInstancesToAdd.convertRowIndexToModel(rows[i]);
        }
        controller.remove(rows);
        dupErrorModel.fireTableDataChanged();
        toAddModel.fireTableDataChanged();
    }//GEN-LAST:event_jBtnDropActionPerformed

    private void jBtnLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLinkActionPerformed
        jTableInstancesToAdd.clearSelection();
        try {
            controller.link(dupErrorModel.getSelected());
        } catch (SQLException ex) {
            Logger.getLogger(EDACCAddInstanceErrorDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        dupErrorModel.fireTableDataChanged();
        toAddModel.fireTableDataChanged();

    }//GEN-LAST:event_jBtnLinkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                EDACCAddInstanceErrorDialog dialog = new EDACCAddInstanceErrorDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void initialize(HashMap<Instance, ArrayList<Instance>> duplicate, HashMap<Instance, InstanceClass> instanceClasses) {
        controller = new AddInstanceErrorController(duplicate, this, instanceClasses);


        // initialize the ProblemCausing (Duplicate Instance) table

        dupErrorModel = controller.getDuplicateModel();
        jTableProblemCausing.setModel(dupErrorModel);

        jTableProblemCausing.setRowSorter(new TableRowSorter<InstanceDupErrorTableModel>(dupErrorModel));
        rowSorter = (TableRowSorter<? extends InstanceDupErrorTableModel>) jTableProblemCausing.getRowSorter();
        InstanceDupErrorFilter rowFilter = new InstanceDupErrorFilter(dupErrorModel);
        rowSorter.setRowFilter(rowFilter);
        controller.setFilter(rowFilter);


        //jTableProblemCausing.setRowSorter(controller.getDuplicateSorter());

        // initialize the InstanceToAdd table
        this.toAddModel = controller.getToAddModel();
        jTableInstancesToAdd.setModel(toAddModel);
        //jTableInstancesToAdd.setRowSorter(controller.getToAddSorter());
        jTableInstancesToAdd.getSelectionModel().addListSelectionListener(new InstancesToAddSelectionListener(controller));

        this.dupErrorModel.fireTableDataChanged();
        this.toAddModel.fireTableDataChanged();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdd;
    private javax.swing.JButton jBtnDone;
    private javax.swing.JButton jBtnDrop;
    private javax.swing.JButton jBtnLink;
    private javax.swing.JPanel jPnlDialogBtns;
    private javax.swing.JPanel jPnlInstancesToAdd;
    private javax.swing.JPanel jPnlProblemCausing;
    private javax.swing.JPanel jPnlToolBtns;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableInstancesToAdd;
    private javax.swing.JTable jTableProblemCausing;
    // End of variables declaration//GEN-END:variables

    public int getSelectedToAddInstance() {
        return jTableInstancesToAdd.getSelectedRow();
    }

    public void sort() {
        this.rowSorter.sort();
    }

    public boolean isSelected() {
        return jTableInstancesToAdd.getSelectedRowCount() != 0;
    }

    public void multipleSelecteBtnShow(boolean b) {
        if(isSelected()){
            this.jBtnLink.setEnabled(!b);
            this.jBtnAdd.setEnabled(true);
            this.jBtnDrop.setEnabled(true);
        }else {
            this.jBtnLink.setEnabled(false);
            this.jBtnAdd.setEnabled(false);
            this.jBtnDrop.setEnabled(false);
        }
        
    }

    public int getSelectedToAddRowCount() {
        return this.jTableInstancesToAdd.getSelectedRowCount();
    }
}
