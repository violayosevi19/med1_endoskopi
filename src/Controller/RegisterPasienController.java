
package Controller;

import Dao.RegisterPasienDao;
import Dao.RegisterPasienDaoImpl;
import Model.RegisterPasienModel;
import View.RegisterPasien;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;

public class RegisterPasienController {
    RegisterPasienModel model;
    RegisterPasien view;
    RegisterPasienDao dao;
    
     public RegisterPasienController(RegisterPasien view){
        this.view = view;
        dao = new RegisterPasienDaoImpl();
    }
    
    public void clearForm() {
        view.getTxtNama().setText("");
        view.getTxtId().setText("");
        view.getTxtTgllahir().setDate(null);
        view.getRbtnPria().setSelected(false);
        view.getRbtnWanita().setSelected(false);
    }
    
    public void insert(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String id_pasien = view.getTxtId().getText();
            String nama_pasien = view.getTxtNama().getText();
            String tgl_lahir = sdf.format(view.getTxtTgllahir().getDate());
            String jenis_kelamin = view.getRbtnPria().isSelected() ? "Laki-Laki" : "Perempuan";
            
            model = new RegisterPasienModel();
            model.setId_pasien(id_pasien);
            model.setNama_pasien(nama_pasien);
            model.setTgl_lahir(tgl_lahir);
            model.setJenis_kelamin(jenis_kelamin);
            
            dao.insert(model);
            JOptionPane.showMessageDialog(view, "Registrasi Pasien Berhasil!");
            
        } catch (Exception e) {
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
}
