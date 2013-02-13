package webMailApp.gui.mailBox;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 13.02.13
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
 */
public class LettersList {
    private JTable table;

    public JTable getTable() {
        LetterTableModel.initData();
        table = new JTable(new LetterTableModel()) {
//            @Override
//            public TableCellRenderer getCellRenderer(int row, int column) {
//                TableCellRenderer myRenderer = new MyRenderer();
//                if (column == 0)
//                    return myRenderer;
//                else  return super.getCellRenderer(row, column);
//            }
//
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                if (columnIndex == 0) {
//                    return Boolean.class;
//                }
//
//                return super.getColumnClass(columnIndex);
//            }

        };

        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(Color.green);

        return table;
    }

//    public class MyRenderer extends JCheckBox implements TableCellRenderer {
//       public MyRenderer() {
//       }
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                                                       boolean isSelected, boolean hasFocus, int row, int column) {
//            if (isSelected) {
//                this.setSelected(true);
//            } else {
//                this.setSelected(false);
//            }
//            return this;
//        }
//    }
}
