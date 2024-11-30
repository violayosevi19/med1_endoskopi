
package Dao;

import Config.Koneksi;
import Model.AssesmentModel;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AssesmentDaoImpl implements AssesmentDao{

    @Override
    public void insert(AssesmentModel assesment) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO assesment(nama,keluhan,diagnosis) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, assesment.getNama());
        ps.setString(2, assesment.getKeluhan());
        ps.setString(3, assesment.getDiagnosis());
        ps.executeUpdate();

    }

    @Override
    public List<AssesmentModel> getAllAssesment() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
