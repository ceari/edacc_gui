/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCCreateInstanceClassDialog.java
 *
 * Created on 19.03.2010, 14:55:29
 */

package edacc;

/**
 *
 * @author Lea Fetzer
 */
public class EDACCCreateInstanceClassDialog extends javax.swing.JDialog {

    /** Creates new form EDACCCreateInstanceClassDialog */
    public EDACCCreateInstanceClassDialog(java.awt.Frame parent, boolean modal) {
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
        java.awt.GridBagConstraints gridBagConstraints;

        SourceOrUserClass = new javax.swing.ButtonGroup();
        jTextArea1 = new javax.swing.JTextArea();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelDescription = new javax.swing.JLabel();
        jRadioButtonSourceClass = new javax.swing.JRadioButton();
        jRadioButtonUserClass = new javax.swing.JRadioButton();
        jLabelTitle = new javax.swing.JLabel();
        jButtonCreate = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCCreateInstanceClassDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(3);
        jTextArea1.setTabSize(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        getContentPane().add(jTextArea1, gridBagConstraints);

        jLabelName.setText(resourceMap.getString("jLabelName.text")); // NOI18N
        jLabelName.setName("jLabelName"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabelName, gridBagConstraints);

        jTextFieldName.setText(resourceMap.getString("jTextFieldName.text")); // NOI18N
        jTextFieldName.setName("jTextFieldName"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        getContentPane().add(jTextFieldName, gridBagConstraints);

        jLabelDescription.setText(resourceMap.getString("jLabelDescription.text")); // NOI18N
        jLabelDescription.setName("jLabelDescription"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabelDescription, gridBagConstraints);

        SourceOrUserClass.add(jRadioButtonSourceClass);
        jRadioButtonSourceClass.setText(resourceMap.getString("jRadioButtonSourceClass.text")); // NOI18N
        jRadioButtonSourceClass.setName("jRadioButtonSourceClass"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        getContentPane().add(jRadioButtonSourceClass, gridBagConstraints);

        SourceOrUserClass.add(jRadioButtonUserClass);
        jRadioButtonUserClass.setText(resourceMap.getString("jRadioButtonUserClass.text")); // NOI18N
        jRadioButtonUserClass.setName("jRadioButtonUserClass"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        getContentPane().add(jRadioButtonUserClass, gridBagConstraints);

        jLabelTitle.setText(resourceMap.getString("jLabelTitle.text")); // NOI18N
        jLabelTitle.setName("jLabelTitle"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        getContentPane().add(jLabelTitle, gridBagConstraints);

        jButtonCreate.setText(resourceMap.getString("jButtonCreate.text")); // NOI18N
        jButtonCreate.setName("jButtonCreate"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        getContentPane().add(jButtonCreate, gridBagConstraints);

        jButtonCancel.setText(resourceMap.getString("jButtonCancel.text")); // NOI18N
        jButtonCancel.setName("jButtonCancel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(jButtonCancel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EDACCCreateInstanceClassDialog dialog = new EDACCCreateInstanceClassDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup SourceOrUserClass;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JRadioButton jRadioButtonSourceClass;
    private javax.swing.JRadioButton jRadioButtonUserClass;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables

}
