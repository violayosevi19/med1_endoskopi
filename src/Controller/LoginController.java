package Controller;

import Dao.LoginDao;
import Dao.LoginDaoImpl;
import Model.LoginModel;
import View.Assesment;
import MainView.Login;
import MainView.MenuNew;
import View.MenuEndoskopi;
import View.Setting;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class LoginController {

//    Login view;
    Login mainView;
    LoginDao dao;
    LoginModel model;
    private static LoginModel currentUser;

    public LoginController(Login mainView) {
        this.mainView = mainView;
        dao = new LoginDaoImpl();
    }

    public void login() {
        try {
            String username = mainView.getTxtUsername().getText();
            String password = new String(mainView.getTxtPassword().getPassword());

            model = new LoginModel();
            model.setUsername(username);
            model.setPassword(password);
            System.out.println(password);
            if (username.equals("med1") && password.equals("$mediutama")) {
                JOptionPane.showMessageDialog(mainView, "Login Berhasil!");
                  mainView.getjPanel3().removeAll();
                  mainView.getjPanel3().add(new MenuNew());
                  mainView.getjPanel3().repaint();
                  mainView.getjPanel3().revalidate();
            } else {
                Map<String, Object> dataUser = dao.login(model);

                if (dataUser != null) {
//                    Map<String, Object> currentUser =  dataUser;
                    
                    JOptionPane.showMessageDialog(mainView, "Login Berhasil!");
                    mainView.getjPanel3().removeAll();
                    mainView.getjPanel3().add(new Assesment());
                    mainView.getjPanel3().repaint();
                    mainView.getjPanel3().revalidate();
                  } else {
                    JOptionPane.showMessageDialog(mainView, "Username atau Password salah!");
                }
            }

            // Panggil DAO untuk login
//            LoginModel loggedInUser = dao.login(model);
        } catch (Exception ex) {
            Logger.getLogger(AssesmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static LoginModel getCurrentUser() {
        return currentUser; // Mengakses data user yang login
    }

}
