package Labs;

import Utils.Concurrency.*;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConcurrencyLabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9_1();
        task9_2();
        task10();
        task11();
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

    /**
     * Создайте 10 потоков. Каждый поток должен записать 10 значений равных своему номеру в общий (разделяемый массив).
     * Вывести содержимое массива
     */

    static void task3() throws Exception {
        System.out.println(LINE);
        System.out.println("3) Parallel writing to array.");

        final int SIZE = 10;
        CyclicBarrier barrier = new CyclicBarrier(SIZE+1);

        int[] array = new int[SIZE*10];
        for(int i = 1; i <= SIZE; i++)
            new Thread(new ArrayWriter(i, array, barrier)).start();

        barrier.await();
        System.out.println(Arrays.toString(array));
    }

    /**
     * Создайте 10 потоков. Каждый поток должен записать 10 значений равных Hello world  + номер (Hello world 1)
     * в стандартный поток вывода
     */

    static void task4() throws Exception {
        System.out.println(LINE);
        System.out.println("4) Hello world");

        Semaphore semaphore = new Semaphore(0);
        final int SIZE = 10;
        for(int i = 1; i <= SIZE; i++)
            new Thread(new HelloWorld(Integer.toString(i), SIZE, semaphore)).start();

        semaphore.acquire(10);
    }

    /**
     * Задача Producer/consumer.
     * Задача Producer состоит в том, чтобы генерировать данные и помещать их в очередь, в то же время потребитель
     * будет пытаться удалить данные из очереди и продолжить их обработку.
     */

    static void task5() throws Exception {
        System.out.println(LINE);
        System.out.println("5) Producer/consumer.");

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        final int COUNT = 100;
        Thread a, b;
        (a = new Thread(new QConsumer(queue, COUNT))).start();
        (b = new Thread(new QProducer(queue, COUNT))).start();

        a.join();
        b.join();
    }

    /**
     * Дан массив. Осуществить добавление в него данных из 10 потоков.
     */

    static void task6() throws Exception {
        System.out.println(LINE);
        System.out.println("6) Shared array");

        final int COUNT = 10;
        SharedArray array = new SharedArray(COUNT * 10);
        CyclicBarrier barrier = new CyclicBarrier(COUNT+1);

        for(int i = 1; i <= COUNT; i++)
            new Thread(new SharedArrayProducer(i, array, barrier)).start();

        barrier.await();
        array.show();
    }

    /**
     * Дан счетчик I = 0. Необходимо проинкрементировать его до 10 используя 10 потоков. Каждый поток может увеличить
     * счетчик на 1 только в случае, если текущее значение совпадает с его идентификатором (Поток 1 может увеличить
     * счетчик только если он равен
     * 1) Решить задачу с использованием фич java 7.
     * 2) Решить задачу без использования фич java 7.
     */

    static void task7() throws Exception {
        System.out.println(LINE);
        System.out.println("7) Counter");

        final int COUNT = 10;

        System.out.println("Variant 1:");

        AtomicInteger counter = new AtomicInteger(0);
        CyclicBarrier barrier = new CyclicBarrier(COUNT+1);

        for(int i = COUNT - 1; i >= 0; i--)
            new Thread(new CounterHandler(i, counter, barrier)).start();

        barrier.await();
        System.out.println("Counter: " + counter.get());

        //==============================================================================================================

        System.out.println("\nVariant 2:");

        SData.counter = 0;
        Object sync = new Object();
        barrier = new CyclicBarrier(COUNT+1);

        for(int i = COUNT - 1; i >= 0; i--)
            new Thread(new CounterHandler2(i, sync, barrier)).start();

        barrier.await();
        System.out.println("Counter: " + SData.counter);
    }

    /**
     * Дан счетчик I = 0. Необходимо проинкрементировать его до 1000 используя 10 потоков и вывысти последовательно
     * значения в стандартный вывод.
     */

    public static void task8() throws Exception {
        System.out.println(LINE);
        System.out.println("8) Counter 100\n\n");

        final int THREADS = 10;
        final int LIMIT = 1000;

        CountDownLatch latch = new CountDownLatch(THREADS);
        Lock lock = new ReentrantLock();
        SData.counter = 0;

        for(int i = 0; i < THREADS; i++)
            new Thread(new CounterHandler3(latch, lock, LIMIT)).start();

        latch.await();
    }

    /**
     * Дан массив n чисел. Необходимо возвести в квадрат каждое значение. Распараллелить задачу применением m потоков.
     */

    public static void task9_1() throws Exception {
        System.out.println(LINE);
        System.out.println("9.1) Pow n numbers by m threads");

        final int SIZE = 100000000;
        final int THREADS = 4;
        final int THRESHOLD = SIZE / THREADS + SIZE % THREADS;

        double[] data = new double[SIZE];
        for(int i = 0; i < SIZE; i++) data[i] = i + 1;

        ForkJoinPool pool = new ForkJoinPool(THREADS);
        pool.invoke(new PowTask(data, 0, SIZE, THRESHOLD));

        System.out.println("First 10: ");
        for(int i = 0; i < 10; i++) System.out.print(data[i] + "\t");
        System.out.println();

        if(SIZE >= 10) {
            System.out.println("Last 10: ");
            for (int i = SIZE - 10; i < SIZE; i++) System.out.print(data[i] + "\t");
            System.out.println();
        }
    }

    public static void task9_2() throws Exception {
        System.out.println(LINE);
        System.out.println("9.2) Pow n numbers by m threads");

        final int SIZE = 100000000;
        final int THREADS = 4;
        final int THRESHOLD = SIZE / THREADS + SIZE % THREADS;

        double[] data = new double[SIZE];
        for(int i = 0; i < SIZE; i++) data[i] = i + 1;

        CountDownLatch latch = new CountDownLatch(THREADS);

        for(int i = 0; i < THREADS; i++) {
            if(i == THREADS - 1) new Thread(new PowTask2(data,i * THRESHOLD, SIZE, latch)).start();
            else new Thread(new PowTask2(data,i * THRESHOLD, (i+1) * THRESHOLD, latch)).start();
        }

        latch.await();

        System.out.println("First 10: ");
        for(int i = 0; i < 10; i++) System.out.print(data[i] + "\t");
        System.out.println();

        if(SIZE >= 10) {
            System.out.println("Last 10: ");
            for (int i = SIZE - 10; i < SIZE; i++) System.out.print(data[i] + "\t");
            System.out.println();
        }
    }

    /**
     * Дан массив n чисел. Необходимо найти максимальное значение. Распараллелить задачу применением m потоков
     */

    public static void task10() {
        System.out.println(LINE);
        System.out.println("10) Find max value");

        final int SIZE = 1000;
        final int THREADS = 4;
        final int THRESHOLD = SIZE / THREADS + SIZE % THREADS;

        double[] data = new double[SIZE];
        for(int i = 0; i < SIZE; i++) data[i] = Math.random() * 1000000;

        ForkJoinPool pool = new ForkJoinPool(THREADS);
        double max = pool.invoke(new MaxTask(data, 0, SIZE, THRESHOLD));
        System.out.println("Max: " + max);
    }

    /**
     * Реализовать функцию y = log_2⁡x c поддержкой кеширования. Вычислить функцию для n случайных x из диапазона [a,b]
     * используя при этом m потоков (каждый поток генерирует n значений, кеш общий дял всех потоков). Выбрать n >  m*(a-b)
     */

    static Supplier<Function<Integer, Double>> concurrentLogFunction(int[] count) {
        return () -> {
            ConcurrentHashMap<Integer, Double> cache = new ConcurrentHashMap<>();
            return x -> {
                if(!cache.containsKey(x)) { cache.put(x, Math.log(x) / Math.log(2)); count[0]++; }
                return cache.get(x);
            };
        };
    }

    public static void task11() {
        System.out.println(LINE);
        System.out.println("11) Get log2x");

        final int VAL_A = 1;
        final int VAL_B = 65536;
        final int THREADS = 4;
        final int SIZE = 1000000;
        final int THRESHOLD = SIZE / THREADS + SIZE % THREADS;

        int[] data = new int[SIZE];
        double[] result = new double[SIZE];
        for(int i = 0; i < SIZE; i++) data[i] = (int)(Math.random() * (VAL_B - VAL_A) + VAL_A);

        int[] cacheSize = new int[1];

        ForkJoinPool pool = new ForkJoinPool(THREADS);
        pool.invoke(new LogTask(data, result,0, SIZE, THRESHOLD, concurrentLogFunction(cacheSize).get()));

        System.out.println("First 10: ");
        for(int i = 0; i < 10; i++) System.out.println("Log2(" + data[i] + ") = " + result[i]);
        System.out.println("Cache size: " + cacheSize[0]);
    }

    /**
     * Реализовать следующее поведение: Decrementer получает число, и через случайные промежутки времени уменьшает его
     * до нуля. Waiter получает ссылку на это число, и ждет пока оно не будет равно нулю. После того, как число равно
     * нулю, Waiter печатает в стандартный поток вывода общее время ожидания, которое должно с точностью до
     * миллисекунды совпасть с реальным временем работы Decrementer. Запрещено использовать цикл.
     * Примечание: Waiter не должен ничего знать о размерах задержек на Decrementer, т.к. они случайны.
     */

    public static void task12() {
        
    }

}

























