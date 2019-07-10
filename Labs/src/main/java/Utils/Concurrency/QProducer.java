package Utils.Concurrency;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QProducer implements Runnable {
    int count;
    ConcurrentLinkedQueue<Integer> queue;

    public QProducer(ConcurrentLinkedQueue<Integer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public void run() {
        for(int i = 0; i < this.count; i++)
            queue.add((int)(Math.random()*100));
    }
}
