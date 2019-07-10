package Utils.Concurrency;

import java.util.concurrent.Semaphore;

public class SConsumer implements Runnable {
    String name;
    Semaphore semaphore;

    public SConsumer(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                this.semaphore.acquire();
                System.out.println("\t" + (i + 1) + ") Random value: " + SData.value[i]);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
