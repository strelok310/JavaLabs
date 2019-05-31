package Utils.NIO;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class PictureCharsetClientThread extends Thread {
    public PictureCharsetClientThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try { Thread.sleep(500); }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Client");

        try (Socket server = new Socket(InetAddress.getLocalHost(), 1503)) {

            System.out.println("connected: " + server.isConnected());
            DataOutputStream output = new DataOutputStream(server.getOutputStream());

            Path path = Paths.get(ClassLoader.getSystemResource("image.jpg").toURI());
            byte[] image = Files.readAllBytes(path);
            String encodedImage = Base64.getEncoder().encodeToString(image);
            image = encodedImage.getBytes(StandardCharsets.US_ASCII);

            output.writeUTF("ASCII");
            output.writeInt(image.length);
            output.write(image);

            output.close();

        } catch (IOException | URISyntaxException e) {
            System.out.println("Client error: " + e.getMessage());
        }
        System.out.println();
    }
}
