/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hanschristian
 */
public class DatabaseController {

    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT ALL from table users
    public ArrayList<User> getAllPenduduk() {
        ArrayList<User> users = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM users";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nama"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                User.setPhoto(rs.getString("photo"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("MASUK CATCH GET ALL USERS : ");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("MASUK CATCH GET ALL USERS NULL : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("MASUK CATCH GET ALL USERS NULL : ");
            e.printStackTrace();
        }
        conn.disconnect();
        return (users);
    }

    // SELECT WHERE
    public static User getUser(String id) {
        conn.connect();
        String query = "SELECT * FROM users WHERE id='" + id + "'";
        User users = new User();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nama"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                User.setPhoto(rs.getString("photo"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    // INSERT
    public static boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getGender());
            stmt.setDate(6, new java.sql.Date(user.getBirthday().getTime()));
            stmt.setString(7, User.getPhoto());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //UPDATE
//    public boolean updatePenduduk(User user) {
//        conn.connect();
//        String query = "UPDATE penduduk SET Nama='" + penduduk.getNamaValue() + "', "
//                + "Tempat_Lahir='" + penduduk.getTempatLahirValue() + "', "
//                + "Tanggal_Lahir='" + new java.sql.Date(penduduk.getTanggalLahirValue().getTime()) + "', "
//                + "Gender='" + penduduk.getGenderValue() + "', "
//                + "Golongan_Darah='" + penduduk.getGolDarValue() + "', "
//                + "Alamat='" + penduduk.getAlamatValue() + "', "
//                + "RT_RW='" + penduduk.getRtRwValue() + "', "
//                + "KEL_Desa='" + penduduk.getKelDesaValue() + "', "
//                + "Kecamatan='" + penduduk.getKecamatanValue() + "', "
//                + "Agama='" + penduduk.getAgamaValue() + "', "
//                + "Status_Kawin='" + penduduk.getStatusKawinValue() + "', "
//                + "Pekerjaan='" + penduduk.getPekerjaanValue() + "', "
//                + "Kewarganegaraan='" + penduduk.getKewarganegaraanValue() + "', "
//                + "Berlaku_Hingga='" + penduduk.getBerlakuHinggaValue() + "', "
//                + "Kota_PembuatanKtp='" + penduduk.getKotaPembuatan() + "', "
//                + "Tanggal_PembuatanKtp='" + new java.sql.Date(penduduk.getTanggalPembuatanValue().getTime()) + "', "
//                + "pathFoto='" + Penduduk.getSelectedFotoPath() + "' "
//                + "pathTtd='" + Penduduk.getSelectedTtdPath() + "' "
//                + "WHERE NIK='" + penduduk.getNikValue() + "'";
//        try {
//            Statement stmt = conn.con.createStatement();
//            stmt.executeUpdate(query);
//            return (true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return (false);
//        }
//    }

    // DELETE
    public boolean deletePenduduk(User user) {
        conn.connect();

        String query = "DELETE FROM users WHERE id='" + user.getId() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
