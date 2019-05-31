package Utils.NIO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class PictureServerThread extends Thread {
    private Socket client;
    private String filePath;

    public PictureServerThread(String name, Socket client, String filePath) {
        super(name);
        this.client = client;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(this.client.getInputStream()))) {

            System.out.println("Start handling: " + this.getName());

            String encodedImage = input.readLine();
            byte[] data = Base64.getDecoder().decode(encodedImage);

            Path path = Paths.get(this.filePath);
            Files.write(path, data);

            System.out.println("End handling: " + this.getName());
            this.client.close();
        }
        catch (IOException e) {
            System.out.println(this.getName() + " error: " + e.getMessage());
        }
    }
}
