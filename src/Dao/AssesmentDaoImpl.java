
package Dao;

import Config.Koneksi;
import Model.AssesmentModel;
import Model.ImagesModel;
import java.util.List;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.*;


public class AssesmentDaoImpl implements AssesmentDao{

    @Override
    public void insert(AssesmentModel assesment) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO assesment(nama,keluhan,diagnosis) VALUES (?,?,?)";
        System.out.println("sql" +sql);
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, assesment.getNama());
        ps.setString(2, assesment.getKeluhan());
        ps.setString(3, assesment.getDiagnosis());
        ps.executeUpdate();
        
//        ResultSet rs = ps.getGeneratedKeys();
//        System.out.println("ini result" +rs);
//        int assesmentId = 0;
//            if (rs.next()) {
//                assesmentId = rs.getInt(1); // Ambil ID dari entry yang baru saja disimpan
//                System.out.println("ini jalan" +assesmentId);
//
//        }
//             // 2. Insert data gambar-gambar yang terkait dengan assesment
//        String sqlImage = "INSERT INTO assesment_images(assesment_id, image_path) VALUES (?, ?)";
//        PreparedStatement psImage = conn.prepareStatement(sqlImage);
//
//            // Loop untuk menyimpan path gambar
//        for (String imagePath : assesment.getImagePaths()) {
//                psImage.setInt(1, assesmentId);  // Set ID assesment
//                psImage.setString(2, imagePath); // Set path gambar
//                psImage.addBatch(); // Menambahkan ke batch
//        }
//
//         // Eksekusi batch untuk menyimpan gambar
//        psImage.executeBatch();
//        conn.commit();
    }

    @Override
    public List<AssesmentModel> getAllAssesment() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void saveAssessmentImages(int assesmentId, List<String> imagePaths, Connection conn) throws SQLException {
        String sqlImage = "INSERT INTO assesment_images(assesment_id, image_path) VALUES (?, ?)";
        PreparedStatement psImage = conn.prepareStatement(sqlImage);

        // Loop untuk menyimpan path gambar
        for (String imagePath : imagePaths) {
            psImage.setInt(1, assesmentId);  // Set ID assesment
            psImage.setString(2, imagePath); // Set path gambar
            psImage.addBatch(); // Menambahkan ke batch
        }

        // Eksekusi batch untuk menyimpan gambar
        psImage.executeBatch();
    }
    
}
