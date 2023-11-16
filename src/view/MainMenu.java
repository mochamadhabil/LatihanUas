/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author abil
 */
public class MainMenu {

    JFrame container;
    JButton btnLogin;
    JButton btnRegistrasi;

    public MainMenu() {
        container = new JFrame("Main Menu");
        container.setSize(250, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);

        //Bagian Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(62, 25, 110, 25);
        container.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();

                container.setVisible(false);
            }
        });
        
        //Bagian Button Pencarian
        btnRegistrasi = new JButton("Registrasi");
        btnRegistrasi.setBounds(62, 60, 110, 25);
        container.add(btnRegistrasi);

        btnRegistrasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen();

                container.setVisible(false);
            }
        });
        container.setVisible(true);
    }
}
