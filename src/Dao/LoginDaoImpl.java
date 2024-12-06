
package Dao;

import Config.Koneksi;
import Model.LoginModel;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.*;

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
    
    // Fungsi untuk meng-hash password (contoh menggunakan SHA-256)
    private String hashPassword(String password) {
        try {
            // Menggunakan SHA-256 untuk hashing password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString(); // Mengembalikan hasil hash dalam bentuk string hexadecimal
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }
    
   
    
}
