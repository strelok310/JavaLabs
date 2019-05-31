package Utils.NIO;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class PictureClientThread extends Thread {

    public PictureClientThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try { Thread.sleep(500); }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Client");
        System.out.println("Connection...");

        try (Socket server = new Socket(InetAddress.getLocalHost(), 1503)) {

            System.out.println("connected: " + server.isConnected());
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));

            Path path = Paths.get(ClassLoader.getSystemResource("image.jpg").toURI());
            byte[] image = Files.readAllBytes(path);
            String encodedImage = Base64.getEncoder().encodeToString(image);

            output.write(encodedImage);
            output.close();

        } catch (IOException | URISyntaxException e) {
            System.out.println("Client error: " + e.getMessage());
        }
        System.out.println();
    }
}
