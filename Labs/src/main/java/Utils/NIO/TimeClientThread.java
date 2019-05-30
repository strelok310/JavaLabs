package Utils.NIO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeClientThread extends Thread {
    private String[] zones;

    public TimeClientThread(String name, String[] zones) {
        super(name);
        this.zones = zones;
    }

    @Override
    public void run() {

        try { Thread.sleep(500); }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Client");

        for(String zone : zones) {
            System.out.println("Connection...");

            try (Socket server = new Socket(InetAddress.getLocalHost(), 1503)) {
                System.out.println("connected: " + server.isConnected());

                ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(server.getInputStream());

                output.writeObject(ZoneId.of(zone));
                ZonedDateTime dateTime = (ZonedDateTime) input.readObject();
                System.out.format("ZonedDateTime (%s): %s\n", zone, dateTime.toString());

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Client error: " + e.getMessage());
            }
            System.out.println();
        }

    }
}
