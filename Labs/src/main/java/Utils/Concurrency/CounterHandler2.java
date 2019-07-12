package Utils.Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CounterHandler2 implements Runnable {
    int id;
    Object sync;
    CyclicBarrier barrier;

    public CounterHandler2(int id, Object sync, CyclicBarrier barrier) {
        this.id = id;
        this.sync = sync;
        this.barrier = barrier;
    }

    public void run() {
        try {
            while(true) {
                synchronized (sync) {
                    if(SData.counter == id) {
                        SData.counter++;
                        sync.notifyAll();
                        break;
                    }
                    sync.wait();
                }
                //Thread.sleep(50);
            }

            barrier.await();
        }
        catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(e.getMessage());
        }
    }
}
