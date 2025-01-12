/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Config.Koneksi;
import Dao.AssesmentDao;
import Dao.AssesmentDaoImpl;
import Model.AssesmentModel;
import View.Assesment;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
        view.getComboBoxNama().setSelectedItem(null);
        view.getTxtKeluhan().setText("");
        view.getTxtDiagnosis().setText("");
    }
    
     public void insert() {
        try {
            String selectedNama = (String) view.getComboBoxNama().getSelectedItem();
            int idPasien = pasienMap.get(selectedNama);  
            String keluhan =  view.getTxtKeluhan().getText();
            String diagnosis =  view.getTxtDiagnosis().getText();
            
            List<String> imagePaths = view.imagePaths;
            Object data = view.currentUser.get("id");
            int userId = (int) data; 
            
            model = new AssesmentModel();
            model.setNama(selectedNama); 
            model.setKeluhan(keluhan);
            model.setDiagnosis(diagnosis);
            model.setId_user(userId);
            model.setId_pasien(idPasien);


            
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
     
     private Map<String, Integer> pasienMap = new HashMap<>();
      public void getPasiens() {
        try {
            // Ambil daftar nama dari database
            List<Object[]> pasiens = dao.getPasien();
//            System.out.println("Data Pasiens " + pasiens.get(0));
            

            // Isi data ke JComboBox
            for (Object[] pasien : pasiens) {
                int id = (int) pasien[0];
                String nama = (String) pasien[1];
            
                view.getComboBoxNama().addItem(nama); // Menambahkan item ke JComboBox
                pasienMap.put(nama, id);
                  
               
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      
      public void printFile(){
        try {
            Koneksi koneksi = new Koneksi();
            Connection conn = koneksi.getConnection();
            InputStream jasperInputStream = getClass().getClassLoader().getResourceAsStream("Report//LaporanEndoskopi.jasper");
            String file = "src/Report/LaporanEndoskopi.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            String baseDir = System.getProperty("user.dir") + "\\";
            String pathLogo = System.getProperty("user.dir") + "\\logo\\";
            parameters.put("BASE_DIR", baseDir);
            parameters.put("PATH_LOGO", pathLogo);
            if (conn != null) {
                String sqlQuery = "SELECT \n" +
                                "    UPPER(setting.nama_rs) AS nama_rs,\n" +
                                "DATE(pasiens.tgl_lahir) AS tgl_lahir ,\n"+
                                "    assesments.*,\n" +
                                "    users.*,\n" +
                                "    pasiens.*, setting.*\n" +
                                "FROM assesments\n" +
                                "JOIN users \n" +
                                "    ON assesments.id_user = users.id \n" +
                                "JOIN pasiens \n" +
                                "    ON assesments.pasien_id = pasiens.id, setting\n" +
                                "ORDER BY assesments.id DESC\n" +
                                "LIMIT 1";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                
                System.out.println(rs);
           
                // Menggunakan JRResultSetDataSource untuk memberikan ResultSet ke JasperReports
                JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(rs);

                JasperPrint print = JasperFillManager.fillReport(jasperInputStream, parameters, jrResultSetDataSource);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setVisible(true);
            } else {
                System.out.println("Database connection is null.");
            }

        } catch (Exception e) {
            System.out.println("message : " +e.getMessage());
            e.printStackTrace();
        }
    }
      
     
    
   
    
    
}
