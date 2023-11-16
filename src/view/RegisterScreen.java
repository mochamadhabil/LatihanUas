/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DatabaseController;
import controller.DateLabelFormatter;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.User;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author abil
 */
public class RegisterScreen {

    JFrame container;
    JTextField email;
    JTextField name;
    JPasswordField password;
    JRadioButton pria;
    JRadioButton wanita;
    JDatePickerImpl tanggalLahir;
    JPanel fotoPanel;
    JFileChooser foto;
    JButton btnRegistrasi;
    JButton btnBack;
    ArrayList<User> users = new ArrayList<>();

    public RegisterScreen() {
        container = new JFrame("Main Menu");
        container.setSize(400, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);

        //Bagian Email
        JLabel emailLabel = new JLabel("Email          : ");
        email = new JTextField();
        emailLabel.setBounds(30, 30, 150, 25);
        email.setBounds(130, 33, 230, 20);
        container.add(emailLabel);
        container.add(email);

        //Bagian Nama
        JLabel namaLabel = new JLabel("Nama         : ");
        name = new JTextField();
        namaLabel.setBounds(30, 50, 150, 25);
        name.setBounds(130, 55, 230, 20);
        container.add(namaLabel);
        container.add(name);

        //Bagian Password
        JLabel passwordLabel = new JLabel("Password : ");
        password = new JPasswordField();
        passwordLabel.setBounds(30, 70, 150, 25);
        password.setBounds(130, 75, 230, 20);
        container.add(passwordLabel);
        container.add(password);

        //Bagian Gender
        JLabel jenisKelamin = new JLabel("Jenis Kelamin : ");
        jenisKelamin.setBounds(30, 90, 150, 25);
        container.add(jenisKelamin);
        pria = new JRadioButton();
        pria.setText("(1). Laki-Laki");
        pria.setBounds(130, 95, 120, 20);
        wanita = new JRadioButton();
        wanita.setText("(2). Perempuan");
        wanita.setBounds(250, 95, 120, 20);
        container.add(pria);
        container.add(wanita);

        //Supaya dapat memilih salah satu 
        ButtonGroup groupJk = new ButtonGroup();
        groupJk.add(pria);
        groupJk.add(wanita);

        // GUI Library : Date
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir : ");
        labelTanggalLahir.setBounds(30, 110, 150, 25);
        container.add(labelTanggalLahir);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        tanggalLahir = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        tanggalLahir.setBounds(130, 115, 200, 30);
        container.add(tanggalLahir);

        //Bagian Foto
        JLabel pilihFoto = new JLabel("Foto   : ");
        pilihFoto.setBounds(30, 145, 150, 25);
        container.add(pilihFoto);

        JButton buttonChooseFoto = new JButton("Pilih Foto");
        buttonChooseFoto.setBounds(130, 145, 200, 25);
        container.add(buttonChooseFoto);

        JLabel fotoDipilih = new JLabel("Foto Dipilih : ");
        fotoDipilih.setBounds(430, 30, 300, 20);
        container.add(fotoDipilih);

        // pakai panel untuk menampilkan gambar
        fotoPanel = new JPanel();
        fotoPanel.setBounds(430, 50, 150, 200);
        container.add(fotoPanel);

        buttonChooseFoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFoto();
            }
        });

        //button Registrasi
        btnRegistrasi = new JButton("Registrasi");
        btnRegistrasi.setBounds(100, 200, 110, 25);
        container.add(btnRegistrasi);

        btnRegistrasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idValue = 0;
                String namaValue = name.getText();
                String emailValue = email.getText();
                String passValue = password.getText();

                // Pengecekan panjang password
                if (passValue.length() < 8) {
                    JOptionPane.showMessageDialog(container, "Password harus memiliki minimal 8 karakter.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Menghentikan proses registrasi jika password tidak memenuhi syarat
                }

                int genderValue = pria.isSelected() ? 1 : 2;
                Date tanggalLahirValue = new java.sql.Date(((java.util.Date) tanggalLahir.getModel().getValue()).getTime());

                User user = new User(idValue, namaValue, emailValue, passValue, genderValue, tanggalLahirValue);
                users.add(user);

                DatabaseController controller = new DatabaseController();
                controller.insertNewUser(user);

                container.setVisible(false);

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
                
                new MainMenu();
            }
        });

        //button back
        btnBack = new JButton("Back");
        btnBack.setBounds(230, 200, 110, 25);
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

    public void chooseFoto() {
        foto = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture Files", "jpg", "jpeg", "png");
        foto.setFileFilter(filter);

        int returnValue = foto.showOpenDialog(container); // buat nampilin dialog pemilihan file

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = foto.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            User.setPhoto(filePath);
            String fotoPath = User.getPhoto();
            if (fotoPath != null) {
                try {
                    BufferedImage img = ImageIO.read(new File(filePath));
                    ImageIcon imageIcon = new ImageIcon(img);
                    JLabel imageLabel = new JLabel(imageIcon);
                    imageLabel.setBounds(0, 0, img.getWidth(), img.getHeight());
                    // ini buat hapus komponen sebelumnya dari fotoPanel
                    fotoPanel.removeAll();
                    fotoPanel.revalidate(); // untuk nge-refresh fotoPanel
                    fotoPanel.repaint(); // untuk nge-repaint fotoPanel
                    fotoPanel.add(imageLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("null");
            }
        }
    }
}
