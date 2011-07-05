/*
 * SelectMixedDomainPanel.java
 *
 * Created on 04.07.2011, 11:22:55
 */
package edacc.parametergrapheditor;

import edacc.parameterspace.domain.Domain;
import edacc.parameterspace.domain.MixedDomain;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 *
 * @author simon
 */
public class SelectMixedDomainPanel extends javax.swing.JPanel implements IDomainPanel {

    private GridBagConstraints c;
    private GridBagLayout layout;

    private Domain domain;
    /** Creates new form SelectMixedDomainPanel */
    public SelectMixedDomainPanel(Domain domain) {
        initComponents();
        this.domain = domain;
        layout = new GridBagLayout();
        setLayout(layout);
        c = new GridBagConstraints();
        SelectDomainPanel panel = new SelectDomainPanel(domain);
        panel.comboDomain.removeItem("Mixed");
        panel.comboDomain.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comboDomainActionPerformed(e);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = .5;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(panel, c);
        setGridBagConstraints();
        invalidate();
        revalidate();
        repaint();
    }

    private void setGridBagConstraints() {
        c.gridy = 0;
        c.weighty = 1;
        for (int i = 0; i < this.getComponentCount(); i++) {
            c.gridy++;
            c.weighty *= 1000;
            layout.setConstraints(this.getComponent(i), c);
        }
    }

    private void comboDomainActionPerformed(ActionEvent e) {
        boolean found = false;
        for (int i = this.getComponentCount() - 1; i >= 0; i--) {
            SelectDomainPanel panel = (SelectDomainPanel) getComponent(i);
            if ("".equals(panel.comboDomain.getSelectedItem())) {
                if (found) {
                    this.remove(i);
                }
                found = true;
            }
        }
        if (!found) {
            SelectDomainPanel panel = new SelectDomainPanel(domain);
            panel.comboDomain.removeItem("Mixed");
            panel.comboDomain.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    comboDomainActionPerformed(e);
                }
            });
            add(panel, c);
        }
        setGridBagConstraints();
        invalidate();
        revalidate();
        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public Domain getDomain() throws InvalidDomainException {
        if (getComponentCount() == 1) {
            throw new InvalidDomainException("You have to select at least one domain for a mixed domain.");
        }
        LinkedList<Domain> domains = new LinkedList<Domain>();
        
        // last domain is an invalid domain (-1)
        for (int i = 0; i < getComponentCount()-1; i++) {
            domains.add(((IDomainPanel) getComponent(i)).getDomain());
        }
        return new MixedDomain(domains);
    }
}
