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
import javax.swing.JOptionPane;

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
        ps.setString(6, settingModel.getTombol());

        ps.executeUpdate();
    }

    @Override
    public void ClearAll() throws Exception {
         Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "delete from setting";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeQuery();
        
        String sql2 = "delete from pasien";
        PreparedStatement ps2 = conn.prepareStatement(sql);
        ps2.executeQuery();
        
        String sql3 = "delete from assement";
        PreparedStatement ps3 = conn.prepareStatement(sql3);
        ps3.executeQuery();
        
        String sql4 = "delete from user where role!='operator'";
        PreparedStatement ps4 = conn.prepareStatement(sql4);
        ps4.executeQuery();
    }
    
}
