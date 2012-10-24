/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCSelectPropertiesDialog.java
 *
 * Created on 18.04.2011, 15:10:29
 */
package edacc;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author dgall
 */
public class EDACCSelectPropertiesDialog extends javax.swing.JDialog {

    /** Creates new form EDACCSelectPropertiesDialog */
    public EDACCSelectPropertiesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    class CellRenderer extends JCheckBox implements ListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(" " + list.getModel().getElementAt(index));
            setSelected(isSelected());
            return (this);
        }
    }

    class CheckList extends JList {

        public CheckList() {
            this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.setCellRenderer(new CellRenderer());


            this.addListSelectionListener(new CheckSelectionListener());
        }
    }

    class CheckSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == true) {
                return;
            }
            final JList l = (JList) e.getSource();
            final int[] selected = l.getSelectedIndices();
            System.out.println();
            for (int i = 0; i < selected.length; i++) {
                System.out.println(selected[i]);
                JCheckBox checkBox = (JCheckBox) l.getCellRenderer().getListCellRendererComponent(l, null, selected[i], true, true);
            checkBox.setSelected(checkBox.isSelected());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        propertySelectionList = new CheckList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        propertySelectionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        propertySelectionList.setName("PropertySelectionList"); // NOI18N
        jScrollPane1.setViewportView(propertySelectionList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList propertySelectionList;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        EDACCSelectPropertiesDialog dlg = new EDACCSelectPropertiesDialog(null, true);
        dlg.show();
    }

}
