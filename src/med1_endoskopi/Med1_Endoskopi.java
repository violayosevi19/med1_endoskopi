/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package med1_endoskopi;

import Config.Koneksi;
import View.Encrypt;
import MainView.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

public class Med1_Endoskopi {

    public static void main(String[] args) {
         try {
            File file = new File("data.txt");
            boolean openEncrypt = false;
            String fileUUID2 = null; // UUID yang dibaca dari file
            String fileUUID = null;
            String test = "MEDIUTAMA-UTAMA-MEDI-2025";
            
            String generatedUUID = getUniqueCode(); // UUID baru yang dihasilkan
            String UUID = generatedUUID; // UUID kedua dari file
             System.out.println(UUID);

            // Membaca file jika ada
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("equal Code:")) {
                            try {
                                fileUUID = line.split(":")[1].trim(); // Memasukkan UUID yang ditemukan
                            } catch (Exception e) {
                                break;
                            }
                        }
                        if (line.startsWith("Unique Code:")) {
                            fileUUID2 = line.split(":")[1].trim(); // Memasukkan UUID kedua
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Kode unik PC: " + test + " ditulis ke file.");
            }

            // Periksa apakah UUID cocok
            if (fileUUID2 == null  || !fileUUID2.equals(test)) {
                System.out.println("UUID tidak cocok. Mengirim UUID baru ke API...");
                postUUIDToAPI(test);
                Encrypt enc = new Encrypt();
                enc.setVisible(true);
                enc.pack();
                enc.setLocationRelativeTo(null);
            } else {
                System.out.println("UUID cocok, tidak perlu mengirim ke API.");
                Main main = new Main();
                main.setVisible(true);
                main.pack();
                main.setLocationRelativeTo(null);
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error membaca file!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error mendapatkan kode unik!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void postUUIDToAPI(String uuid) {
        try {
            String apiUrl = "http://medi.digimoment.web.id/api/license"; // Ganti dengan URL API Anda
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Data JSON yang dikirim
            String jsonPayload = "{ \"license\": \"" + uuid + "\" }";

            // Kirim data
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            // Periksa respon
            
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("UUID berhasil dikirim ke API.");
            } else {
                System.out.println("Gagal mengirim UUID ke API. Response code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saat mengirim UUID ke API.");
        }
    }

    public static String getUniqueCode() throws Exception {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface network = networkInterfaces.nextElement();
            if (network != null && !network.isLoopback() && network.getHardwareAddress() != null) {
                byte[] mac = network.getHardwareAddress(); // 6 byte MAC Address
                if (mac.length >= 6) {
                    ByteBuffer buffer = ByteBuffer.allocate(16);

                    // Isi 6 byte MAC Address ke buffer
                    buffer.put(mac);

                    // Isi sisa buffer dengan nol
                    for (int i = 0; i < 10; i++) {
                        buffer.put((byte) 0);
                    }

                    // Buat UUID dari buffer
                    buffer.flip();
                    long mostSigBits = buffer.getLong();
                    long leastSigBits = buffer.getLong();
                    UUID uuid = new UUID(mostSigBits, leastSigBits);

                    return uuid.toString(); // Kembalikan UUID sebagai string
                }
            }
        }
        throw new RuntimeException("Tidak dapat menemukan MAC Address!");
    }

}