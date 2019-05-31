package Utils.NIO;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class PictureCharsetServerThread extends Thread {
    private Socket client;

    public PictureCharsetServerThread(String name, Socket client) {
        super(name);
        this.client = client;
    }

    @Override
    public void run() {
        try(DataInputStream input = new DataInputStream(client.getInputStream())) {

            System.out.println("Start handling: " + this.getName());

            String charSet = input.readUTF();
            int size = input.readInt();
            byte[] image = new byte[size];
            input.readFully(image);

            String encodedImage = new String(image, Charset.forName(charSet));
            image = Base64.getDecoder().decode(encodedImage);

            Path path = Paths.get("D:/java_nio_tmp/image2.jpg");
            Files.write(path, image);

            System.out.println("End handling: " + this.getName());
            client.close();
        }
        catch (IOException e) {
            System.out.println(this.getName() + " error: " + e.getMessage());
        }
    }
}
