package Utils.Concurrency;

import java.util.concurrent.CountDownLatch;

public class PowTask2 implements Runnable {
    double[] data;
    int begin;
    int end;
    CountDownLatch latch;

    public PowTask2(double[] data, int begin, int end, CountDownLatch latch) {
        this.data = data;
        this.begin = begin;
        this.end = end;
        this.latch = latch;
    }

    public void run() {
        for(int i = this.begin; i < this.end; i++)
            this.data[i] = Math.pow(this.data[i], 2);
        this.latch.countDown();
    }
}
