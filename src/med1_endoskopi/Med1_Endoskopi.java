/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package med1_endoskopi;

import Config.Koneksi;
import View.Encrypt;
import View.Login;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;



/**
 *
 * @author user
 */
public class Med1_Endoskopi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            // Membaca file data.txt
            File file = new File("data.txt");
            boolean openEncrypt = false;

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine(); // Membaca baris pertama
                if (line != null && line.trim().equalsIgnoreCase("true")) {
                    openEncrypt = true; // Tentukan kondisi berdasarkan isi file
                }
                reader.close();
            } else {
                System.out.println("File data.txt tidak ditemukan. Default ke Login.");
            }

            // Kondisi berdasarkan isi file
//            if (!openEncrypt) {
//                Encrypt enc = new Encrypt();
//                enc.setVisible(true);
//                enc.pack();
//                enc.setLocationRelativeTo(null);
//            } else {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                loginFrame.pack();
                loginFrame.setLocationRelativeTo(null);
//            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error membaca file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
        
        
    
}
