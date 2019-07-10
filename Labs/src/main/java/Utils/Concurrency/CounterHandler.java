package Utils.Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterHandler implements Runnable {
    int id;
    AtomicInteger counter;
    CyclicBarrier barrier;

    public CounterHandler(int id, AtomicInteger counter, CyclicBarrier barrier) {
        this.id = id;
        this.counter = counter;
        this.barrier = barrier;
    }

    public void run() {
        try {
            while (!this.counter.compareAndSet(id, id + 1))
                Thread.sleep(50);
            barrier.await();
        }
        catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(e.getMessage());
        }
    }
}
