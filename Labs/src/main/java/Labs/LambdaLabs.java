package Labs;

import Utils.Lambdas.CallFunction;
import Utils.Tmp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaLabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        taskTest1();
        taskTest2();
    }

    /**
     * Вернуть лямбду, которая печатает “Hello world!”
     */

    static CallFunction task1() {
        return () -> System.out.println("Hello world!");
    }

    static void taskTest1() {
        System.out.println(LINE);
        System.out.println("Return lambda that prints \"Hello world!\"");
        task1().call();
    }

    /**
     * Вернуть лямбду, которая поприветствует того, чье имя будет передано первым аргументом
     */

    static Consumer<String> task2() {
        return x -> System.out.println("Hello " + x + " !");
    }

    static void taskTest2() {
        System.out.println(LINE);
        System.out.println("Greet someone");
        task2().accept("Roman");
    }



    //==============================================================================

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

    static void asd() {
        Consumer<String> wtf = (s) -> System.out.println("hi, "+s);

        /*Consumer<Consumer<String>> qwe = (x) -> {
            x.accept("Darth Vader");
            System.out.println("Have a nice day!");
        };*/

        Consumer<Tmp> qwe = (x) -> {
            x.apply();
            System.out.println("Have a nice day!");
        };

        Function<String, Tmp> func = (x) -> () -> System.out.println(x);

        qwe.accept(func.apply("Darth Vader"));
    }
}
