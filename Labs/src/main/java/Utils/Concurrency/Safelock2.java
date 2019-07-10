package Utils.Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Safelock2 {
    private String name;
    private final Lock lock = new ReentrantLock();

    public Safelock2(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void hello(Safelock2 bower) {

        while(true) {
            this.lock.lock();
            if(bower.lock.tryLock()) break;
            this.lock.unlock();
        }

        System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
        bower.helloBack(this);

        bower.lock.unlock();
        this.lock.unlock();
    }

    public void helloBack(Safelock2 bower) {
        System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
    }
}
