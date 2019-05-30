package Utils.NIO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeServerThread extends Thread {
    private Socket client;

    public TimeServerThread(String name, Socket client) {
        super(name);

        this.client = client;
    }

    @Override
    public void run() {

        try(ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream())) {

            System.out.println("Start handling: " + this.getName());

            ZoneId zone = (ZoneId)input.readObject();
            ZonedDateTime dateTime = ZonedDateTime.now(zone);

            System.out.println("Server time: " + ZonedDateTime.now());
            System.out.println("Server asked time: " + dateTime);

            output.writeObject(dateTime);

            System.out.println("End handling: " + this.getName());
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println(this.getName() + " error: " + e.getMessage());
        }

    }
}
