package webMailApp.gui.mailBox;

import webMailApp.dao.dto.LetterDTO;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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
    private List<LetterDTO> lettersCopyList;

    public LetterTableModel(List<LetterDTO> letters) {
        lettersCopyList = letters;
        initData(lettersCopyList);
    }

    public void initData(List<LetterDTO> letters) {
        Iterator iterator = letters.iterator();
        LetterDTO letter;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        while (iterator.hasNext()) {
            letter = (LetterDTO) iterator.next();
            data.add(new Object[]{false, letter.getLetterFrom(), letter.getLetterTheme(),
                    format.format(letter.getLetterDate())});
        }
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

    public LetterDTO removeRow(int row) {
        data.remove(row);
        LetterDTO l = lettersCopyList.remove(row);
        this.fireTableDataChanged();
        return l;
    }

    public void addRow(Object[] row) {
        data.add(row);
        this.fireTableDataChanged();
    }

    public List<LetterDTO> getLettersCopyList() {
        return lettersCopyList;
    }
}