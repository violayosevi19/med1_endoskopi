
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
import java.util.HashMap;
import java.util.Map;

public class LoginDaoImpl implements LoginDao{

    @Override
    public Map<String, Object> login(LoginModel login) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, login.getUsername());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String storedPasswordHash = rs.getString("password");
        
            // Verifikasi password
//            return checkPassword(login.getPassword(), storedPasswordHash);
// Verifikasi password
        if (checkPassword(login.getPassword(), storedPasswordHash)) {
            // Buat objek LoginModel dan isi dengan data pengguna
//            LoginModel user = new LoginModel();
            Map<String, Object> dataUser = new HashMap<>();
//            user.setId(rs.getInt("id")); // Ambil kolom "id"
//            user.setUsername(rs.getString("username")); // Ambil kolom "username"
//            user.setFullName(rs.getString("full_name")); // Ambil kolom "full_name"
            dataUser.put("id", rs.getInt("id")); // Ambil kolom "id"
            dataUser.put("username", rs.getString("username")); // Ambil kolom "username"
            dataUser.put("fullName", rs.getString("full_name")); // Ambil kolom "full_name"
            // Tambahkan kolom lain sesuai kebutuhan

            return dataUser; // Kembalikan objek LoginModel
        }
        } else {
            return null; // Username tidak ditemukan
        }
         return null; // Login gagal
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
