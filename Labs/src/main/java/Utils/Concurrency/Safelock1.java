package Utils.Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Safelock1 {
    private String name;
    private final Lock lock = new ReentrantLock();
    private static final Lock slock = new ReentrantLock();

    public Safelock1(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean prepareHello(Safelock1 bower) {
        Boolean myLock = lock.tryLock();
        Boolean yourLock = bower.lock.tryLock();

        if (! (myLock && yourLock)) {
            if (myLock) lock.unlock();
            if (yourLock) bower.lock.unlock();
        }

        return myLock && yourLock;
    }

    public void hello(Safelock1 bower) {
        boolean result = false;

        do {
            slock.lock();
            result = prepareHello(bower);
            slock.unlock();
        }
        while(!result);

        System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
        bower.helloBack(this);

        lock.unlock();
        bower.lock.unlock();
    }

    public void helloBack(Safelock1 bower) {
        System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
    }
}
