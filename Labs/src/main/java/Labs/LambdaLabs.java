package Labs;

import Utils.Lambdas.CallFunction;
import Utils.Lambdas.TernaryFunction;
import Utils.Lambdas.TriFunction;
import Utils.Tmp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LambdaLabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        taskTest1();
        taskTest2();
        taskTest3();
        taskTest4();
        taskTest5();
        taskTest6();
        taskTest7();
        taskTest8();
        taskTest9();
        taskTest10();
        taskTest11();
        taskTest12();
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
        return x -> System.out.println("Hello " + x + "!");
    }

    static void taskTest2() {
        System.out.println(LINE);
        System.out.println("Greet someone");
        task2().accept("Roma");
    }

    /**
     * Вернуть лямбду, которая принимает в качестве аргумента предыдущую лямбду, и добавляет вывод
     * “Have a nice day!” после выполнения лямбды
     */

    static UnaryOperator<Consumer<String>> task3() {
        return x -> x.andThen(y -> System.out.println("Have a nice day!"));
    }

    static void taskTest3() {
        System.out.println(LINE);
        System.out.println("Add \"Have a nice day!\"");
        task3().apply(task2()).accept("Roma");
    }

    /**
     * Вернуть лямбду, которая возвращает текущее время
     */

    static Supplier<LocalTime> task4() {
        return () -> LocalTime.now();
    }

    static void taskTest4() {
        System.out.println(LINE);
        System.out.println("Return current time");
        System.out.println("Current time: " + task4().get());
    }

    /**
     * Вернуть лямбду, которая проверяет строку на наличие в ней email. Под email понимать любую строку,
     * которая ввыглядит как <любые символы>@<любые символы>.<любые символы>
     */

    static Predicate<String> task5() {
        //return x -> x.matches("[a-zA-Z0-9]+@(\\w+\\.[a-zA-Z]+)+");
        return x -> x.matches("[\\w&&[^-_]](-?[\\w&&[^-]]+)*@(\\w+\\.[a-zA-Z]+)+");
    }

    static void taskTest5() {
        System.out.println(LINE);
        System.out.println("Check email");

        String[] emails = {
            "123@123.by",
            "asdQWE@gmail.com",
            "123_456@gmail.com",
            "not@email@some.com",
            "some|random\"characters.com",
            "panda@zoo",
            "snowfall@winter.123",
            "new-againt@mail.ru",
            "games@game.informer.com",
            "new-@domain.org",
            "-last@some.dt",
            "a@b.com"
        };

        Predicate<String> matcher = task5();
        for(String item : emails) {
            System.out.println("Email: " + item + "\t\tCorrect: " + matcher.test(item));
        }
    }

    /**
     * К предыдущей лямбде добавить проверку на длину строки (> 10)
     */

    static UnaryOperator<Predicate<String>> task6() {
        return x -> x.and(y -> y.length() > 10);
    }

    static void taskTest6() {
        System.out.println(LINE);
        System.out.println("Check email and length");

        String[] emails = {
                "123@123.by",
                "asdQWE@gmail.com",
                "123_456@gmail.com",
                "not@email@some.com",
                "some|random\"characters.com",
                "panda@zoo",
                "snowfall@winter.123",
                "new-againt@mail.ru",
                "games@game.informer.com",
                "new-@domain.org",
                "-last@some.dt",
                "a@b.com"
        };

        Predicate<String> matcher = task6().apply(task5());
        //Predicate<String> matcher = task5().and(task6());
        for(String item : emails) {
            System.out.println("Email: " + item + "\t\tCorrect: " + matcher.test(item));
        }
    }

    /**
     * К предыдущей лямбде добавить проверку на длину строки (> 10)
     */

    static UnaryOperator<Predicate<String>> task7() {
        return x -> x.negate();
    }

    static void taskTest7() {
        System.out.println(LINE);
        System.out.println("Negate Check email and length");

        String[] emails = {
                "123@123.by",
                "asdQWE@gmail.com",
                "123_456@gmail.com",
                "not@email@some.com",
                "some|random\"characters.com",
                "panda@zoo",
                "snowfall@winter.123",
                "new-againt@mail.ru",
                "games@game.informer.com",
                "new-@domain.org",
                "-last@some.dt",
                "a@b.com"
        };

        Predicate<String> matcher = task7().apply(task6().apply(task5()));
        //Predicate<String> matcher = task5().and(task6()).negate(task7());
        for(String item : emails) {
            System.out.println("Email: " + item + "\t\tCorrect: " + matcher.test(item));
        }
    }

    /**
     * Вернуть лямбду, которая проверяет число на принадлежность к ряду Фибоначчи.
     * Лямбда должна запоминать ранее вычисленные значения.
     */

    static Supplier<Predicate<Integer>> task8() {
        return () -> {
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,1));
            return (x) -> {
                while(list.get(list.size()-1) < x) {
                    int id1 = list.size()-2;
                    int id2 = list.size()-1;
                    list.add(list.get(id1) + list.get(id2));
                }
                return list.contains(x);
            };
        };
    }

    static void taskTest8() {
        System.out.println(LINE);
        System.out.println("Check affiliation to Fibonacci");

        Predicate<Integer> fibonacci = task8().get();
        System.out.println("13 (t): " + fibonacci.test(13));
        System.out.println("5 (t): " + fibonacci.test(5));
        System.out.println("15 (f): " + fibonacci.test(15));
        System.out.println("55 (t): " + fibonacci.test(55));
        System.out.println("34 (t): " + fibonacci.test(34));
        System.out.println("26 (f): " + fibonacci.test(26));
    }

    /**
     * Вернуть лямбду, которая возвращает сумму двух входных аргументов
     */

    static BiFunction<Integer,Integer,Integer> task9() {
        return (x, y) -> x + y;
    }

    static void taskTest9() {
        System.out.println(LINE);
        System.out.println("Get sum of two numbers");

        System.out.println("26 + 91 = " + task9().apply(26,91));
    }

    /**
     * К предыдущей лямбде добавить возведение в квадрат
     */

    static UnaryOperator<BiFunction<Integer,Integer,Integer>> task10() {
        return x -> x.andThen(y -> y*y);
    }

    static void taskTest10() {
        System.out.println(LINE);
        System.out.println("Get square sum of two numbers");

        System.out.println("(26 + 91)^2 = " + task10().apply(task9()).apply(26,91));
    }

    /**
     * Вернуть лямбду, которая возвращает сумму трех входных аргументов
     */

    static TriFunction<Integer,Integer,Integer,Integer> task11() {
        return (x,y,z) -> x + y + z;
    }

    static void taskTest11() {
        System.out.println(LINE);
        System.out.println("Get sum of three numbers");

        System.out.println("26 + 91 + 37 = " + task11().apply(26,91, 37));
    }

    /**
     * Вернуть лямбду, которая возводит число в квадрат
     */

    static UnaryOperator<TriFunction<Integer,Integer,Integer,Integer>> task12() {
        return x -> x.andThen(y -> y*y);
    }

    static void taskTest12() {
        System.out.println(LINE);
        System.out.println("Get square sum of three numbers");

        System.out.println("(26 + 91 + 37)^2 = " + task12().apply(task11()).apply(26,91, 37));
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
