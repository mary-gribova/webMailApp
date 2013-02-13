package webMailApp.gui.mailBox;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 14.02.13
 * Time: 0:10
 * To change this template use File | Settings | File Templates.
 */

public class LetterTableModel extends AbstractTableModel {
    private static final String[] columnNames = {"", "From", "Theme", "Date"};
    private static ArrayList<Object[]> data = new ArrayList<Object[]>();


    public static void initData() {
        for(int i = 0; i < 5; i++)
            data.add(new Object[]{false, "", "", ""});
    }

    public int getColumnCount() {
        return columnNames.length;
    }


    public int getRowCount() {
        return data.size();
    }


    public String getColumnName(int col) {
        return columnNames[col];
    }


    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
           return true;
        else
          return false;
    }


    public void setValueAt(Object value, int row, int col) {
        data.get(row)[col] = value;
        fireTableCellUpdated(row, col);
    }

    public void removeRow(int row) {
       data.remove(row);
        this.fireTableDataChanged();
    }

    public void addRow(Object[] row) {
        data.add(row);
        this.fireTableDataChanged();
    }

}
