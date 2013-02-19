package webMailApp.gui.mailBox;

import webMailApp.dao.dto.LetterDTO;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 19.02.13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
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
