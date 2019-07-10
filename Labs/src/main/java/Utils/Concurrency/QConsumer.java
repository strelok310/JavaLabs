package Utils.Concurrency;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QConsumer implements Runnable {
    int count;
    ConcurrentLinkedQueue<Integer> queue;

    public QConsumer(ConcurrentLinkedQueue<Integer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public void run() {
        Integer value;
        while(count > 0) {
            if((value = queue.poll()) != null) {
                if(this.count % 10 == 0) System.out.println();
                System.out.print(value + "\t");
                this.count--;
            }
        }
    }
}
