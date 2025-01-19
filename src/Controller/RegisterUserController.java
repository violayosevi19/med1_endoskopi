
package Controller;

import Dao.RegisterUserDaoImpl;
import Dao.RegisterUsersDao;
import MainView.RegisterUserNew;
import Model.RegisterUserModel;
//import View.Login;
//import View.RegisterUser;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class RegisterUserController {

    RegisterUserNew mainView;
    RegisterUserModel model;
    RegisterUsersDao dao;
    
    public RegisterUserController(RegisterUserNew mainView){
        this.mainView = mainView;
        dao = new RegisterUserDaoImpl();
    }
    
    public void insert() {
        try {
            String username = mainView.getTxtUsername().getText();
            String password =  new String(mainView.getTxtPassword().getPassword());
            String fullName =  mainView.getTxtFullname().getText();
            String hashedPassword = hashPassword(password);
            
            model = new RegisterUserModel();
            model.setUsername(username); 
            model.setPassword(hashedPassword);
            model.setFullName(fullName);
            
           
            dao.insert(model);
            JOptionPane.showMessageDialog(mainView, "Registrasi Berhasil!");
            
            // Menutup atau menyembunyikan form pendaftaran
            mainView.setVisible(false);  // Jika view adalah form pendaftaran

            // Membuka kembali form login
            Login loginForm = new Login();  // Misalnya Anda memiliki kelas LoginForm
            loginForm.setVisible(true);  // Menampilkan form login
        } catch (Exception ex) {
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String hashPassword(String password) {
        try {
            // Membuat objek MessageDigest untuk algoritma SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Mengubah password menjadi array byte dan melakukan hashing
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Mengonversi byte array menjadi string base64 (agar mudah disimpan di database)
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    
}
