package Utils.Concurrency;

import java.util.concurrent.Semaphore;

public class SProducer implements Runnable {
    String name;
    Semaphore semaphore;

    public SProducer(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    public void run() {
        for(int i = 0; i < 10; i++) {
            SData.value[i] = (int)Math.round(Math.random()*10);
            this.semaphore.release();
        }
    }
}
