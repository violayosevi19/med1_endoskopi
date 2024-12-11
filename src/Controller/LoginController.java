
package Controller;

import Dao.LoginDao;
import Dao.LoginDaoImpl;
import Model.LoginModel;
import View.Assesment;
import View.Login;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


public class LoginController {
    Login view;
    LoginDao dao;
    LoginModel model;
    private static LoginModel currentUser;
    
    public LoginController(Login view){
        this.view = view;
        dao = new LoginDaoImpl();
    }
    
    public void login(){
         try {
            String username = view.getInputUsername().getText();
            String password =  new String(view.getInputPassword().getPassword());
            
            model = new LoginModel();
            model.setUsername(username); 
            model.setPassword(password);
            
           
            // Panggil DAO untuk login
//            LoginModel loggedInUser = dao.login(model);
            Map<String, Object> dataUser = dao.login(model);
            
            if (dataUser != null) {
//               currentUser = dataUser;
               JOptionPane.showMessageDialog(view, "Login Berhasil!");

               // Beralaih ke form berikutnya (misalnya dashboard)
               Assesment assesment = new Assesment(dataUser); // Ganti dengan view Anda
               assesment.setVisible(true);
               view.dispose(); // Menutup form login
            } else {
                JOptionPane.showMessageDialog(view, "Username atau Password salah!");
            }
        } catch (Exception ex) {
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static LoginModel getCurrentUser() {
        return currentUser; // Mengakses data user yang login
    }
    
    
    
}
