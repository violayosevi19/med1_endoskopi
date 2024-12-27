
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
import java.util.ArrayList;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class AssesmentDaoImpl implements AssesmentDao{

    @Override
    public void insert(AssesmentModel assesment) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO assesments(keluhan,diagnosis,gambar_satu,gambar_dua,gambar_tiga,gambar_empat,gambar_lima,gambar_enam,gambar_tujuh,gambar_delapan,id_user, pasien_id) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, assesment.getKeluhan());
        ps.setString(2, assesment.getDiagnosis());
        ps.setString(3, assesment.getGambar_satu());
        ps.setString(4, assesment.getGambar_dua());
        ps.setString(5, assesment.getGambar_tiga());
        ps.setString(6, assesment.getGambar_empat());
        ps.setString(7, assesment.getGambar_lima());
        ps.setString(8, assesment.getGambar_enam());
        ps.setString(9, assesment.getGambar_tujuh());
        ps.setString(10, assesment.getGambar_delapan());
        ps.setInt(11, assesment.getId_user());
        ps.setInt(12, assesment.getId_pasien());

        ps.executeUpdate();
        
    }

    @Override
    public List<Object[]> getPasien() throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "SELECT * FROM pasiens";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
//        List<String> list = new ArrayList<>();
        List<Object[]> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nama = rs.getString("nama");
            list.add(new Object[]{id,nama}); // Ambil nama dari kolom "nama"
        }

        return list; // Kembalikan daftar nama
    }
    
    
    
    
    
}
