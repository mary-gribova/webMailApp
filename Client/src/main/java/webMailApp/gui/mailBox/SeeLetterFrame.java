package webMailApp.gui.mailBox;

import webMailApp.dao.UserDAO;
import webMailApp.dao.dto.LetterDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 22.02.13
 * Time: 2:22
 * To change this template use File | Settings | File Templates.
 */
public class SeeLetterFrame extends JFrame{
    private JLabel letterToLabel;
    private JLabel letterFromLabel;
    private JLabel letterThemeLabel;
    private JTextField letterThemeText;
    private JTextField letterToText;
    private JTextField letterFromText;
    private JEditorPane letterBodyText;

    private JLabel letterDateLabel;
    private JTextField letterDateText;

    private JPanel fromToPanel;
    private JPanel letterBodyPanel;

    public SeeLetterFrame(LetterDTO letter) {
        super("Send letter");
        this.setBounds(100, 100, 600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        fromToPanel = new JPanel();
        fromToPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        fromToPanel.setPreferredSize(new Dimension(600, 70));
        this.add(fromToPanel);
        fromToPanel.setLayout(new GridLayout(4, 2));

        letterFromLabel = new JLabel("From: ");
        fromToPanel.add(letterFromLabel);

        letterFromText = new JTextField();
        letterFromText.setText(letter.getLetterFrom());
        letterFromText.setEditable(false);
        fromToPanel.add(letterFromText);

        letterToLabel = new JLabel("To: ");
        fromToPanel.add(letterToLabel);

        letterToText = new JTextField();
        letterToText.setText(letter.getLetterTo());
        letterToText.setEditable(false);
        fromToPanel.add(letterToText);

        letterThemeLabel = new JLabel("Theme");
        fromToPanel.add(letterThemeLabel);

        letterThemeText = new JTextField();
        letterThemeText.setText(letter.getLetterTheme());
        letterThemeText.setEditable(false);
        fromToPanel.add(letterThemeText);

        letterBodyPanel = new JPanel();
        letterBodyPanel.setPreferredSize(new Dimension(600, 300));
        letterBodyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(letterBodyPanel);


        letterDateLabel = new JLabel("Date");
        fromToPanel.add(letterDateLabel);

        letterDateText = new JTextField();
        letterDateText.setText(letter.getLetterDate().toString());
        letterDateText.setEditable(false);
        fromToPanel.add(letterDateText);



        letterBodyText = new JEditorPane();
        letterBodyText.setPreferredSize(new Dimension(550, 370));
        letterBodyText.setAutoscrolls(true);
        letterBodyText.setText(letter.getLetterBody());
        letterBodyText.setEditable(false);
        letterBodyPanel.add(letterBodyText);
    }

}
