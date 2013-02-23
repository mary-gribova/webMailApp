package webMailApp.gui.mailBox;

import webMailApp.dao.dto.LetterDTO;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 13.02.13
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
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