
package View;

import Controller.AssesmentController;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Assesment extends javax.swing.JFrame {
    
    public static Assesment Instance;
    public JLabel lblImage, lblTest2, lblImage1, lblImage2, lblImage3, lblImage4, lblImage5, lblImage6, lblImage7, lblImage8;
    public JTextField txt, inputNama;
    public JTextArea inputKeluhan, inputDiagnosis;
    
    AssesmentController controller;
    private boolean captureImage = false;
    CvCapture capture;
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//    CanvasFrame frame = new CanvasFrame("Webcam");

 
    public Assesment(String filePath) {
        initComponents();
        controller = new AssesmentController(this);
//        controller.viewTable();
//        controller.listNama(); 

        Instance = this;
        lblImage = previewImage;
        lblImage1 = imageSatu;
        lblImage2 = imageDua;
        lblImage3 = imageTiga;
        lblImage4 = imageEmpat;
        lblImage5 = imageLima;
        lblImage6 = imageEnam;
        lblImage7 = imageTujuh;
        lblImage8 = imageDelapan;
        inputNama = txtNama;
        inputKeluhan = txtKeluhan;
        inputDiagnosis = txtDiagnosis;
        

//        lblTest2 = previewImageTest;
        txt = txtInput;
        getImagePaths();
        
//        testImage();            
//        showImage("capture_test.jpeg");  // Tampilkan gambar default atau kosong

        
        // Menampilkan gambar berdasarkan filePath
//        System.out.println(filePath);
//
//        if (filePath != null && !filePath.isEmpty()) {
//            System.out.println("ga kosong");
//            showImage(filePath);  // Menampilkan gambar sesuai dengan filePath
//        } else {
//            System.out.println("kosong");
//            showImage(null);  // Tampilkan gambar default atau kosong
//        }
        
    }
    
    public void updateData(String text, String newData) {
        lblTest2.setText(text);
        txt.setText(newData);
    }

    public JButton getBtnSavePrint() {
        return btnSavePrint;
    }

    public void setBtnSavePrint(JButton btnSavePrint) {
        this.btnSavePrint = btnSavePrint;
    }

  
    public JTextArea getTxtDiagnosis() {
        return txtDiagnosis;
    }

    public void setTxtDiagnosis(JTextArea txtDiagnosis) {
        this.txtDiagnosis = txtDiagnosis;
    }

    public JTextArea getTxtKeluhan() {
        return txtKeluhan;
    }

    public void setTxtKeluhan(JTextArea txtKeluhan) {
        this.txtKeluhan = txtKeluhan;
    }

    public JTextField getTxtNama() {
        return txtNama;
    }

    public void setTxtNama(JTextField txtNama) {
        this.txtNama = txtNama;
    }

    public void setPreviewImage(String filePath) {
        if (filePath != null) {
            showImage(filePath); // Menampilkan gambar di JLabel
        }
    }
    
        public void testImage(){
        File imageFile = new File("D:/Viola Yosevi/Java Projects/Med1_Endoskopi/capture_test.jpeg");
        ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
//        previewImageTest.setIcon(imageIcon);

    }
    
    
    // Setter untuk gambar, jika perlu
    public void setImage(String filePath) {
        System.out.println("ini filePath dri endoskopi ya" + filePath);
        // Atur gambar ke label yang sesuai
        JLabel[] labels = {
            lblImage1, lblImage2, lblImage3, lblImage4, lblImage5, lblImage6, lblImage7, lblImage8
        };

        for (JLabel label : labels) {
            if (label.getIcon() == null) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(
                        label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH
                ));
                imageIcon.setDescription(filePath); 
                label.setIcon(imageIcon);
                label.setVisible(true);
                break;  // Hentikan setelah menemukan label kosong
            }
        }
    }
    
    
    

    public List<String> getImagePaths() {
        List<String> paths = new ArrayList<>();

        JLabel[] labels = {lblImage1, lblImage2, lblImage3, lblImage4, lblImage5, lblImage6, lblImage7, lblImage8};

        for (JLabel label : labels) {
            if (label.getIcon() != null) {
                ImageIcon icon = (ImageIcon) label.getIcon();
                String imagePath = icon.getDescription(); // Ambil path gambar dari ImageIcon
                if (imagePath != null) {
                    paths.add(imagePath);
                }
            }
        }
         // Print out the image paths for debugging
        System.out.println("Image Paths: " + paths);

        return paths;
    }

    
    public void showImage(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            File imageFile = new File("D:/Viola Yosevi/Java Projects/Med1_Endoskopi/" + filePath);
            if (imageFile.exists()) {
                ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                previewImage.setIcon(imageIcon);
                previewImage.revalidate();
                previewImage.repaint();
                System.out.println("Gambar berhasil ditampilkan.");
            } else {
                System.out.println("Gambar tidak ditemukan: " + imageFile.getAbsolutePath());
            }
        } else {
            System.out.println("Path gambar tidak valid: " + filePath);
        }
       
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeluhan = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnosis = new javax.swing.JTextArea();
        btnOpenEndoskopi = new javax.swing.JButton();
        btnSavePrint = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnTake = new javax.swing.JButton();
        txtInput = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        previewImage = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        imageDua = new javax.swing.JLabel();
        imageEmpat = new javax.swing.JLabel();
        imageSatu = new javax.swing.JLabel();
        imageTiga = new javax.swing.JLabel();
        imageEnam = new javax.swing.JLabel();
        imageDelapan = new javax.swing.JLabel();
        imageLima = new javax.swing.JLabel();
        imageTujuh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ASSESMENT");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Diagnosis");

        txtKeluhan.setColumns(20);
        txtKeluhan.setRows(5);
        jScrollPane1.setViewportView(txtKeluhan);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Keluhan");

        txtDiagnosis.setColumns(20);
        txtDiagnosis.setRows(5);
        jScrollPane2.setViewportView(txtDiagnosis);

        btnOpenEndoskopi.setText("Open Endoskopi");
        btnOpenEndoskopi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenEndoskopiActionPerformed(evt);
            }
        });

        btnSavePrint.setText("Save & Print");
        btnSavePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePrintActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnTake.setText("jButton1");
        btnTake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTakeActionPerformed(evt);
            }
        });

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSavePrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOpenEndoskopi, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTake, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOpenEndoskopi)
                    .addComponent(btnTake))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk))
                .addGap(18, 18, 18)
                .addComponent(btnSavePrint)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(795, 900));

        imageDua.setText("jLabel5");

        imageEmpat.setText("jLabel5");

        imageSatu.setText("jLabel5");

        imageTiga.setText("jLabel5");

        imageEnam.setText("jLabel5");

        imageDelapan.setText("jLabel5");

        imageLima.setText("jLabel5");

        imageTujuh.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(imageLima, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imageTiga, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageTujuh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imageDua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageDelapan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageEnam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageEmpat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(300, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageDua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageEmpat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageTiga, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageEnam, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageLima, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageTujuh, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageDelapan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(previewImage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(previewImage, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnOpenEndoskopiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenEndoskopiActionPerformed
        String nama = txtNama.getText();
        String keluhan = txtKeluhan.getText();
        String diagnosis = txtDiagnosis.getText();
        
        EndoskopiModal modal = new EndoskopiModal();
        modal.setNama(nama);
        modal.setKeluhan(keluhan);
        modal.setDiagnosis(diagnosis);
        modal.setVisible(true);
//        IplImage image = cvLoadImage("C:\\Users\\user\\Pictures\\Screenshots\\Screenshot 2024-11-25 135419.png");
//        final CanvasFrame canvas = new CanvasFrame("Demo");
//        
//        canvas.showImage(image);
//        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
               
//            if (capture == null) {
//                capture = opencv_highgui.cvCreateCameraCapture(0);
//                opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 720);
//                opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 1280);
//
//                frame = new CanvasFrame("Webcam");
//                frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//            }
//
//            // Menampilkan frame webcam
//            new Thread(() -> {
//                while (frame.isVisible()) {
//                    IplImage grabbedImage = opencv_highgui.cvQueryFrame(capture);
//                    if (grabbedImage != null) {
//                        frame.showImage(grabbedImage);
//                    }
//                }
//            }).start();
//            new Thread(() -> {
//                try {
//                    grabber.start(); // Mulai grabber kamera
//                    while (frame.isVisible()) {
//                        IplImage grabbedImage = grabber.grab(); // Ambil frame
//                        if (grabbedImage != null) {
//                            frame.showImage(grabbedImage); // Tampilkan frame ke CanvasFrame
//                        }
//                    }
//                    grabber.stop(); // Hentikan grabber saat frame ditutup
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//
//            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            
    }//GEN-LAST:event_btnOpenEndoskopiActionPerformed

    private void btnSavePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePrintActionPerformed
        controller.insert();
        controller.clearForm();
    }//GEN-LAST:event_btnSavePrintActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnTakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakeActionPerformed
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        
        try {
//            grabber.start();
//            IplImage img = grabber.grab();
//            if(img != null){
//                String filePath = "capture_test.jpeg";
//                cvSaveImage(filePath, img);
//                
//                ImageIcon imagePreview = new ImageIcon();
//                ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(
//                        previewImage.getWidth(),
//                        previewImage.getHeight(),
//                      Image.SCALE_SMOOTH // Pastikan gambar sesuai ukuran JLabel// Pastikan gambar sesuai ukuran JLabel
//                ));
//
//                // Set gambar ke JLabel yang sudah ada
//                previewImage.setIcon(imageIcon);
//            }
//            // Berhenti menggunakan grabber
//            grabber.stop();
            IplImage img = grabber.grab();
               if (img != null) {
                   String filePath = "capture_test.jpeg";
                   cvSaveImage(filePath, img);

                   ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(
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

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        File imageFile = new File("D:/Viola Yosevi/Java Projects/Med1_Endoskopi/capture_test.jpeg");
        ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
        EndoskopiModal.formInstance.lbl.setIcon(imageIcon);
    }//GEN-LAST:event_btnOkActionPerformed

//     private JLabel imageLabel;  
//     private void updateImageDisplay(String imagePath) {
//        // Membuat ImageIcon dari file gambar
//        ImageIcon imageIcon = new ImageIcon(imagePath);
//        
//        // Mengatur ImageIcon ke JLabel
//        imageLabel.setIcon(imageIcon);
//        
//        // Memperbarui tampilan
//        revalidate();
//        repaint();
//    }
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
            java.util.logging.Logger.getLogger(Assesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Assesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Assesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Assesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Assesment(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnOpenEndoskopi;
    private javax.swing.JButton btnSavePrint;
    private javax.swing.JButton btnTake;
    private javax.swing.JLabel imageDelapan;
    private javax.swing.JLabel imageDua;
    private javax.swing.JLabel imageEmpat;
    private javax.swing.JLabel imageEnam;
    private javax.swing.JLabel imageLima;
    private javax.swing.JLabel imageSatu;
    private javax.swing.JLabel imageTiga;
    private javax.swing.JLabel imageTujuh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel previewImage;
    private javax.swing.JTextArea txtDiagnosis;
    private javax.swing.JTextField txtInput;
    private javax.swing.JTextArea txtKeluhan;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
