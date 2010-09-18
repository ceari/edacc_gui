/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCSelectPropertyValueTypeClassDialog.java
 *
 * Created on 16.09.2010, 11:50:19
 */

package edacc;

import edacc.model.NoConnectionToDBException;
import edacc.properties.PropertyValueTypeSelectionModel;
import edacc.properties.PropertyValueTypeTableModel;
import edacc.properties.SelectPropertyValueTypeClassController;
import edacc.satinstances.PropertyValueType;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rretz
 */
public class EDACCSelectPropertyValueTypeClassDialog extends javax.swing.JDialog {
    private SelectPropertyValueTypeClassController controller;
    private PropertyValueTypeSelectionModel propValueTypeTableModel;
    private File file;

    /** Creates new form EDACCSelectPropertyValueTypeClassDialog */
    public EDACCSelectPropertyValueTypeClassDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // initialize tablePropertyValueTypes
        propValueTypeTableModel = new PropertyValueTypeSelectionModel(new String[]{"name"});
        tablePropertyValueTypes.setModel(propValueTypeTableModel);
        tablePropertyValueTypes.setRowSorter(new TableRowSorter<PropertyValueTypeSelectionModel>(propValueTypeTableModel));

        controller = new SelectPropertyValueTypeClassController(this, tablePropertyValueTypes);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePropertyValueTypes = new javax.swing.JTable();
        buttonAdd = new javax.swing.JButton();
        buttonDone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCSelectPropertyValueTypeClassDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        panelMain.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("panelMain.border.title"))); // NOI18N
        panelMain.setName("panelMain"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablePropertyValueTypes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePropertyValueTypes.setName("tablePropertyValueTypes"); // NOI18N
        jScrollPane1.setViewportView(tablePropertyValueTypes);

        buttonAdd.setText(resourceMap.getString("buttonAdd.text")); // NOI18N
        buttonAdd.setName("buttonAdd"); // NOI18N
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonDone.setText(resourceMap.getString("buttonDone.text")); // NOI18N
        buttonDone.setName("buttonDone"); // NOI18N
        buttonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(buttonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(buttonDone)))
                .addContainerGap())
        );

        panelMainLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonAdd, buttonDone});

        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd)
                    .addComponent(buttonDone))
                .addContainerGap())
        );

        panelMainLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonAdd, buttonDone});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        int[] selectedRows = tablePropertyValueTypes.getSelectedRows();
        if(selectedRows.length == 0){
            JOptionPane.showMessageDialog(this,
                "Nothing is selected. Select a property value type.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } else{
            for (int i = 0; i < selectedRows.length; i++){
                selectedRows[i] = tablePropertyValueTypes.convertRowIndexToModel(selectedRows[i]);
            }
            try {
                controller.addPropertyValueTypes(selectedRows, file);
                this.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(EDACCSelectPropertyValueTypeClassDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(EDACCSelectPropertyValueTypeClassDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EDACCSelectPropertyValueTypeClassDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }//GEN-LAST:event_buttonAddActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EDACCSelectPropertyValueTypeClassDialog dialog = new EDACCSelectPropertyValueTypeClassDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTable tablePropertyValueTypes;
    // End of variables declaration//GEN-END:variables

    void initialize(File file) throws SQLException, NoConnectionToDBException, IOException {
        this.file = file;
        Vector<String> names = controller.readPropertyValueTypesFromFile(file);
        propValueTypeTableModel.addRows(names);
    }

}
