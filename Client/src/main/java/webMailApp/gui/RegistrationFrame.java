package webMailApp.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 12.02.13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationFrame extends JFrame {
    private JLabel firstNameLabel;
    private JTextField firstNameText;

    private JLabel lastNameLabel;
    private JTextField lastNameText;

    private JLabel birthLabel;
    private JTextField birthText;

    private JLabel phoneNameLabel;
    private JTextField phoneNameText;

    private JLabel passLabel;
    private JPasswordField passText;

    private JButton registerBut;

    public RegistrationFrame() {
       super("Registration");
       this.setBounds(150, 150, 600, 400);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setResizable(false);
       this.setLayout(new GridBagLayout());

        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.insets = new Insets(5, 5, 5, 5);

        firstNameLabel = new JLabel("First Name: ");
        gBC.gridx = 0;
        gBC.gridy = 0;
        this.add(firstNameLabel, gBC);

        firstNameText = new JTextField();
        gBC.gridx = 60;
        gBC.gridy = 0;
        gBC.gridwidth = 70;
        this.add(firstNameText, gBC);

        lastNameLabel = new JLabel("Last Name: ");
        gBC.gridx = 0;
        gBC.gridy = 20;
        this.add(lastNameLabel, gBC);

        lastNameText = new JTextField();
        gBC.gridx = 60;
        gBC.gridy = 20;
        this.add(lastNameText, gBC);

        birthLabel = new JLabel("Birth: ");
        gBC.gridx = 0;
        gBC.gridy = 40;
        this.add(birthLabel, gBC);

        birthText = new JTextField();
        gBC.gridx = 60;
        gBC.gridy = 40;
        this.add(birthText, gBC);

        phoneNameLabel = new JLabel("Phone: ");
        gBC.gridx = 0;
        gBC.gridy = 60;
        this.add(phoneNameLabel, gBC);

        phoneNameText = new JTextField();
        gBC.gridx = 60;
        gBC.gridy = 60;
        this.add(phoneNameText, gBC);

        passLabel = new JLabel("Pass: ");
        gBC.gridx = 0;
        gBC.gridy = 80;
        this.add(passLabel, gBC);

        passText = new JPasswordField();
        gBC.gridx = 60;
        gBC.gridy = 80;
        this.add(passText, gBC);

        registerBut = new JButton("Register");
        gBC.gridx = 15;
        gBC.gridy = 100;
        this.add(registerBut, gBC);

    }


    public static void main(String[] args) {
        RegistrationFrame regFrame = new RegistrationFrame();
        regFrame.setVisible(true);
    }
}
