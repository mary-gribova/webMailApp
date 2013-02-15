package webMailApp.gui;

import webMailApp.dao.UserDAO;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private JFormattedTextField birthText;

    private JLabel phoneNameLabel;
    private JTextField phoneNameText;

    private JLabel passLabel;
    private JPasswordField passText;

    private JLabel addressLabel;
    private JTextField addressText;

    private JButton registerBut;

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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

        addressLabel = new JLabel("Address: ");
        gBC.gridx = 0;
        gBC.gridy = 40;
        this.add(addressLabel, gBC);

        addressText = new JTextField();
        gBC.gridx = 60;
        gBC.gridy = 40;
        this.add(addressText, gBC);

        birthLabel = new JLabel("Birth: ");
        gBC.gridx = 0;
        gBC.gridy = 60;
        this.add(birthLabel, gBC);


        birthText = new JFormattedTextField(format);
        MaskFormatter dateMask = null;
        try {
            dateMask = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateMask.install(birthText);
        gBC.gridx = 60;
        gBC.gridy = 60;
        this.add(birthText, gBC);

        phoneNameLabel = new JLabel("Phone: ");
        gBC.gridx = 0;
        gBC.gridy = 80;
        this.add(phoneNameLabel, gBC);


        phoneNameText = new JTextField();
        gBC.gridx = 60;
        gBC.gridy = 80;
        this.add(phoneNameText, gBC);

        passLabel = new JLabel("Pass: ");
        gBC.gridx = 0;
        gBC.gridy = 100;
        this.add(passLabel, gBC);

        passText = new JPasswordField();
        gBC.gridx = 60;
        gBC.gridy = 100;
        this.add(passText, gBC);

        registerBut = new JButton("Register");
        gBC.gridx = 15;
        gBC.gridy = 120;
        this.add(registerBut, gBC);

        registerBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userFirstName = getFirstNameText().getText();
                String userLastName = getLastNameText().getText();
                String userPhone = getPhoneNameText().getText();
                String userAddress = getAddressText().getText();
                String userPass = String.valueOf(getPassText().getPassword());
                Date userBirthDate = null;

                try {
                    userBirthDate = format.parse(getBirthText().getText());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

//                System.out.println("user: "  + userFirstName + ", " + userLastName);
//                System.out.println("user phone: " + userPhone);
//                System.out.println("user addr: " + userAddress);
//                System.out.println("user pass: " + userPass);
//                System.out.println("user birth: " + userBirthDate.toString());

                UserDAO userDAO = new UserDAO();
                if (userDAO.addUser(userFirstName, userLastName, userPass, userBirthDate, userPhone, userAddress))
                    System.out.println("Wuhhuuuuu!!! Success!!!!");

            }
        });
    }

    public JTextField getFirstNameText() {
        return firstNameText;
    }

    public JTextField getLastNameText() {
        return lastNameText;
    }

    public JTextField getBirthText() {
        return birthText;
    }

    public JTextField getPhoneNameText() {
        return phoneNameText;
    }

    public JPasswordField getPassText() {
        return passText;
    }

    public JTextField getAddressText() {
        return addressText;
    }
}
