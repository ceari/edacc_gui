/*
 * EDACCExperimentModeGenerateJobs.java
 *
 * Created on 09.04.2011, 18:43:44
 */
package edacc;

import edacc.experiment.Util;

/**
 *
 * @author simon
 */
public class EDACCExperimentModeGenerateJobs extends javax.swing.JDialog {

    private boolean cancelled;
    private int cpuTimeLimit, memoryLimit, wallClockTimeLimit, stackSizeLimit, outputSizeLimitFirst, outputSizeLimitLast, maxSeed;

    /** Creates new form EDACCExperimentModeGenerateJobs */
    public EDACCExperimentModeGenerateJobs(java.awt.Frame parent, boolean modal, int cpuTimeLimit, int memoryLimit, int wallClockTimeLimit, int stackSizeLimit, int outputSizeLimitFirst, int outputSizeLimitLast) {
        super(parent, modal);
        initComponents();
        cancelled = true;
        txtCPUTimeLimit.setText("" + cpuTimeLimit);
        txtMemoryLimit.setText("" + memoryLimit);
        txtWallClockTimeLimit.setText("" + wallClockTimeLimit);
        txtStackSizeLimit.setText("" + stackSizeLimit);
        if (outputSizeLimitFirst != -1 && outputSizeLimitLast != -1) {
            chkLimitOutput.setSelected(true);
            txtOutputLimitFirst.setText("" + outputSizeLimitFirst);
            txtOutputLimitLast.setText("" + outputSizeLimitLast);
            txtOutputLimitFirst.setEnabled(true);
            txtOutputLimitLast.setEnabled(true);
        } else {
            chkLimitOutput.setSelected(false);
            txtOutputLimitFirst.setText("0");
            txtOutputLimitLast.setText("0");
            txtOutputLimitFirst.setEnabled(false);
            txtOutputLimitLast.setEnabled(false);
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

        jPanel1 = new javax.swing.JPanel();
        txtCPUTimeLimit = new javax.swing.JTextField();
        txtMemoryLimit = new javax.swing.JTextField();
        txtWallClockTimeLimit = new javax.swing.JTextField();
        txtStackSizeLimit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        chkLimitOutput = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtOutputLimitFirst = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtOutputLimitLast = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaxSeed = new javax.swing.JTextField();
        btnGenerate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCExperimentModeGenerateJobs.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        txtCPUTimeLimit.setText(resourceMap.getString("txtCPUTimeLimit.text")); // NOI18N
        txtCPUTimeLimit.setToolTipText(resourceMap.getString("txtCPUTimeLimit.toolTipText")); // NOI18N
        txtCPUTimeLimit.setName("txtCPUTimeLimit"); // NOI18N
        txtCPUTimeLimit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCPUTimeLimitFocusGained(evt);
            }
        });

        txtMemoryLimit.setText(resourceMap.getString("txtMemoryLimit.text")); // NOI18N
        txtMemoryLimit.setToolTipText(resourceMap.getString("txtMemoryLimit.toolTipText")); // NOI18N
        txtMemoryLimit.setName("txtMemoryLimit"); // NOI18N

        txtWallClockTimeLimit.setText(resourceMap.getString("txtWallClockTimeLimit.text")); // NOI18N
        txtWallClockTimeLimit.setName("txtWallClockTimeLimit"); // NOI18N

        txtStackSizeLimit.setText(resourceMap.getString("txtStackSizeLimit.text")); // NOI18N
        txtStackSizeLimit.setName("txtStackSizeLimit"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        chkLimitOutput.setText(resourceMap.getString("chkLimitOutput.text")); // NOI18N
        chkLimitOutput.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        chkLimitOutput.setName("chkLimitOutput"); // NOI18N
        chkLimitOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLimitOutputActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtOutputLimitFirst.setText(resourceMap.getString("txtOutputLimitFirst.text")); // NOI18N
        txtOutputLimitFirst.setName("txtOutputLimitFirst"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtOutputLimitLast.setText(resourceMap.getString("txtOutputLimitLast.text")); // NOI18N
        txtOutputLimitLast.setName("txtOutputLimitLast"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtMaxSeed.setText(resourceMap.getString("txtMaxSeed.text")); // NOI18N
        txtMaxSeed.setName("txtMaxSeed"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chkLimitOutput)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMemoryLimit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(txtWallClockTimeLimit, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(txtStackSizeLimit, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(txtCPUTimeLimit, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(txtOutputLimitFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(txtOutputLimitLast, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(txtMaxSeed, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCPUTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txtMemoryLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWallClockTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStackSizeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkLimitOutput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOutputLimitFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtOutputLimitLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaxSeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnGenerate.setText(resourceMap.getString("btnGenerate.text")); // NOI18N
        btnGenerate.setName("btnGenerate"); // NOI18N
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(450, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerate)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerate)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cancelled = true;
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        try {
            boolean format_check = true;
            format_check &= Util.verifyNumber_geq(txtCPUTimeLimit, -1);
            format_check &= Util.verifyNumber_geq(txtMemoryLimit, -1);
            format_check &= Util.verifyNumber_geq(txtWallClockTimeLimit, -1);
            format_check &= Util.verifyNumber_geq(txtStackSizeLimit, -1);

            if (chkLimitOutput.isSelected()) {
                format_check &= Util.verifyNumber_geq(txtOutputLimitFirst, 0);
                format_check &= Util.verifyNumber_geq(txtOutputLimitLast, 0);
            }
            if (!format_check) {
                throw new NumberFormatException();
            }
            cpuTimeLimit = Integer.parseInt(txtCPUTimeLimit.getText());
            memoryLimit = Integer.parseInt(txtMemoryLimit.getText());
            wallClockTimeLimit = Integer.parseInt(txtWallClockTimeLimit.getText());
            stackSizeLimit = Integer.parseInt(txtStackSizeLimit.getText());
            if (chkLimitOutput.isSelected()) {
                outputSizeLimitFirst = Integer.parseInt(txtOutputLimitFirst.getText());
                outputSizeLimitLast = Integer.parseInt(txtOutputLimitLast.getText());
            } else {
                outputSizeLimitFirst = -1;
                outputSizeLimitLast = -1;
            }
            maxSeed = Integer.parseInt(txtMaxSeed.getText());
            cancelled = false;
            this.setVisible(false);
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Couldn't validate input. Please correct input and try again.", "Invalid data", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void chkLimitOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLimitOutputActionPerformed
        if (chkLimitOutput.isSelected()) {
            txtOutputLimitFirst.setEnabled(true);
            txtOutputLimitLast.setEnabled(true);
        } else {
            txtOutputLimitFirst.setEnabled(false);
            txtOutputLimitLast.setEnabled(false);
        }
    }//GEN-LAST:event_chkLimitOutputActionPerformed

    private void txtCPUTimeLimitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPUTimeLimitFocusGained
        txtCPUTimeLimit.selectAll();
    }//GEN-LAST:event_txtCPUTimeLimitFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JCheckBox chkLimitOutput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCPUTimeLimit;
    private javax.swing.JTextField txtMaxSeed;
    private javax.swing.JTextField txtMemoryLimit;
    private javax.swing.JTextField txtOutputLimitFirst;
    private javax.swing.JTextField txtOutputLimitLast;
    private javax.swing.JTextField txtStackSizeLimit;
    private javax.swing.JTextField txtWallClockTimeLimit;
    // End of variables declaration//GEN-END:variables

    public boolean isCancelled() {
        return cancelled;
    }

    public int getCpuTimeLimit() {
        return cpuTimeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public int getStackSizeLimit() {
        return stackSizeLimit;
    }

    public int getWallClockTimeLimit() {
        return wallClockTimeLimit;
    }

    public int getOutputSizeLimitFirst() {
        return outputSizeLimitFirst;
    }

    public int getOutputSizeLimitLast() {
        return outputSizeLimitLast;
    }

    public int getMaxSeed() {
        return maxSeed;
    }
}
