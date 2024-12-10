
package Dao;

import Config.Koneksi;
import Model.RegisterPasienModel;
import java.sql.*;


public class RegisterPasienDaoImpl implements RegisterPasienDao{

    @Override
    public void insert(RegisterPasienModel registerPasien) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO pasiens(id_pasien, nama, tgl_lahir, jenis_kelamin) VALUES(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, registerPasien.getId_pasien());
        ps.setString(2, registerPasien.getNama_pasien());
        ps.setString(3, registerPasien.getTgl_lahir());
        ps.setString(4, registerPasien.getJenis_kelamin());
        
        ps.executeUpdate();
                
    }
    
}
