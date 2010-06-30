/*
 * EDACCPlotView.java
 *
 * Created on 29.06.2010, 17:28:12
 */
package edacc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.jdesktop.application.Action;
import org.rosuda.javaGD.GDCanvas;

class ExtensionFileFilter extends FileFilter {

    String description;
    String extensions[];

    public ExtensionFileFilter(String description, String extension) {
        this(description, new String[]{extension});
    }

    public ExtensionFileFilter(String description, String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    }

    private void toLower(String array[]) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = array[i].toLowerCase();
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            String path = file.getAbsolutePath().toLowerCase();
            for (int i = 0, n = extensions.length; i < n; i++) {
                String extension = extensions[i];
                if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 *
 * @author simon
 */
public class EDACCPlotView extends javax.swing.JDialog {

    public static GDCanvas gdc;

    /** Creates new form EDACCPlotView */
    public EDACCPlotView(java.awt.Frame parent) {
        super(parent);
        this.setModal(true);
        initComponents();
        gdc = new GDCanvas(0, 0);
        gdc.setPreferredSize(new Dimension(0, 0));
        gdc.setMinimumSize(new Dimension(0, 0));
        gdc.setMaximumSize(new Dimension(65535, 65535));

        pnlPlot.setLayout(new BorderLayout());
        pnlPlot.add(gdc, BorderLayout.CENTER);
        
    }

    public GDCanvas getGDCanvas() {
        return gdc;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPlot = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCPlotView.class);
        pnlPlot.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("pnlPlot.border.title"))); // NOI18N
        pnlPlot.setName("pnlPlot"); // NOI18N

        javax.swing.GroupLayout pnlPlotLayout = new javax.swing.GroupLayout(pnlPlot);
        pnlPlot.setLayout(pnlPlotLayout);
        pnlPlotLayout.setHorizontalGroup(
            pnlPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );
        pnlPlotLayout.setVerticalGroup(
            pnlPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCPlotView.class, this);
        btnSave.setAction(actionMap.get("btnSave")); // NOI18N
        btnSave.setText(resourceMap.getString("btnSave.text")); // NOI18N
        btnSave.setName("btnSave"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave)
                .addContainerGap(584, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void btnSave() {
        JFileChooser fc = new JFileChooser();
        // Set the platform depending image types
        for (String suffix:ImageIO.getWriterFileSuffixes()) {
            fc.setFileFilter(new ExtensionFileFilter(suffix.toUpperCase() + " (*." + suffix + ")", suffix));
        }
        fc.setAcceptAllFileFilterUsed(true);
        fc.setMultiSelectionEnabled(false);
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Get a buffered Image with the right dimension
            BufferedImage bufferedImage = new BufferedImage(gdc.getWidth(), gdc.getHeight(), BufferedImage.TYPE_INT_RGB);
            // paint the image on the new buffered image
            gdc.paintAll(bufferedImage.createGraphics());
            String fileName = fc.getSelectedFile().getAbsolutePath();
            String imgType;
            // find out the image type to be used
            if (fc.getFileFilter() == null || !(fc.getFileFilter() instanceof ExtensionFileFilter)) {
                imgType = "";
                for (String suffix: ImageIO.getWriterFileSuffixes()) {
                    if (fileName.endsWith("." + suffix)) {
                        imgType = suffix;
                        break;
                    }
                }
            } else {
                imgType = ((ExtensionFileFilter) fc.getFileFilter()).extensions[0];
                if (!fileName.endsWith("." + imgType)) {
                    fileName += "." +imgType;
                }
            }
            if ("".equals(imgType)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Could not determine the specified file extension.", "Wrong file extension", javax.swing.JOptionPane.ERROR_MESSAGE);
                return ;
            }
            File f = new File(fileName);
            if (f.exists()) {
                int userInput = javax.swing.JOptionPane.showConfirmDialog(this, "File exists. Overwrite?", "File exists", javax.swing.JOptionPane.YES_NO_OPTION);
                if (userInput == 1) return ;
            }
            try {
                ImageIO.write(bufferedImage, imgType, f);
            } catch (IOException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error while writing file: " + ex.getMessage(), "Error while writing file", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlPlot;
    // End of variables declaration//GEN-END:variables
}
