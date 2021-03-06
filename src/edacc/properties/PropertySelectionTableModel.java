/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.properties;

import edacc.model.Property;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rretz
 */
public class PropertySelectionTableModel extends AbstractTableModel{
    private Vector<Property> rows = new Vector<Property>();
    private Vector<Boolean> selected = new Vector<Boolean>();
    private String[] columns = {"Result property", "Selected"};

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return rows.get(rowIndex).getName();
            case 1:
                return selected.get(rowIndex);
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column){
        return columns[column];
    }

   @Override
    public Class getColumnClass(int column){
        if(column == 0 )
            return String.class;
        else
            return Boolean.class;
    }

    public Property getProperty(int rowIndex){
        return rows.get(rowIndex);
    }

    public void setSelected(boolean select, int rowIndex){
        selected.set(rowIndex, select);
        this.fireTableDataChanged();
    }
    
    public void setValueAt(Object value, int row, int col) {
        if(col == 1){
            selected.set(row, (Boolean) value);
            fireTableCellUpdated(row, col);
        }
        

    }

    public void addProperty(Property toAdd){
        this.rows.add(toAdd);
        this.selected.add(false);
    }

    public void addProperties(Vector<Property> toAdd){
        for(int i = 0; i < toAdd.size(); i++){
            addProperty(toAdd.get(i));
        }
        this.fireTableDataChanged();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex){
        if( columnIndex == 1) return true;
        return false;
    }

    public Vector<Property> getAllChoosen(){
        Vector<Property> choosen = new Vector<Property>();
        for(int i = 0; i < selected.size(); i++){
            if(selected.get(i))
                choosen.add(rows.get(i));
        }
        return choosen;
    }
}
