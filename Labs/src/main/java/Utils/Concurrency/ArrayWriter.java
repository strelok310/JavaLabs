package Utils.Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ArrayWriter implements Runnable {
    int[] array;
    int id;
    CyclicBarrier barrier;

    public ArrayWriter(int id, int[] array, CyclicBarrier barrier) {
        this.array = array;
        this.id = id;
        this.barrier = barrier;
    }

    public void run() {
        for(int i = (id-1)*10; i < id*10; i++)
            array[i] = id;

        try { barrier.await(); }
        catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(e.getMessage());
        }
    }
}
