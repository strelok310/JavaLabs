package Labs;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaLabs {

    static void main(String[] args) throws Exception {

    }


    static void task() {
        //кэширование результата функции
        Supplier<Double> func = () -> Math.random();
        Supplier<Double> func_const = () -> Math.random() * 100;

        Supplier<Function<Supplier, Object>> lambda = () -> {
            HashMap<Supplier, Object> cache = new HashMap<>();
            return (x) -> {
                if(!cache.containsKey(x)) cache.put(x, x.get());
                return cache.get(x);
            };
        };

        Function<Supplier, Object> f = lambda.get();

        System.out.println(f.apply(func));
        System.out.println(f.apply(func));
        System.out.println(f.apply(func));
        System.out.println(f.apply(func_const));
        System.out.println(f.apply(func));
        System.out.println(f.apply(func_const));
        System.out.println(f.apply(func));
    }
}
