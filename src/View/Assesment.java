
package View;

import Config.Koneksi;
import Controller.AssesmentController;
import Model.LoginModel;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRResultSetDataSource;


public class Assesment extends javax.swing.JFrame {
    
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
    CvCapture capture;
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
//        controller.viewTable();
//        controller.listNama(); 

//        try (
//            Statement stat = conn.createStatement();
//             ResultSet res = stat.executeQuery("SELECT tombol FROM setting LIMIT 1")) {
//            
//            if (res.next()) {
//                tombol = res.getString("tombol");
//                
//                if(tombol == "1")
//                {
//                    String key_pic = "VK_1";
//                }else{
//                    
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Assesment.class.getName()).log(Level.SEVERE, null, ex);
//        }
 // Tambahkan ini agar JFrame mendeteksi input keyboard
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
                        IplImage grabbedImage = grabber.grab();
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
    
   

private IplImage capturedStateImage;
private int pictureCount = 0; // Variabel untuk menghitung jumlah gambar yang diambil

public void takePicture() {
    System.out.println("Kamu berhasil take");
    try {
        IplImage img = grabber.grab();
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

    
     private BufferedImage convertToBufferedImage(IplImage iplImage) {

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
     

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeluhan = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnosis = new javax.swing.JTextArea();
        btnOpenEndoskopi = new javax.swing.JButton();
        btnSavePrint = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        comboBoxNama = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        imageSatu = new javax.swing.JLabel();
        imageDua = new javax.swing.JLabel();
        imageTiga = new javax.swing.JLabel();
        imageEmpat = new javax.swing.JLabel();
        imageLima = new javax.swing.JLabel();
        imageEnam = new javax.swing.JLabel();
        imageTujuh = new javax.swing.JLabel();
        imageDelapan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Diagnosis");

        txtKeluhan.setColumns(20);
        txtKeluhan.setRows(5);
        jScrollPane1.setViewportView(txtKeluhan);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        comboBoxNama.setEditable(true);
        comboBoxNama.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxNamaItemStateChanged(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon  300px.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSavePrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOpenEndoskopi, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addGap(110, 110, 110))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(comboBoxNama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnOpenEndoskopi)
                .addGap(50, 50, 50)
                .addComponent(btnSavePrint)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLima, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageEnam, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageTujuh, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageDelapan, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageDua, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageTiga, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageEmpat, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageDua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageTiga, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageEmpat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageDelapan, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(imageLima, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imageEnam, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imageTujuh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
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
        MenuEndoskopi menu = new MenuEndoskopi();
        menu.setVisible(true);
        menu.pack();
        menu.setLocationRelativeTo(null);
        this.dispose();
//        System.out.println("ini data image " +imagePaths);
    }//GEN-LAST:event_btnBackActionPerformed

    private void comboBoxNamaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxNamaItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            String selectedItem = (String) evt.getItem();
            System.out.println("kamu milih " +selectedItem);

        }
//        comboBoxNama.insertItemAt(item, 0);
    }//GEN-LAST:event_comboBoxNamaItemStateChanged



    private void filterComboBoxItems(JComboBox<String> comboBox, String input) {
    // Ambil semua item dari JComboBox
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();

        // Simpan item yang sesuai dengan pencarian
        List<String> filteredItems = new ArrayList<>();

        // Filter item berdasarkan input yang diketik
        for (int i = 0; i < model.getSize(); i++) {
            String item = model.getElementAt(i);
            if (item.toLowerCase().contains(input.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        // Perbarui JComboBox dengan item yang difilter
        model.removeAllElements();
        for (String item : filteredItems) {
            model.addElement(item);
        }

        // Pilih item pertama (jika ada) sebagai default
        if (!filteredItems.isEmpty()) {
            comboBox.setSelectedIndex(0);
        }
    }

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
                new Assesment().setVisible(true);
                
            }
        });
    }

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtDiagnosis;
    private javax.swing.JTextArea txtKeluhan;
    // End of variables declaration//GEN-END:variables
}
