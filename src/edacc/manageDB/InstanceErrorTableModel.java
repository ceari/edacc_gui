/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edacc.manageDB;

import edacc.experiment.ThreadSafeDefaultTableModel;
import edacc.model.Instance;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * TableModel created for the Table with the instances a error occured by adding them to the database.
 * Used in the EDACCAddInstanceErrorDialog.
 * @author rretz
 */
public class InstanceErrorTableModel extends DefaultTableModel {

    private String[] columns = {"Name", "MD5"};
    private ArrayList<Instance> instances = new ArrayList<Instance>();

    public InstanceErrorTableModel() {
        
    }

    InstanceErrorTableModel(ArrayList<Instance> toAdd) {
        this.instances = toAdd;
    }

    @Override
    public int getRowCount() {
        if(instances == null)
            return 0;
        return instances.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Class getColumnClass(int col) {
        if (instances.isEmpty()) {
            return String.class;
        } else {
            return getValueAt(0, col).getClass();
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return instances.get(row).getName();
            case 1:
                return instances.get(row).getMd5();
            default:
                return "";
        }
    }

    public void remove(ArrayList<Instance> toRemove) {
        for (Instance remove : toRemove) {
            instances.remove(remove);
        }
    }

    public Instance getInstance(int row) {
        return instances.get(row);
    }

    public ArrayList<Instance> getAllInstances() {
        return instances;
    }

}
