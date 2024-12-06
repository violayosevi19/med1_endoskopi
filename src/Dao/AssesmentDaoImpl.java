
package Dao;

import Config.Koneksi;
import Model.AssesmentModel;
import Model.ImagesModel;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;


public class AssesmentDaoImpl implements AssesmentDao{

    @Override
    public void insert(AssesmentModel assesment) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO assesment(nama,keluhan,diagnosis,gambar_satu,gambar_dua,gambar_tiga,gambar_empat,gambar_lima,gambar_enam,gambar_tujuh,gambar_delapan) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, assesment.getNama());
        ps.setString(2, assesment.getKeluhan());
        ps.setString(3, assesment.getDiagnosis());
        ps.setString(4, assesment.getGambar_satu());
        ps.setString(5, assesment.getGambar_dua());
        ps.setString(6, assesment.getGambar_tiga());
        ps.setString(7, assesment.getGambar_empat());
        ps.setString(8, assesment.getGambar_lima());
        ps.setString(9, assesment.getGambar_enam());
        ps.setString(10, assesment.getGambar_tujuh());
        ps.setString(11, assesment.getGambar_delapan());

        ps.executeUpdate();
        
    }

    @Override
    public List<AssesmentModel> getAllAssesment() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
