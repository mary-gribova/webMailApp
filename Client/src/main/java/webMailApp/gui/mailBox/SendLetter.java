package webMailApp.gui.mailBox;

import webMailApp.dao.LetterDAO;
import webMailApp.dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 16.02.13
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public class SendLetter extends JFrame {
  private JButton sendButton;
  private JLabel letterToLabel;
  private JLabel letterFromLabel;
  private JLabel letterThemeLabel;
  private JTextField letterThemeText;
  private JTextField letterToText;
  private JTextField letterFromText;
  private JEditorPane letterBodyText;

  private JPanel fromToPanel;
  private JPanel letterBodyPanel;

  public SendLetter(String letterFrom) {
      super("Send letter");
      this.setBounds(100, 100, 600, 600);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setResizable(false);
      this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


      fromToPanel = new JPanel();

      fromToPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      fromToPanel.setPreferredSize(new Dimension(600, 70));
      this.add(fromToPanel);



      fromToPanel.setLayout(new GridLayout(3, 2));
      letterFromLabel = new JLabel("From: ");
      fromToPanel.add(letterFromLabel);

      letterFromText = new JTextField();
      letterFromText.setText(letterFrom);
      letterFromText.setEditable(false);
      fromToPanel.add(letterFromText);

      letterToLabel = new JLabel("To: ");
      fromToPanel.add(letterToLabel);


      letterToText = new JTextField();
      fromToPanel.add(letterToText);

      letterThemeLabel = new JLabel("Theme");
      fromToPanel.add(letterThemeLabel);

      letterThemeText = new JTextField();
      fromToPanel.add(letterThemeText);

      letterBodyPanel = new JPanel();
      letterBodyPanel.setPreferredSize(new Dimension(600, 300));
      letterBodyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      this.add(letterBodyPanel);

      letterBodyText = new JEditorPane();
      letterBodyText.setPreferredSize(new Dimension(550, 370));
      letterBodyText.setAutoscrolls(true);
      letterBodyPanel.add(letterBodyText);

      sendButton = new JButton("Send");
      this.add(sendButton);

      sendButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String letterFrom = letterFromText.getText().toString();
              String letterTo = letterToText.getText().toString();
              String letterTheme = letterThemeText.getText().toString();
              String letterBody = letterBodyText.getText().toString();

              String s = new LetterDAO().sendLetter(letterFrom, letterTo, letterTheme, new Date(), letterBody);

               if (s.equals("No such to")) {
                   JOptionPane.showMessageDialog(getFrame(), "No such recipient!");
               } else if (s.equals("Fail")) {

               } else if (s.equals("OK")) {
                   getFrame().setVisible(false);
               }


          }
      });

  }

  private JFrame getFrame() {
      return this;
  }
}
