package Utils.Concurrency;

import java.util.concurrent.RecursiveAction;
import java.util.function.Function;

public class LogTask extends RecursiveAction {
    int[] data;
    double[] result;
    int begin;
    int end;
    int threshold;
    Function<Integer, Double> func;

    public LogTask(int[] data, double[] result,
                   int begin, int end, int threshold,
                   Function<Integer, Double> func) {
        this.data = data;
        this.result = result;
        this.begin = begin;
        this.end = end;
        this.threshold = threshold;
        this.func = func;
    }

    public void compute() {
        if(this.end - this.begin <= threshold) {
            for(int i = this.begin; i < this.end; i++) result[i] = func.apply(data[i]);
        }
        else {
            int middle = (this.begin + this.end) / 2;
            invokeAll(new LogTask(this.data, this.result, this.begin, middle, this.threshold, this.func),
                    new LogTask(this.data, this.result, middle, this.end, this.threshold, this.func));
        }
    }
}
