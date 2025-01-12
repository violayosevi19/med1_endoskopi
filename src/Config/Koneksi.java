package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() {
        // Hanya membuat koneksi jika belum ada
        if (conn == null) {
            try {
                // URL koneksi SQLite
                String jdbcUrl = "jdbc:sqlite:src\\config\\db_endoskopi.db";
                
                conn = DriverManager.getConnection(jdbcUrl);
//
                 //Membuat koneksi ke database SQLite
//                conn = DriverManager.getConnection(jdbcUrl);
//                String url = "jdbc:mysql://localhost:3307/db_endoskopi";
//                String user = "root";
//                String pass = "";
//                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//                conn = (Connection) DriverManager.getConnection(url, user, pass);
//                
                System.out.println("Koneksi ke database berhasil!");
            } catch (SQLException e) {
                // Logging jika terjadi error
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, "Koneksi ke database gagal!", e);
            }
        }
        return conn; // Mengembalikan koneksi yang telah dibuat
    }
}
