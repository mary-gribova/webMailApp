package webMailApp.gui;

import webMailApp.dao.UserDAO;
import webMailApp.gui.mailBox.MailBoxFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 12.02.13
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */

public class AutentificationFrame extends JFrame {
    private JButton registerBut;
    private JButton loginBut;
    private JLabel passLabel;
    private JLabel loginLabel;
    private JTextField loginField;
    private JPasswordField passField;

    public AutentificationFrame() {
        super("Autentification");
        this.setBounds(150, 150, 400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.HORIZONTAL;
        gBC.insets = new Insets(5, 5, 5, 5);


        loginLabel = new JLabel("Login: ");
        gBC.gridx = 0;
        gBC.gridy = 0;
        this.add(loginLabel, gBC);

        loginField = new JTextField();
        gBC.gridwidth = 20;
        gBC.gridx = 20;
        gBC.gridy = 0;
        this.add(loginField, gBC);

        passLabel = new JLabel("Pass: ");
        gBC.gridx = 0;
        gBC.gridy = 15;
        this.add(passLabel, gBC);


        passField = new JPasswordField();
        gBC.gridx = 20;
        gBC.gridy = 15;
        this.add(passField, gBC);

        loginBut = new JButton("Log in");
        gBC.gridx = 10;
        gBC.gridy = 30;
        this.add(loginBut, gBC);


        registerBut = new JButton("Register");
        gBC.gridx = 10;
        gBC.gridy = 40;
        gBC.insets = new Insets(5, 5, 5, 5);
        this.add(registerBut, gBC);

        registerBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationFrame().setVisible(true);
                getFrame().setVisible(false);
            }
        });

        loginBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = loginField.getText().toString();
                String password = String.valueOf(passField.getPassword());

                String sessionID = new UserDAO().login(login, password);

                if (sessionID != null && !sessionID.equals("")) {
                    MailBoxFrame.showMailBoxFrame(sessionID);
                    getFrame().setVisible(false);
                }  else {
                    JOptionPane.showMessageDialog(getFrame(), "Incorrect login/password or you need to register");
                }


            }
        });
    }

    private JFrame getFrame() {
       return this;
    }

}
