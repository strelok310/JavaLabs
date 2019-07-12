package Utils.Concurrency;

import java.util.concurrent.RecursiveTask;

public class MaxTask extends RecursiveTask<Double> {
    double[] data;
    int begin;
    int end;
    int threshold;

    public MaxTask(double[] data, int begin, int end, int threshold) {
        this.data = data;
        this.begin = begin;
        this.end = end;
        this.threshold = threshold;
    }

    public Double compute() {
        double max;
        if(this.end - this.begin <= threshold) {
            max = this.data[this.begin];
            for(int i = this.begin + 1; i < this.end; i++)
                max = Math.max(max, this.data[i]);
        }
        else {
            int middle = (this.begin + this.end) / 2;

            MaxTask task1 = new MaxTask(this.data, this.begin, middle, this.threshold);
            MaxTask task2 = new MaxTask(this.data, middle, this.end, this.threshold);
            task1.fork();
            task2.fork();

            max = Math.max(task1.join(), task2.join());
        }
        return max;
    }
}
