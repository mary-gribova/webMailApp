package webMailApp.gui.mailBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JButton write;

    private JButton delLetter;
    private JTable letterList;

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
      letterPanel.setPreferredSize(new Dimension(this.getWidth() * 2 / 3, this.getHeight()));
      letterPanel.setBackground(Color.cyan);

      //-------------letters list---------------------
       letterList = new LettersList().getTable();
       JScrollPane scrollPaneList = new JScrollPane(letterList);
       letterList.setFillsViewportHeight(true);

      //----------------------------------
      delLetter = new JButton("Delete");
      delLetter.addActionListener(new DelActionListener());


      letterPanel.add(delLetter);
      letterPanel.add(scrollPaneList);
      this.add(letterPanel, this);


     DefaultMutableTreeNode root = new DefaultMutableTreeNode("Inbox");
     root.add(new DefaultMutableTreeNode("folder1"));
     DefaultMutableTreeNode folder2 = new DefaultMutableTreeNode("folder2");
     folder2.add(new DefaultMutableTreeNode("letter"));
     folder2.add(new DefaultMutableTreeNode("letter"));
     folder2.add(new DefaultMutableTreeNode("letter"));

     root.add(folder2);
     root.add(new DefaultMutableTreeNode("folder2"));
     folderTree = new JTree(root);


     actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
     actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

     write = new JButton("Write");
     actionPanel.add(write, actionPanel);

     actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
     JScrollPane scrollPane = new JScrollPane(folderTree);
     scrollPane.setPreferredSize(new Dimension(actionPanel.getWidth(), actionPanel.getHeight()));
     actionPanel.add(scrollPane);
   }

    public class DelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < letterList.getRowCount(); i++) {
                if ((Boolean)letterList.getValueAt(i, 0)) {
                    ((LetterTableModel)letterList.getModel()).removeRow(i);
                }
            }
        }
    }
}
