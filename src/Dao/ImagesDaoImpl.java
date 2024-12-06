/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Koneksi;
import Model.ImagesModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class ImagesDaoImpl implements ImagesDao{
    @Override
    public void insertImages(ImagesModel images) throws Exception {
        Koneksi koneksi = new Koneksi();
        Connection conn = koneksi.getConnection();
        String sql = "INSERT INTO assesment_images (assesment_id, image_path) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, images.getAssesment_id());
        ps.setString(2, images.getImage_path());
        ps.executeUpdate();
    }
}
