package Labs;

import Utils.Concurrency.SConsumer;
import Utils.Concurrency.SProducer;
import Utils.Concurrency.Safelock1;
import Utils.Concurrency.Safelock2;

import java.util.concurrent.Semaphore;

public class ConcurrencyLabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        task1();
        task2();
    }

    /**
     * Разрешить deadlock в /.payload/java8/multithreading/Deadlock.java всеми способами, которые вы знаете.
     */

    static void task1() throws Exception {
        System.out.println(LINE);
        System.out.println("1) Deadlock");

        System.out.println("Variant 1");
        Safelock1 a = new Safelock1("1");
        Safelock1 b = new Safelock1("2");
        Thread ta = new Thread(() -> a.hello(b));
        Thread tb = new Thread(() -> b.hello(a));

        ta.start();
        tb.start();
        ta.join();
        tb.join();

        System.out.println("Variant 2");
        Safelock2 c = new Safelock2("1");
        Safelock2 d = new Safelock2("2");
        Thread tc = new Thread(() -> c.hello(d));
        Thread td = new Thread(() -> d.hello(c));

        tc.start();
        td.start();
        tc.join();
        td.join();
    }

    /**
     * Реализовать обмен данными между потоками через семафор
     */

    static void task2() throws Exception {
        System.out.println(LINE);
        System.out.println("2) Data exchange by semaphore.");

        Semaphore semaphore = new Semaphore(0);

        Thread a, b;
        (a = new Thread(new SProducer("Producer", semaphore))).start();
        (b = new Thread(new SConsumer("Consumer", semaphore))).start();

        a.join();
        b.join();
    }
}
