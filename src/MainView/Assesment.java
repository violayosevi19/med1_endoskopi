/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MainView;

import Config.Koneksi;
import Controller.AssesmentController;
//import View.Assesment;
import static View.Assesment.Instance;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author HP
 */
public class Assesment extends javax.swing.JPanel {

    /**
     * Creates new form Assesment
     */
    
    public static Assesment Instance;
    public Map<String, Object> currentUser;
    public JLabel lblImage, lblTest2, lblImage1, lblImage2, lblImage3, lblImage4, lblImage5, lblImage6, lblImage7, lblImage8;
    public JTextField txt, inputNama;
    public JComboBox comboPasien;
    public JTextArea inputKeluhan, inputDiagnosis;
    public List<String> filePaths = new ArrayList<>();
    public List<String> imagePaths = new ArrayList<>();
    
    private Statement stat;
    private ResultSet res;
    private String tombol;
    private String key_pic;

    
    AssesmentController controller;
    private boolean captureImage = false;
    opencv_highgui.CvCapture capture;
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(1);
    
    private Connection conn;
    
    public Assesment(Map<String, Object> user) {
            initComponents();
            conn = Koneksi.getConnection();
            controller = new AssesmentController(this);
            controller.getPasiens();
            this.currentUser = user;
        
        populateUserData();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    takePicture();
                }
            }
        });

        // Pastikan JFrame dapat fokus
        this.setFocusable(true);
        this.requestFocusInWindow();

        Instance = this;
        lblImage1 = imageSatu;
        lblImage2 = imageDua;
        lblImage3 = imageTiga;
        lblImage4 = imageEmpat;
        lblImage5 = imageLima;
        lblImage6 = imageEnam;
        lblImage7 = imageTujuh;
        lblImage8 = imageDelapan;
