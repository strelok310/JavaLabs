package Labs;

import Utils.Lambdas.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.*;

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
        taskTest13();
        taskTest14();
        taskTest15();
        taskTest16();
        taskTest17();
        taskTest18();
        taskTest19();
        taskTest20();
        taskTest21();
        taskTest22();
        taskTest23();
        taskTest24();
    }

    /**
     * Вернуть лямбду, которая печатает “Hello world!”
     */

    static CallFunction task1() {
        return () -> System.out.println("Hello world!");
    }

    static void taskTest1() {
        System.out.println(LINE);
        System.out.println("1) Return lambda that prints \"Hello world!\"\n");
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
        System.out.println("2) Greet someone\n");
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
        System.out.println("3) Add \"Have a nice day!\"\n");
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
        System.out.println("4) Return current time\n");
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
        System.out.println("5) Check email\n");

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
        System.out.println("6) Check email and length\n");

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
        System.out.println("7) Negate Check email and length\n");

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
        System.out.println("8) Check affiliation to Fibonacci\n");

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
        System.out.println("9) Get sum of two numbers\n");

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
        System.out.println("10) Get square sum of two numbers\n");

        System.out.println("(26 + 91)^2 = " + task10().apply(task9()).apply(26,91));
    }

    /**
     * Вернуть лямбду, которая возвращает сумму трех входных аргументов
     */

    static TernaryFunction<Integer> task11() {
        return (x,y,z) -> x + y + z;
    }

    static void taskTest11() {
        System.out.println(LINE);
        System.out.println("11) Get sum of three numbers\n");

        System.out.println("26 + 91 + 37 = " + task11().apply(26,91, 37));
    }

    /**
     * Вернуть лямбду, которая возводит число в квадрат
     */

    static UnaryOperator<TernaryFunction<Integer>> task12() {
        return x -> x.andThen(y -> y*y);
    }

    static void taskTest12() {
        System.out.println(LINE);
        System.out.println("12) Get square sum of three numbers\n");

        TernaryFunction<Integer> lambda11 = task11();
        TernaryFunction<Integer> lambda12 = task12().apply(lambda11);
        System.out.println("(26 + 91 + 37)^2 = " + lambda12.apply(26,91, 37));
    }

    /**
     * Добавить к предыдущей лямбде инкремент перед
     */

    static UnaryOperator<TernaryFunction<Integer>> task13() {
        return x -> x.andThen(y -> ++y);
    }

    static void taskTest13() {
        System.out.println(LINE);
        System.out.println("13) Add pre-increment to previous lambda\n");

        TernaryFunction<Integer> lambda11 = task11();
        TernaryFunction<Integer> lambda12 = task12().apply(lambda11);
        TernaryFunction<Integer> lambda13 = task13().apply(lambda12);
        System.out.println("(26 + 91 + 37)^2 = " + lambda13.apply(26,91, 37));
    }

    /**
     * Добавить к предыдущей лямбде декремент после
     */

    static UnaryOperator<TernaryFunction<Integer>> task14() {
        return x -> x.andThen(y -> y--);
    }

    static void taskTest14() {
        System.out.println(LINE);
        System.out.println("14) Add post-decrement to previous lambda\n");

        TernaryFunction<Integer> lambda11 = task11();
        TernaryFunction<Integer> lambda12 = task12().apply(lambda11);
        TernaryFunction<Integer> lambda13 = task13().apply(lambda12);
        TernaryFunction<Integer> lambda14 = task14().apply(lambda13);
        System.out.println("(26 + 91 + 37)^2 = " + lambda14.apply(26,91, 37));
    }

    /**
     * Вернуть лямбду, которая решает уравнение x^2 + 3x – 1 для заданного x
     */

    static UnaryOperator<Double> task15() {
        return x -> Math.pow(x, 2) + 3 * x - 1;
    }

    static void taskTest15() {
        System.out.println(LINE);
        System.out.println("15) Solve equation\n");

        System.out.println("x = 5.0");
        System.out.println("Result: " + task15().apply(5.0));
    }

    /**
     * Вернуть лямбду, которая решает уравнение (x^2 + 3x – 1)^2 + 3* (x^2 + 3x – 1) -1  для заданного x.
     * Необходимо решить задачу переиспользованием предыдущей лямбды.
     */

    static Function<UnaryOperator<Double>, Function<Double, Double>> task16() {
        return x -> x.andThen(x);
    }

    static void taskTest16() {
        System.out.println(LINE);
        System.out.println("16) Solve equation reusing previous lambda\n");

        UnaryOperator<Double> lambda15 = task15();
        Function<Double, Double> lambda16 = task16().apply(lambda15);

        System.out.println("x = 5.0");
        System.out.println("Result: " + lambda16.apply(5.0));
    }

    /**
     * Вернуть лямбду, которая решает уравнение x*x*x + y*y*y + z*z*z + u*u*u + v*v*v для заданных (x,y,z,u,v)
     */

    static FiveFunction<Integer> task17() {
        return (x,y,z,u,v) -> x*x*x + y*y*y + z*z*z + u*u*u + v*v*v;
    }

    static void taskTest17() {
        System.out.println(LINE);
        System.out.println("17) Solve equation x*x*x + y*y*y + z*z*z + u*u*u + v*v*v \n");

        System.out.println("Solve 1, 2, 3, 4 ,5");
        System.out.println("Result: " + task17().apply(1,2,3,4,5));
    }

    /**
     * Вернуть лямбду, которая возвращает лямбду, возводящую в квадрат по заданной степени
     */

    static Function<Integer, UnaryOperator<Integer>> task18() {
        return x -> y -> (int) Math.pow(y, x);
    }

    static void taskTest18() {
        System.out.println(LINE);
        System.out.println("18) Exponentiation Lambda \n");

        System.out.println("Result 2^10: " + task18().apply(10).apply(2));
        System.out.println("Result 3^5: " + task18().apply(5).apply(3));
    }

    /**
     * Вернуть лямбду, которая производит композицию двух функций. (f1,f2) => f2(f1(x)) .
     * Для этого реализовать функциональный интерфейс. Пример использования:
     */

    static BiFunction<Function<Integer,Integer>,
                      Function<Integer,Integer>,
                      Function<Integer,Integer>> task19() {
        return (x,y) -> y.compose(x);
    }

    static ComposeFunction<Integer> task19v2() {
        return (x,y) -> y.compose(x);
    }

    static void taskTest19() {
        System.out.println(LINE);
        System.out.println("19) Compose lambdas\n");

        System.out.println("Variant 1:");

        int result = task19().apply(x -> x*2, x -> x-1).apply(10);
        System.out.println(result);

        result = task19().apply(task19().apply(x -> x*x, x -> x-10),
                                task19().apply(x -> x/2, x -> x + 1))
                         .apply(10);
        System.out.println(result);

        //==============================================================================================================

        System.out.println("\nVariant 2:");

        result = task19v2().compose(x -> x*2, x -> x-1).apply(10);
        System.out.println(result);

        result = task19v2().compose(task19().apply(x -> x*x, x -> x-10),
                                    task19v2().compose(x -> x/2, x -> x + 1))
                           .apply(10);
        System.out.println(result);

    }

    /**
     * Вернуть лямбду, которая вернет полином, основанный на переданных коэффициентах
     */

    static BiFunction<Integer[], Integer, Integer> task20() {
        return (mas, x) -> {
            int sum = 0, exp = mas.length - 1;
            for(Integer item : mas) sum += item * Math.pow(x, exp--);
            return sum;
        };
    }

    static void taskTest20() {
        System.out.println(LINE);
        System.out.println("20) Return lambda that solve polynomial\n");

        Integer[] polynomial1 = {2, 3, 5};
        Integer[] polynomial2 = {-5, -10};
        Integer[] polynomial3 = {8};

        int result1 = task20().apply(polynomial1, 5);
        int result2 = task20().apply(polynomial2, 5);
        int result3 = task20().apply(polynomial3, 5);

        System.out.println("X = 5");
        System.out.println("Input:");
        System.out.println(Arrays.toString(polynomial1));
        System.out.println(Arrays.toString(polynomial2));
        System.out.println(Arrays.toString(polynomial3));

        System.out.println("\nOutput");
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    /**
     * Вернуть лямбду, которая кеширует результат некоторой функции, которая является входных аргументом
     */

    static Supplier<Function<Supplier, Object>> task21() {
        return () -> {
            HashMap<Supplier, Object> cache = new HashMap<>();
            return x -> {
                if(!cache.containsKey(x)) cache.put(x, x.get());
                return cache.get(x);
            };
        };
    }

    static void taskTest21() {
        System.out.println(LINE);
        System.out.println("21) Cache result of the function\n");

        Supplier<Double> func1 = () -> Math.random();
        Supplier<Double> func2 = () -> Math.random() * 100;

        Function<Supplier, Object> f = task21().get();

        System.out.println(f.apply(func1));
        System.out.println(f.apply(func1));
        System.out.println(f.apply(func2));
        System.out.println(f.apply(func1));
        System.out.println(f.apply(func2));
        System.out.println(f.apply(func1));
    }

    /**
     * Вернуть лямбду, которая запускает переданную функцию f, и в случае исключения перезапускает ее n раз
     */

    static Function<Integer, Consumer<ThrowingCall>> task22() {
        return x -> {
            Integer[] n = {x};
            return y -> {
                while(n[0]-- > 0) {
                    try { y.call(); return; }
                    catch (Exception e) { System.out.println("Exception: " + e.getMessage()); }
                }
            };
        };
    }

    static void taskTest22() {
        System.out.println(LINE);
        System.out.println("22) Rerun lambda in case of exception\n");

        task22().apply(9).accept(() -> { throw new Exception("error"); } );
    }

    /**
     * Вернуть лямбду, которая перед и после запуска переданной функции f, вызывает функцию логгера logFunc
     */

    static InterceptFunction<UnaryOperator<Integer>, Consumer> task23() {
        return (x,y) -> z -> {
            y.accept("Before: " + z);
            Integer result = x.apply(z);
            y.accept("After: " + result);
            return result;
        };
    }

    static void taskTest23() {
        System.out.println(LINE);
        System.out.println("23) Rerun lambda in case of exception\n");

        //Variant 1
        task23().intercept(x -> x + 1, System.out::println).apply(10);

        //Variant 2
        System.out.println();
        LoggedFunction<Integer, Integer> f = x -> x + 1;
        f.intercept(System.out::println).apply(10);
    }

    /**
     * Вернуть лямбду, представляющую из себя последовательность, которая начинается с переданного x
     */

    static Function<Long, Supplier> task24() {
        return x -> {
            Long[] value = {x};
            return () -> value[0]++;
        };
    }

    static void taskTest24() {
        System.out.println(LINE);
        System.out.println("24) Sequence generator\n");

        Supplier<Long> generator = task24().apply(100L);
        System.out.println(generator.get());
        System.out.println(generator.get());
        System.out.println(generator.get());
    }

}

















