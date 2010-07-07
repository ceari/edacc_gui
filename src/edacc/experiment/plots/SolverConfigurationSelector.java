package edacc.experiment.plots;

import edacc.experiment.SolverConfigurationTableModel;
import edacc.model.SolverConfiguration;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author simon
 */
public class SolverConfigurationSelector extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;
    private SolverConfigurationTableModel tableModel;
    private JButton btnSelectAll, btnDeselectAll, btnInvert;
    private TableRowSorter<SolverConfigurationTableModel> sorter;
    private JLabel lblFilter;

    public SolverConfigurationSelector() {
        super(new GridBagLayout());
        Dimension dimensionButton = new Dimension(109, 25);
        tableModel = new SolverConfigurationTableModel();
        table = new JTable(tableModel);
        sorter = new TableRowSorter<SolverConfigurationTableModel>(tableModel);
        table.setRowSorter(sorter);

        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(15);
        colModel.getColumn(1).setPreferredWidth(400);
        colModel.getColumn(2).setPreferredWidth(400);
        scrollPane = new JScrollPane(table);
        lblFilter = new JLabel("");
        lblFilter.setForeground(Color.red);
        scrollPane.setMinimumSize(new Dimension(0,250));
        scrollPane.setPreferredSize(new Dimension(0, 250));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(lblFilter, c);
        c.gridy = 1;
        add(scrollPane, c);
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 2;
        c.gridwidth = 1;
        btnSelectAll = new JButton("Select All");
        btnSelectAll.setPreferredSize(dimensionButton);
        btnSelectAll.setMinimumSize(dimensionButton);
        add(btnSelectAll, c);
        btnDeselectAll = new JButton("Deselect All");
        btnDeselectAll.setPreferredSize(dimensionButton);
        btnDeselectAll.setMinimumSize(dimensionButton);
        c.gridx = 1;
        add(btnDeselectAll, c);
        btnInvert = new JButton("Invert Selection");
        btnInvert.setPreferredSize(dimensionButton);
        btnInvert.setMinimumSize(dimensionButton);
        c.gridx = 2;
        add(btnInvert, c);

        btnSelectAll.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnSelectAll();
            }
        });

        btnDeselectAll.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnDeselectAll();
            }
        });

        btnInvert.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnInvert();
            }
        });
    }

    public void btnSelectAll() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.selected[i] = true;
        }
        tableModel.fireTableDataChanged();
    }

    public void btnDeselectAll() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.selected[i] = false;
        }
        tableModel.fireTableDataChanged();
    }

    public void btnInvert() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.selected[i] = !tableModel.selected[i];
        }
        tableModel.fireTableDataChanged();
    }

    public void setSolverConfigurations(Vector<SolverConfiguration> solverConfigurations) {
        tableModel.setSolverConfigurations(solverConfigurations);
    }

    public Vector<SolverConfiguration> getSelectedSolverConfigurations() {
        return tableModel.getSelectedSolverConfigurations();
    }
}