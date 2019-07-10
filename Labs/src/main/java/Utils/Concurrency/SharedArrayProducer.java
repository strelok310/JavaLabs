package Utils.Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SharedArrayProducer implements Runnable {
    SharedArray array;
    int id;
    CyclicBarrier barrier;

    public SharedArrayProducer(int id, SharedArray array, CyclicBarrier barrier) {
        this.id = id;
        this.array = array;
        this.barrier = barrier;
    }

    public void run() {
        try {
            for (int i = (id - 1) * 10; i < id * 10; i++) {
                this.array.push(id);
                Thread.sleep(50);
            }

            barrier.await();
        }
        catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(e.getMessage());
        }
    }
}
