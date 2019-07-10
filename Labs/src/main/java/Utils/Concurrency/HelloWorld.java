package Utils.Concurrency;

import java.util.concurrent.Semaphore;

public class HelloWorld implements Runnable {
    String name;
    int count;
    Semaphore semaphore;

    public HelloWorld(String name, int count, Semaphore semaphore) {
        this.name = name;
        this.count = count;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            for(int i = 0; i < this.count; i++) {
                System.out.println("Hello world " + name);
                Thread.sleep(50);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        semaphore.release();
    }
}
