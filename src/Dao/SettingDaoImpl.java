/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Koneksi;
import Model.SettingModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class SettingDaoImpl implements SettingDao{

    @Override
    public void insert(SettingModel settingModel) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO assesments(nama,phone,kode_pos,logo,alamat,tombol) "
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, settingModel.getNama());
        ps.setString(2, settingModel.getPhone());
        ps.setString(3, settingModel.getKode_pos());
        ps.setString(4, settingModel.getLogo());
        ps.setString(5, settingModel.getAlamat());
        ps.setString(5, settingModel.getTombol());

        ps.executeUpdate();
    }
    
}
