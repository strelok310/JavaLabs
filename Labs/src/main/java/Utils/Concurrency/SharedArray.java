package Utils.Concurrency;

public class SharedArray {
    int maxSize;
    int count;
    int[] array;

    public SharedArray(int maxSize) {
        this.maxSize = maxSize;
        this.count = 0;
        this.array = new int[maxSize];
    }

    public synchronized boolean push(int value) {
        if(this.count < this.maxSize) {
            this.array[this.count++] = value;
            return true;
        }
        return false;
    }

    public synchronized int get(int id) {
        if(id >= 0 && id < this.maxSize)
            return this.array[id];
        return 0;
    }

    public synchronized void put(int id, int value) {
        if(id >= 0 && id < this.maxSize) {
            this.array[id] = value;
        }
    }

    public synchronized int[] getArray() {
        return this.array.clone();
    }

    public synchronized void show() {
        for(int i = 0; i < this.maxSize; i++) {
            if(i % 10 == 0) System.out.println();
            System.out.print(this.array[i] + "\t");
        }
    }
}
