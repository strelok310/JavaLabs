package Utils.Concurrency;

import java.util.concurrent.RecursiveAction;

public class PowTask extends RecursiveAction {
    double[] data;
    int begin;
    int end;
    int threshold;

    public PowTask(double[] data, int begin, int end, int threshold) {
        this.data = data;
        this.begin = begin;
        this.end = end;
        this.threshold = threshold;
    }

    public void compute() {
        if(this.end - this.begin <= threshold) {
            for(int i = this.begin; i < this.end; i++)
                this.data[i] = Math.pow(this.data[i], 2);
        }
        else {
            int middle = (this.begin + this.end) / 2;
            invokeAll(new PowTask(this.data, this.begin, middle, this.threshold),
                      new PowTask(this.data, middle, this.end, this.threshold));
        }
    }
}
