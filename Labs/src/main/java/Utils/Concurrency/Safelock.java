package Utils.Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class Safelock {
    static class Friend {
        private final String name;
        private final Lock lock = new ReentrantLock();
        private static final Lock slock = new ReentrantLock();

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public boolean impendingBow(Friend bower) {
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = bower.lock.tryLock();
            } finally {
                if (! (myLock && yourLock)) {
                    if (myLock) lock.unlock();
                    if (yourLock) bower.lock.unlock();
                }
            }
            return myLock && yourLock;
        }

        public void bow(Friend bower) {
            boolean result = false;
            do {
                slock.lock();
                result = impendingBow(bower);
                slock.unlock();
            }
            while(!result);

            System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);

            lock.unlock();
            bower.lock.unlock();
        }

        public void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}