//        inputNama = txtNama;
        inputKeluhan = txtKeluhan;
        inputDiagnosis = txtDiagnosis;  
        comboPasien = comboBoxNama;
        
        AutoCompleteDecorator.decorate(comboBoxNama);
    }
    
    public Assesment() {
        this(null); // Panggil konstruktor lain dengan nilai null
    }
    
    private void populateUserData() {
        if (currentUser != null) {
            System.out.println("data user : " + currentUser);
            // Tambahkan field lainnya jika diperlukan
        }
    }
    
     public void getFilePath(List<String> files) {
           this.filePaths = files;  // Menyimpan filePaths yang diterima ke variabel global
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


    public JComboBox<String> getComboBoxNama() {
        return comboBoxNama;
    }

    public void setComboBoxNama(JComboBox<String> comboBoxNama) {
        this.comboBoxNama = comboBoxNama;
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

    
    @SuppressWarnings("unchecked")
    
    public void CameraViewer() {
    try {
        CanvasFrame frame = new CanvasFrame("Webcam");
        frame.setCanvasSize(1200, 800);
        frame.pack();
        frame.setLocationRelativeTo(null);
        

        // Thread untuk menangkap gambar dari kamera
        new Thread(() -> {
            try {
                grabber.start();
                
                // Tambahkan KeyListener untuk menangkap input keyboard
                frame.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        System.out.println(e.getKeyCode());
                        System.out.println("Kamu tekan tombol: " + e.getKeyText(e.getKeyCode()));
                        
                        
                        
                        
                        if (e.getKeyCode() == KeyEvent.VK_1) {
                            System.out.println("masuk yaaa");
                            takePicture();
                        }
                    }
                });

                frame.setFocusable(true);
                frame.requestFocusInWindow();

                // Loop untuk menampilkan gambar
                while (frame.isVisible()) {
                    try {
                        opencv_core.IplImage grabbedImage = grabber.grab();
                        if (grabbedImage != null) {
                            frame.showImage(grabbedImage);
                        }
                    } catch (Exception e) {
                        System.out.println("Gagal mengambil frame: " + e.getMessage());
                        break; // Keluar dari loop jika gagal
                    }
                }

                // Hentikan grabber jika jendela ditutup
                grabber.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Ubah perilaku penutupan jendela
        frame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

        // Tambahkan listener untuk menangani event penutupan jendela
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.out.println("Jendela ditutup. Grabber dihentikan.");
                try {
                    grabber.stop();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
   

private opencv_core.IplImage capturedStateImage;
private int pictureCount = 0; // Variabel untuk menghitung jumlah gambar yang diambil

public void takePicture() {
    System.out.println("Kamu berhasil take");
    try {
        opencv_core.IplImage img = grabber.grab();
        if (img != null) {
            capturedStateImage = img;
            String folderPath = "images/";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs(); // Buat folder jika belum ada
            }
            String fileName = "capture_" + System.currentTimeMillis() + ".jpeg";
            String filePath = folderPath + fileName;
            cvSaveImage(filePath, capturedStateImage); // Menyimpan gambar ke file
            imagePaths.add(filePath);
            BufferedImage bufferedImage = convertToBufferedImage(img);

            JLabel[] labels = {
                imageSatu, imageDua, imageTiga, imageEmpat, imageLima, imageEnam, imageTujuh, imageDelapan
            };

            for (JLabel label : labels) {
                if (label.getIcon() == null) {
                    ImageIcon imageIcon = new ImageIcon(bufferedImage.getScaledInstance(
                        label.getWidth(),
                        label.getHeight(),
                        Image.SCALE_SMOOTH
                    ));
                    label.setIcon(imageIcon);
                    label.setVisible(true);
                    break;
                }
            }

            // Menambahkan jumlah pengambilan gambar
            pictureCount++;
            String message = "Success Take Picture ke-" + pictureCount;

            // Menampilkan notifikasi otomatis tutup dalam 3 detik
            JDialog dialog = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE).createDialog("Notifikasi");
            dialog.setModal(false); // Non-modal agar tidak mengganggu aktivitas lainnya
            dialog.setVisible(true);

            // Timer untuk menutup dialog setelah 3 detik
            new javax.swing.Timer(3000, e -> dialog.dispose()).start();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
     private BufferedImage convertToBufferedImage(opencv_core.IplImage iplImage) {

        int width = iplImage.width();
        int height = iplImage.height();


        byte[] data = new byte[width * height * 3];
        iplImage.getByteBuffer().get(data);


        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

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
     
    
  
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        imageSatu = new javax.swing.JLabel();
        imageDua = new javax.swing.JLabel();
        imageTiga = new javax.swing.JLabel();
        imageEmpat = new javax.swing.JLabel();
        imageLima = new javax.swing.JLabel();
        imageEnam = new javax.swing.JLabel();
        imageTujuh = new javax.swing.JLabel();
        imageDelapan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnosis = new javax.swing.JTextArea();
        btnOpenEndoskopi = new javax.swing.JButton();
        btnSavePrint = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboBoxNama = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeluhan = new javax.swing.JTextArea();

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(imageLima, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(imageEnam, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(imageTujuh, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(imageDelapan, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(imageSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(imageDua, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(imageTiga, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(imageEmpat, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(67, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(144, 144, 144)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(imageSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imageDua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imageTiga, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imageEmpat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imageDelapan, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(imageLima, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageEnam, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageTujuh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(144, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 0, 51));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ASSESMENT");

        comboBoxNama.setEditable(true);
        comboBoxNama.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxNamaItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Diagnosis");

        txtKeluhan.setColumns(20);
        txtKeluhan.setRows(5);
        jScrollPane1.setViewportView(txtKeluhan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxNama, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnOpenEndoskopi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSavePrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnOpenEndoskopi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSavePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnBack)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenEndoskopiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenEndoskopiActionPerformed
        //        String nama = txtNama.getText();
        //        String nama = (String) comboBoxNama.getSelectedItem();
        //        String keluhan = txtKeluhan.getText();
        //        String diagnosis = txtDiagnosis.getText();
        //
        //        EndoskopiModal modal = new EndoskopiModal();
        //        modal.setNama(nama);
        //        modal.setKeluhan(keluhan);
        //        modal.setDiagnosis(diagnosis);
        //        modal.setVisible(true);
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
        CameraViewer();
    }//GEN-LAST:event_btnOpenEndoskopiActionPerformed

    private void btnSavePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePrintActionPerformed
        controller.insert();
        controller.clearForm();
        controller.printFile();
    }//GEN-LAST:event_btnSavePrintActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //        MenuEndoskopi login = new MenuEndoskopi();
        //        login.setVisible(true);
        //        login.pack();
        //        login.setLocationRelativeTo(null);
        //        this.dispose();
        //        System.out.println("ini data image " +imagePaths);
    }//GEN-LAST:event_btnBackActionPerformed

    private void comboBoxNamaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxNamaItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            String selectedItem = (String) evt.getItem();
            System.out.println("kamu milih " +selectedItem);

        }
        //        comboBoxNama.insertItemAt(item, 0);
    }//GEN-LAST:event_comboBoxNamaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnOpenEndoskopi;
    private javax.swing.JButton btnSavePrint;
    private javax.swing.JComboBox<String> comboBoxNama;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtDiagnosis;
    private javax.swing.JTextArea txtKeluhan;
    // End of variables declaration//GEN-END:variables
}
