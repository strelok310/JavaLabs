package Utils.Concurrency;

public class Deadlock {

    private String name;

    public Deadlock(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void hello(Deadlock lock) {
        System.out.format("%s: %s" + "  has bowed to me!%n", this.name, lock.getName());
        lock.helloBack(this);
    }

    public synchronized void helloBack(Deadlock lock) {
        System.out.format("%s: %s"
                        + " has bowed back to me!%n",
                this.name, lock.getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Deadlock deadlock = new Deadlock("1");
        Deadlock deadlock2 = new Deadlock("2");

        new Thread(() -> deadlock.hello(deadlock2)).start();
        new Thread(() -> deadlock2.hello(deadlock)).start();
    }

}
