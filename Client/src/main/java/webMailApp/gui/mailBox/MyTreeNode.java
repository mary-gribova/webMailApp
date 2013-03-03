package webMailApp.gui.mailBox;

import webMailApp.dto.LetterDTO;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Mariia Gribova
 * @version 1.0
 */

public class MyTreeNode extends DefaultMutableTreeNode {
    private LetterDTO letter;

    public MyTreeNode(LetterDTO letter) {
        super(letter.getLetterFrom() + " ," + letter.getLetterTheme());
        this.letter = letter;
    }

    public LetterDTO getLetter() {
        return letter;
    }
}
