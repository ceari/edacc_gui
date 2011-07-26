/*
 * EDACCExperimentModeRandomInstanceSelection.java
 *
 * Created on 17.09.2010, 16:54:10
 */
package edacc;

import edacc.experiment.Util;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.jdesktop.application.Action;

/**
 *
 * @author simon
 */
public class EDACCExperimentModeRandomInstanceSelection extends javax.swing.JDialog {

    private EDACCExperimentMode expMode;

    /** Creates new form EDACCExperimentModeRandomInstanceSelection */
    public EDACCExperimentModeRandomInstanceSelection(java.awt.Frame parent, boolean modal, final EDACCExperimentMode expMode) {
        super(parent, modal);
        this.expMode = expMode;
        initComponents();
        txtCount.selectAll();
        txtCount.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSelect();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    btnCancel();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    txtCount.setText(Util.getNumberText(txtCount.getText()));
                }
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSelect = new javax.swing.JButton();
        txtCount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCExperimentModeRandomInstanceSelection.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCExperimentModeRandomInstanceSelection.class, this);
        btnCancel.setAction(actionMap.get("btnCancel")); // NOI18N
        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.setPreferredSize(new java.awt.Dimension(80, 25));

        btnSelect.setAction(actionMap.get("btnSelect")); // NOI18N
        btnSelect.setText(resourceMap.getString("btnSelect.text")); // NOI18N
        btnSelect.setName("btnSelect"); // NOI18N
        btnSelect.setPreferredSize(new java.awt.Dimension(80, 25));

        txtCount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCount.setText(resourceMap.getString("txtCount.text")); // NOI18N
        txtCount.setName("txtCount"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCount, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void btnCancel() {
        this.dispose();
    }

    @Action
    public void btnSelect() {
        try {
            expMode.randomInstanceSelection(Integer.parseInt(txtCount.getText()));
            this.dispose();
        } catch (NumberFormatException ex) {
            txtCount.selectAll();
            javax.swing.JOptionPane.showMessageDialog(null, "Count has to be an integer.", "Random Instance Selection", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            txtCount.selectAll();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Random Instance Selection", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtCount;
    // End of variables declaration//GEN-END:variables
}
