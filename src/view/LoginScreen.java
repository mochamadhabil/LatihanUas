/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author abil
 */
public class LoginScreen {

    JFrame container;
    JTextField email;
    JPasswordField password;
    JButton btnLogin;
    JButton btnBack;

    public LoginScreen() {
        container = new JFrame("Main Menu");
        container.setSize(400, 200);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);

        //Bagian Email
        JLabel emailLabel = new JLabel("Email          : ");
        email = new JTextField();
        emailLabel.setBounds(30, 30, 150, 25);
        email.setBounds(110, 33, 230, 20);
        container.add(emailLabel);
        container.add(email);

        //Bagian Passowrd
        JLabel passwordLabel = new JLabel("Password : ");
        password = new JPasswordField();
        passwordLabel.setBounds(30, 50, 150, 25);
        password.setBounds(110, 55, 230, 20);
        container.add(passwordLabel);
        container.add(password);

        //button login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(110, 100, 110, 25);
        container.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulasi data dari database
                String emailFromDatabase = "user@example.com";
                String passwordFromDatabase = "password123";

                String enteredEmail = email.getText();
                String enteredPassword = new String(password.getPassword());

                // Pengecekan ke database
                if (enteredEmail.equals(emailFromDatabase) && enteredPassword.equals(passwordFromDatabase)) {
                    // Jika email dan password ada di database, tampilkan Menu Homepage
                    new MainMenu();
                    container.setVisible(false);
                } else {
                    // Jika tidak cocok dengan data di database, tampilkan pesan kesalahan
                    JOptionPane.showMessageDialog(container, "Login failed. Incorrect email or password.");
                }
            }
        });

        //button back
        btnBack = new JButton("Back");
        btnBack.setBounds(230, 100, 110, 25);
        container.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();

                container.setVisible(false);
            }
        });

        container.setVisible(true);
    }
}
