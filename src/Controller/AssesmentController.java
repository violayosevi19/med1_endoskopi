/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.AssesmentDao;
import Dao.AssesmentDaoImpl;
import Model.AssesmentModel;
import View.Assesment;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author user
 */
public class AssesmentController {
    Assesment view;
    AssesmentModel model;
    AssesmentDao dao;
    
    public AssesmentController(Assesment view){
        this.view = view;
        dao = new AssesmentDaoImpl();
    }
    
    public void clearForm() {
        view.getTxtNama().setText("");
        view.getTxtKeluhan().setText("");
        view.getTxtDiagnosis().setText("");
    }
    
     public void insert() {
        try {
            String nama = view.getTxtNama().getText();
            String keluhan =  view.getTxtKeluhan().getText();
            String diagnosis =  view.getTxtDiagnosis().getText();
            
            List<String> imagePaths = view.filePaths;
            
            model = new AssesmentModel();
            model.setNama(nama); 
            model.setKeluhan(keluhan);
            model.setDiagnosis(diagnosis);
            
            model.setGambar_satu(imagePaths.size() > 0 ? imagePaths.get(0) : null);
            model.setGambar_dua(imagePaths.size() > 1 ? imagePaths.get(1) : null);
            model.setGambar_tiga(imagePaths.size() > 2 ? imagePaths.get(2) : null);
            model.setGambar_empat(imagePaths.size() > 3 ? imagePaths.get(3) : null);
            model.setGambar_lima(imagePaths.size() > 4 ? imagePaths.get(4) : null);
            model.setGambar_enam(imagePaths.size() > 5 ? imagePaths.get(5) : null);
            model.setGambar_tujuh(imagePaths.size() > 6 ? imagePaths.get(6) : null);
            model.setGambar_delapan(imagePaths.size() > 7 ? imagePaths.get(7) : null);
            dao.insert(model);
            JOptionPane.showMessageDialog(view, "Entry data ok");
        } catch (Exception ex) {
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
    
   
    
    
}
