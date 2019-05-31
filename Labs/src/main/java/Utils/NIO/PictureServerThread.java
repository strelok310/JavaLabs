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

    public PictureServerThread(String name, Socket client) {
        super(name);
        this.client = client;
    }

    @Override
    public void run() {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

            System.out.println("Start handling: " + this.getName());

            String encodedImage = input.readLine();
            byte[] data = Base64.getDecoder().decode(encodedImage);

            Path path = Paths.get("D:/java_nio_tmp/image.jpg");
            Files.write(path, data);

            System.out.println("End handling: " + this.getName());
            client.close();
        }
        catch (IOException e) {
            System.out.println(this.getName() + " error: " + e.getMessage());
        }
    }
}
