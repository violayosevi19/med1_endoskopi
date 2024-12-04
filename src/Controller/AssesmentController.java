/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.AssesmentDao;
import Dao.AssesmentDaoImpl;
import Model.AssesmentModel;
import View.Assesment;
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
            
            model = new AssesmentModel();
            model.setNama(nama); 
            model.setKeluhan(keluhan);
            model.setDiagnosis(diagnosis);
            dao.insert(model);
            JOptionPane.showMessageDialog(view, "Entry data ok");
        } catch (Exception ex) {
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
    
   
    
    
}
