
package Dao;

import Config.Koneksi;
import Model.LoginModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.*;
import java.util.Base64;

public class LoginDaoImpl implements LoginDao{

    @Override
    public boolean login(LoginModel login) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, login.getUsername());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String storedPasswordHash = rs.getString("password");
            
            System.out.println("masuk" +storedPasswordHash);
            // Verifikasi password
            return checkPassword(login.getPassword(), storedPasswordHash);
        } else {
            return false; // Username tidak ditemukan
        }
    }
    
      // Fungsi untuk memverifikasi password yang diinputkan dengan yang ada di database
    private boolean checkPassword(String inputPassword, String storedPasswordHash) {
        // Hash password yang dimasukkan pengguna menggunakan metode yang sama dengan yang digunakan saat menyimpan password
        String inputPasswordHash = hashPassword(inputPassword);

        // Bandingkan password yang di-hash
        return inputPasswordHash.equals(storedPasswordHash);
    }
   
    
    private String hashPassword(String password) {
        try {
            // Membuat objek MessageDigest untuk algoritma SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Mengubah password menjadi array byte dan melakukan hashing
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Mengonversi byte array menjadi string base64 (agar mudah disimpan di database)
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
   
    
}
