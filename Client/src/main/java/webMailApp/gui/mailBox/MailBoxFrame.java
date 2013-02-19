package webMailApp.gui.mailBox;

import webMailApp.dao.UserDAO;
import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.UserDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 12.02.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class MailBoxFrame extends JFrame {
    private String sessionID;
    private UserDTO user;

    private JPanel actionPanel;
    private JPanel letterPanel;

    private JTree folderTree;

    private JButton write;

    private JButton delLetter;
    private JTable letterList;

    private List<LetterDTO> lettersData = new ArrayList<LetterDTO>();

    public UserDTO getUserBySession() {
        return new UserDAO().getUserBySessionID(sessionID);
    }

    public DefaultMutableTreeNode getAndShowLetters() {
        List<FolderDTO> folder = new UserDAO().getRecievedLetters(user.getUserAddress());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Inbox");

        Iterator folderIterator = folder.iterator();
        while (folderIterator.hasNext()) {
            FolderDTO curFolder = (FolderDTO) folderIterator.next();

            if (!curFolder.getFolderName().equals("Inbox")) {
                DefaultMutableTreeNode someFolder = new DefaultMutableTreeNode(curFolder.getFolderName());
                root.add(someFolder);

                Iterator curFolderIterator = curFolder.getLetters().iterator();

                while (curFolderIterator.hasNext()) {
                    LetterDTO letter = (LetterDTO) curFolderIterator.next();
                    lettersData.add(letter);
                    someFolder.add(new MyTreeNode(letter));
                }
            }  else {
                Iterator curFolderIterator = curFolder.getLetters().iterator();

                while (curFolderIterator.hasNext()) {
                    LetterDTO letter = (LetterDTO) curFolderIterator.next();
                    lettersData.add(letter);
                    root.add(new MyTreeNode(letter));
                }
            }

        }

        return root;

    }

    public void delLetters(List<LetterDTO> letters) {
        new UserDAO().delLetters(letters);
    }

    public MailBoxFrame(String sessionID) {
        this.sessionID = sessionID;
        user = getUserBySession();
        this.setTitle("MyMailBox - " + user.getUserAddress());

        DefaultMutableTreeNode root = getAndShowLetters();

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
        letterList = new LettersList(lettersData).getTable();
        JScrollPane scrollPaneList = new JScrollPane(letterList);
        letterList.setFillsViewportHeight(true);

        //----------------------------------
        delLetter = new JButton("Delete");
        delLetter.addActionListener(new DelActionListener());


        letterPanel.add(delLetter);
        letterPanel.add(scrollPaneList);
        this.add(letterPanel, this);

        folderTree = new JTree(root);


        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        write = new JButton("Write");
        actionPanel.add(write, actionPanel);

        actionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JScrollPane scrollPane = new JScrollPane(folderTree);
        scrollPane.setPreferredSize(new Dimension(actionPanel.getWidth(), actionPanel.getHeight()));
        actionPanel.add(scrollPane);

        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SendLetter(user.getUserAddress()).setVisible(true);
            }
        });


    }

    public class DelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int count = letterList.getRowCount();
            int del = 0;
            ArrayList<LetterDTO> lettersToDel = new ArrayList<LetterDTO>();

            for (int i = 0; i < count; i++) {
                if ((Boolean)letterList.getModel().getValueAt(i, 0)) {
                    del++;
                }
            }

            while (del > 0) {
                for (int i = 0; i < letterList.getRowCount(); i++) {
                    if ((Boolean)letterList.getModel().getValueAt(i, 0)) {
                        lettersToDel.add(((LetterTableModel)letterList.getModel()).removeRow(i));
                        del--;
                    }
                }
            }

            new UserDAO().delLetters(lettersToDel);
        }
    }

}