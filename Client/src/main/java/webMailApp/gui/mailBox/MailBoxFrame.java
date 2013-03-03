package webMailApp.gui.mailBox;

import webMailApp.dao.LetterDAO;
import webMailApp.dao.UserDAO;
import webMailApp.dto.FolderDTO;
import webMailApp.dto.LetterDTO;
import webMailApp.dto.UserDTO;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @author Mariia Gribova
 * @version 1.0
 */


public class MailBoxFrame extends JFrame {
    private UserDTO user;

    private JPanel actionPanel;
    private JPanel letterPanel;

    private JTree folderTree;

    private JButton write;

    private JButton delLetter;
    private JTable letterList;

    private JButton seeLetter;

    private static final UserDAO userDAO = new UserDAO();

    private int delay = 5000;

    private List<LetterDTO> lettersData = new ArrayList<LetterDTO>();

    private Timer timer;
    private final String email;

    public UserDTO getUserByEmail() {
        return userDAO.getUserByEmail(email);
    }


    public DefaultMutableTreeNode getAndShowLetters() {
        List<FolderDTO> folder = new LetterDAO().getRecievedLetters(email);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Inbox");


        if (folder != null && folder.size() != 0) {
            FolderDTO curFolder = folder.get(0);

            Iterator curFolderIterator = curFolder.getLetters().iterator();

            while (curFolderIterator.hasNext()) {
                LetterDTO letter = (LetterDTO) curFolderIterator.next();
                lettersData.add(letter);
                root.add(new MyTreeNode(letter));
            }
        }

        return root;
    }

    public MailBoxFrame(String email) {
        this.email = email;
        user = getUserByEmail();
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

        seeLetter = new JButton("See letter");

        letterPanel.add(delLetter);
        letterPanel.add(scrollPaneList);
        letterPanel.add(seeLetter);

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

        seeLetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = letterList.getSelectedRow();
                LetterTableModel m = (LetterTableModel) letterList.getModel();
                LetterDTO l = null;

                if (index >= 0 && index < m.getLettersCopyList().size()) {
                    l = m.getLettersCopyList().get(index);
                    new SeeLetterFrame(l).setVisible(true);
                }

            }
        });

        prepareStartShedule();
    }

    public static void showMailBoxFrame(final String sessionID)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MailBoxFrame(sessionID).setVisible(true);
            }
        });
    }

    private void prepareStartShedule()
    {
        timer = new Timer(delay, startCycle());
        timer.start();
    }

    private Action startCycle()
    {
        return new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                /*Updating list of letters (jtable)*/
                LetterTableModel m = (LetterTableModel) letterList.getModel();
                List<FolderDTO> f = new LetterDAO().getRecievedLetters(user.getUserAddress());

                List<LetterDTO> letters;

                if (f != null && f.size() != 0) {
                    letters = f.get(0).getLetters();
                    m.initNewData(letters);

                    /*updating jtree*/
                    DefaultTreeModel model = (DefaultTreeModel)folderTree.getModel();
                    DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
                    root.removeAllChildren();

                    Iterator cur = letters.iterator();

                    while (cur.hasNext()) {
                        LetterDTO letter = (LetterDTO) cur.next();
                        root.add(new MyTreeNode(letter));
                    }

                    model.reload(root);
                }
            }
        };
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

            int curDel = del;

            while (del > 0) {
                for (int i = 0; i < letterList.getRowCount(); i++) {
                    if ((Boolean)letterList.getModel().getValueAt(i, 0)) {
                        lettersToDel.add(((LetterTableModel)letterList.getModel()).getLettersCopyList().get(i));
                        del--;
                    }
                }
            }

            del = curDel;

            boolean b = new LetterDAO().delLetters(lettersToDel);

            if (b) {
                while (del > 0) {
                    for (int i = 0; i < letterList.getRowCount(); i++) {
                        if ((Boolean)letterList.getModel().getValueAt(i, 0)) {
                            ((LetterTableModel)letterList.getModel()).removeRow(i);
                            del--;
                        }
                    }
                }
            }
        }
    }

}