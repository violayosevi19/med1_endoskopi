
package Dao;

import Config.Koneksi;
import Model.RegisterUserModel;
import java.sql.*;

public class RegisterUserDaoImpl implements RegisterUsersDao{

    @Override
    public void insert(RegisterUserModel registerUsers) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO users(username,password,full_name) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, registerUsers.getUsername());
        ps.setString(2, registerUsers.getPassword());
        ps.setString(3, registerUsers.getFullName());
        
        ps.executeUpdate();
        
    }
    
}
