package Utils.Concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class CounterHandler3 implements Runnable {
    CountDownLatch latch;
    Lock lock;
    int limit;

    public CounterHandler3(CountDownLatch latch, Lock lock, int limit) {
        this.latch = latch;
        this.lock = lock;
        this.limit = limit;
    }

    public void run() {
        while(true) {
            lock.lock();

            if(SData.counter == limit) {
                lock.unlock();
                break;
            }

            SData.counter++;
            System.out.print(SData.counter + "\t");
            if(SData.counter % 10 == 0) System.out.println();

            lock.unlock();
        }

        latch.countDown();
    }
}
