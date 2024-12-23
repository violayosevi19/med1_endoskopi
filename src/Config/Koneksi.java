
package Config;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Koneksi {
//    public static void main(String[] args) {
//        // URL, username, dan password MySQL
//        String url = "jdbc:mysql://localhost:3307/db_endoskopi"; // Sesuaikan nama database
//        String username = "root"; // Default username
//        String password = ""; // Kosong jika tidak ada password
//
//        try {
//            // Membuat koneksi ke database
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Koneksi ke database berhasil!");
//            connection.close(); // Tutup koneksi
//        } catch (SQLException e) {
//            System.out.println("Koneksi gagal!");
//            e.printStackTrace();
//        }
//    }
    private static Connection conn;
    
    public static Connection getConnection(){
        if(conn == null){
            try {
//                String jdbcUrl = "jdbc:sqlite:D:\\Viola Yosevi\\Application\\SQLite\\sqlite\\db_endoskopi.db";
//                conn = (Connection) DriverManager.getConnection(jdbcUrl);
//                String sql = "SELECT * FROM pasiens";
//                Statement st = conn.createStatement();
//                
//                ResultSet rs = st.executeQuery(sql);
//                while(rs.next()){
//                    String name = rs.getString("nama");
//                    
//                    System.out.println("this is data " +  name);
//                }
                String url = "jdbc:mysql://localhost:3307/db_endoskopi";
                String user = "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conn = (Connection) DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi ke database berhasil!");
            } catch (Exception e) {
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return conn;
    }
   
    
   
    
}
