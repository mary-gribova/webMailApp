package webMailApp.gui.mailBox;

import webMailApp.dto.LetterDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Mariia Gribova
 * @version 1.0
 */

public class LettersList {
    private JTable table;
    private List<LetterDTO> letters;

    public LettersList(List<LetterDTO> letters) {
        this.letters = letters;
    }

    public JTable getTable() {
        table = new JTable(new LetterTableModel(letters));
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(Color.green);
        return table;
    }

}