/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Config.Koneksi;
import Dao.ImagesDaoImpl;
import Model.ImagesModel;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class EndoskopiModal extends javax.swing.JFrame {

    public static EndoskopiModal formInstance;
    private JTextField txtNama;
    private JTextArea txtKeluhan;
    private JTextArea txtDiagnosis;
    private Assesment assesmentForm;
    private String nama;
    private String keluhan;
    private String diagnosis;
    public List<String> imagePaths = new ArrayList<>();

    
    private boolean captureImage = false;
    CvCapture capture;
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    CanvasFrame frame = new CanvasFrame("Webcam");
    public EndoskopiModal() {
        initComponents();
        formInstance = this;

        CameraViewer(); 
    }

   // Setter untuk menerima data tanpa perlu menggunakan UI komponen
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public void CameraViewer(){
        try {

             frame.setCanvasSize(1200, 1000);
             frame.pack();
             frame.setLocationRelativeTo(null);
            // Menjalankan kamera dalam thread terpisah
             new Thread(() -> {
                try {
                    grabber.start(); // Mulai grabber kamera
                    while (frame.isVisible()) {
                        IplImage grabbedImage = grabber.grab(); // Ambil frame
                        if (grabbedImage != null) {
                            frame.showImage(grabbedImage); // Tampilkan frame ke CanvasFrame
                        }
                    }
                    grabber.stop(); // Hentikan grabber saat frame ditutup
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();


            // Menutup aplikasi saat jendela ditutup
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnTake = new javax.swing.JButton();
        previewImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnTake.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTake.setText("TAKE PICTURE");
        btnTake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTakeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(btnTake, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(previewImage, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnTake, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(previewImage, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private IplImage capturedStateImage; 
    Assesment assessmentForm = new Assesment();

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            if (capturedStateImage != null) {
                String folderPath = "images/";
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs(); // Buat folder jika belum ada
                }
                String fileName = "capture_" + System.currentTimeMillis() + ".jpeg";
                String filePath = folderPath + fileName;
                cvSaveImage(filePath, capturedStateImage);  // Menyimpan gambar ke file
                
                imagePaths.add(filePath);
                
                // Simpan path file ke database
//                ImagesModel imageModel = new ImagesModel();
//                imageModel.setAssesment_id(1);  // Isi dengan assesment_id yang sesuai
//                imageModel.setImage_path(filePath); // Path file yang sudah disimpan
//                
//                 // Insert image path ke database
//                ImagesDaoImpl imagesDao = new ImagesDaoImpl();
//                imagesDao.insertImages(imageModel);
                // Simpan path file ke database
//                saveImageToDatabase(filePath);

              
                

//                 SwingUtilities.invokeLater(() -> {
//                    if (assessmentForm != null) {
//                        assessmentForm.setPreviewImage(filePath);
//                        assessmentForm.showImage(filePath);
//                        assessmentForm.repaint();
//                    }
//                });
                     // Menutup form lama jika sudah ada (form lama kosong)
                    // Menutup form lama jika sudah ada
//                    if (Assesment.Instance != null) {
//                        System.out.println("Assesment.Instance tidak null");
//                        if (Assesment.Instance.isVisible()) {
//                            System.out.println("Form pertama terlihat.");
//                            SwingUtilities.invokeLater(() -> {
//                                Assesment.Instance.dispose();  // Menutup form pertama jika terlihat
//                                Assesment.Instance = null;  // Null-kan instance form lama agar tidak referensinya ada lagi
//                                System.out.println("Form Assessment pertama ditutup.");
//                            });
//                        } else {
//                            System.out.println("Form pertama tidak terlihat.");
//                        }
//                    } else {
//                        System.out.println("Assesment.Instance adalah null");
//                    }
//                    
//                    if (Assesment.Instance != null && !Assesment.Instance.isFocused()) {
//                        Assesment.Instance.setVisible(false); 
//                        System.out.println("Form pertama ada, tetapi tidak aktif atau tidak terlihat.");
//                    }

                   SwingUtilities.invokeLater(() -> {
                        if (Assesment.Instance == null) {
                            Assesment.Instance = new Assesment(); // Membuat form baru
                        }
                        if (Assesment.Instance != null && Assesment.Instance.lblImage != null) {
                            
                             // Periksa label mana yang kosong
//                            for (int i = 1; i <= 8; i++) {
//                                JLabel label = getLabelByIndex(i); // Dapatkan label berdasarkan index
//                                System.out.println("id" + i);
//
//                                // Jika label kosong (null), tampilkan gambar di sana
//                                if (label.getIcon() == null) {
//                                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(
//                                            label.getWidth(),
//                                            label.getHeight(),
//                                            Image.SCALE_SMOOTH
//                                    ));
//                                    label.setIcon(imageIcon);
//                                    label.setVisible(true);
//                                    break;  // Hentikan jika sudah menemukan label yang kosong
//                                }
//                            }

                            Assesment.Instance.getFilePath(imagePaths);
                            JLabel[] labels = {
                                Assesment.Instance.lblImage1,
                                Assesment.Instance.lblImage2,
                                Assesment.Instance.lblImage3,
                                Assesment.Instance.lblImage4,
                                Assesment.Instance.lblImage5,
                                Assesment.Instance.lblImage6,
                                Assesment.Instance.lblImage7,
                                Assesment.Instance.lblImage8
                            };

                            // Memastikan gambar muncul di label sesuai urutan yang benar
                            for (JLabel label : labels) {
                                if (label.getIcon() == null) {
                                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(
                                            label.getWidth(),
                                            label.getHeight(),
                                            Image.SCALE_SMOOTH
                                    ));
                                    label.setIcon(imageIcon);
                                    label.setVisible(true);
                                    break; // Hentikan setelah menemukan label kosong
                                }
                            }


                             // Memaksa ulang tata letak dan menggambar ulang
                            Assesment.Instance.lblImage.revalidate();
                            Assesment.Instance.lblImage.repaint();
                            Assesment.Instance.getContentPane().revalidate();
                            Assesment.Instance.getContentPane().repaint();

                            // Memastikan form terlihat
                            if (!Assesment.Instance.isVisible()) {
                                Assesment.Instance.setVisible(true);
                                Assesment.Instance.comboPasien.setSelectedItem(nama);
                                Assesment.Instance.inputKeluhan.setText(keluhan);
                                Assesment.Instance.inputDiagnosis.setText(diagnosis);
                                

                                for (String path : imagePaths) {
                                    System.out.println("Path baru ditambahkan for: " + path);  // Debugging log
                                    Assesment.Instance.getFilePath(imagePaths); // Kirim satu per satu ke Assessment
                                }
                            } else {
                                for (String path : imagePaths) {
                                    if (!imagePaths.contains(path)) {
                                        System.out.println("Path baru ditambahkan for else: " + path);  // Debugging log
                                        Assesment.Instance.getFilePath(imagePaths); // Kirim satu per satu ke Assessment
                                    }
                                }
                            }
                           
                        } else {
                            System.out.println("Instance Assesment atau lblImage tidak ditemukan.");
                        }
                    });


            } else {
                JOptionPane.showMessageDialog(this, "Tidak ada gambar yang diambil!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    private void btnTakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakeActionPerformed
        try {
            IplImage img = grabber.grab();
               if (img != null) {
                   capturedStateImage = img;
                   BufferedImage bufferedImage = convertToBufferedImage(img);

                    ImageIcon imageIcon = new ImageIcon(bufferedImage.getScaledInstance(
                                    previewImage.getWidth(),
                                    previewImage.getHeight(),
                                    Image.SCALE_SMOOTH
                                ));

                   // Set gambar ke JLabel
                   previewImage.setIcon(imageIcon);
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTakeActionPerformed

    private BufferedImage convertToBufferedImage(IplImage iplImage) {
        // Mendapatkan lebar dan tinggi gambar
        int width = iplImage.width();
        int height = iplImage.height();

        // Membaca channel (warna) dari IplImage ke dalam array byte
        byte[] data = new byte[width * height * 3];
        iplImage.getByteBuffer().get(data);

        // Membuat BufferedImage dengan tipe RGB
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Menyalin data byte ke dalam BufferedImage
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int blue = data[(y * width + x) * 3] & 0xFF;
                int green = data[(y * width + x) * 3 + 1] & 0xFF;
                int red = data[(y * width + x) * 3 + 2] & 0xFF;

                // Menyusun pixel ke dalam format RGB (Red, Green, Blue)
                int pixel = (red << 16) | (green << 8) | blue;
                bufferedImage.setRGB(x, y, pixel);
            }
        }

        return bufferedImage;
    }

    private JLabel getLabelByIndex(int index) {
        switch (index) {
            case 1: return Assesment.Instance.lblImage1;
            case 2: return Assesment.Instance.lblImage2;
            case 3: return Assesment.Instance.lblImage3;
            case 4: return Assesment.Instance.lblImage4;
            case 5: return Assesment.Instance.lblImage5;
            case 6: return Assesment.Instance.lblImage6;
            case 7: return Assesment.Instance.lblImage7;
            case 8: return Assesment.Instance.lblImage8;
            default: return null;
        }
    }
    
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        previewImage.setIcon(null);  // Hapus gambar di preview
        capturedStateImage = null;     
    }//GEN-LAST:event_btnResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EndoskopiModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EndoskopiModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EndoskopiModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EndoskopiModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EndoskopiModal().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTake;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel previewImage;
    // End of variables declaration//GEN-END:variables
}
