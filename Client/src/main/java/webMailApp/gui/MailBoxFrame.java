package webMailApp.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 12.02.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class MailBoxFrame extends JFrame {
    private static String mailAddr = "default@mail.app";

    private JPanel actionPanel;
    private JPanel letterPanel;

    private JTree folderTree;

    public MailBoxFrame() {
      super("MyMailBox - " + mailAddr);

      this.setBounds(100, 100, 800, 600);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(true);

      this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

      actionPanel = new JPanel();
      actionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      actionPanel.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
      this.add(actionPanel, this);

      letterPanel = new JPanel();
      letterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      letterPanel.setPreferredSize(new Dimension(this.getWidth()*2/3, this.getHeight()));
      this.add(letterPanel, this);

      DefaultMutableTreeNode root = new DefaultMutableTreeNode(getHierarchy());
      folderTree = new JTree(root);
      actionPanel.add(folderTree);

    }

    public static void main(String[] args) {
        MailBoxFrame mailBoxFrame = new MailBoxFrame();
        mailBoxFrame.setVisible(true);
    }

    public Object[] getHierarchy() {
       return  new Object[] { "Inbox",
                               new Object[] { "folder1", "folder2", "folder3"}};

    }
}
