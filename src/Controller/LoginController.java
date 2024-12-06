
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


public class LoginController {
    Login view;
    LoginDao dao;
    LoginModel model;
    
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
            
           
            boolean loginSuccessful = dao.login(model);
            
            if (loginSuccessful) {
               JOptionPane.showMessageDialog(view, "Login Berhasil!");

               // Beralih ke form berikutnya (misalnya dashboard)
               Assesment assesment = new Assesment(null); // Ganti dengan view Anda
               assesment.setVisible(true);
               view.dispose(); // Menutup form login
            } else {
                JOptionPane.showMessageDialog(view, "Username atau Password salah!");
            }
        } catch (Exception ex) {
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
