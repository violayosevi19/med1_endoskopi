/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.SettingDao;
import Model.AssesmentModel;
import Model.SettingModel;
import View.Setting;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class SettingController {
    Setting view;
    SettingDao dao;
    SettingModel model;
    
     public void insert() {
        try {
            String name = view.getTxtName().getText();
            String phone = view.getTxtPhone().getText();
            String pos = view.getTxtKodePos().getText();
            String tombol = view.getTxtTombol().getText();
            String address = view.getTxtAdress().getText();
            model = new SettingModel();
            model.setNama(name); 
            model.setPhone(phone);
            model.setKode_pos(pos);
            model.setTombol(tombol);
            model.setAlamat(address);
            
           JOptionPane.showMessageDialog(view, "Setting Berhasil!");
           dao.insert(model);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex);
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     public void clearAll() throws Exception {
         dao.ClearAll();
     }
     
}
