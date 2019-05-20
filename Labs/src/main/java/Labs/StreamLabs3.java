package Labs;

import Utils.Streams2.UserData;
import Utils.Streams3.Combinatorics;
import Utils.Streams3.UserLogged;

import java.awt.geom.Point2D;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamLabs3 {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String args[]) throws Exception {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
        task23();
        task24();
        task25();
        task26();
    }

    /**
     * Дан массив объектов (поля: id – Long, name - String, date - LocalDate ) Найти первый элемент в массиве по
     * заданному имени. В случае отсутствия такового вернуть нового пользователя.
     */

    static void task1() {
        System.out.println(LINE);
        System.out.println("Return first element of array");
        ArrayList<UserData> dataList = new ArrayList<>(Arrays.asList(
                new UserData(15,"Image.jpg", LocalDate.of(2019,5,1)),
                new UserData(9,"Star_Wars.mkv", LocalDate.of(2019,7,13)),
                new UserData(100,"Info.txt", LocalDate.of(2019,5,1)),
                new UserData(85,"Labs.docx", LocalDate.of(2022,5,5)),
                new UserData(100,"secret.rar", LocalDate.of(2019,5,1))
        ));
        String str = "Info.txt";

        System.out.println("Input:");
        System.out.println(dataList);
        System.out.println("Search for: " + str);


        //==============================================================================================================

        UserData item = dataList.stream()
                                .filter((x) -> x.getName().equals(str))
                                .findFirst().orElse(new UserData(3,"Picture.png", LocalDate.of(2018,6,15)));

        System.out.println("\nOutput:");
        System.out.println(item.toStringItem());

        //==============================================================================================================

        dataList.clear();
        item = dataList.stream()
                       .filter((x) -> x.getName().equals(str))
                       .findFirst().orElse(new UserData(3,"Picture.png", LocalDate.of(2018,6,15)));

        System.out.println("\nOutput (not found):");
        System.out.println(item.toStringItem());
    }

    /**
     * Дан массив объектов (поля: id – Long, name - String, date - LocalDate ) Найти любой элемент в массиве по
     * заданному имени с date больше текущей даты. В случае отсутствия такового вернуть null.
     */

    static void task2() {
        System.out.println(LINE);
        System.out.println("Return any element of array with date after current");
        ArrayList<UserData> list = new ArrayList<>(Arrays.asList(
                new UserData(15,"Image.jpg", LocalDate.of(2019,5,1)),
                new UserData(9,"Star_Wars.mkv", LocalDate.of(2019,7,13)),
                new UserData(100,"Info.txt", LocalDate.of(2020,5,17)),
                new UserData(85,"Labs.docx", LocalDate.of(2022,5,5)),
                new UserData(113,"secret.rar", LocalDate.of(2019,5,1))
        ));
        String str = "Info.txt";
        LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(2019,5,13);

        System.out.println("Input:");
        System.out.println(list);
        System.out.println("Search for: " + str);
        System.out.println("Current date: " + date);

        //==============================================================================================================

        UserData item = list.stream()
                            .filter((x) -> x.getName().equals(str) && x.getDate().isAfter(date))
                            .findAny().orElse(null);

        System.out.println("\nOutput:");
        System.out.println(item.toStringItem());

        //==============================================================================================================

        list.clear();
        item = list.stream()
                   .filter((x) -> x.getName().equals(str) && x.getDate().isAfter(date))
                   .findAny().orElse(null);

        System.out.println("\nOutput (not found):");
        if(item == null) System.out.println("Null");
        else System.out.println("not Null");
    }

    /**
     * Дан текстовый файл по пути ./payload/java8/stream/wordCount.txt. Вернут стрим со словами, отсортированными
     * по частоте встречи по убыванию.
     */

    static void task3() throws Exception {
        System.out.println(LINE);
        System.out.println("Count words and sort");
        Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI()));

        System.out.println("Input:\nwordCount.txt");

        //Variant 1
        System.out.println("\nVariant 1:");
        HashMap<String, Integer> map = new HashMap<>();
        String[] words = fileStream
            .flatMap((x) -> Arrays.stream(x.split(" ")))
            .peek((x) -> {
                if(map.containsKey(x)) map.replace(x, map.get(x) + 1);
                else map.put(x, 1);
            })
            .distinct()
            .sorted((x, y) -> map.get(y) - map.get(x))
            .toArray(String[]::new);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(words));
        System.out.println("Word frequency:");
        System.out.println(map);

        //==============================================================================================================

        //Variant 2
        System.out.println("\nVariant 2:");
        fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI()));
        Stream<String> res = fileStream.flatMap((x) -> Arrays.stream(x.split(" ")))
                                            .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                                            .entrySet()
                                            .stream()
                                            .sorted((x,y) -> Integer.parseInt(String.valueOf(y.getValue() - x.getValue())))
                                            .map((x) -> x.getKey());
        System.out.println(Arrays.toString(res.toArray(String[]::new)));

    }

    /**
     * Дан стрим, преобразовать его в ArrayList
     */

    static void task4() {
        System.out.println(LINE);
        System.out.println("Create ArrayList from stream");
        Integer[] mas = {1,2,3,4,5,6,7,8};

        System.out.println("Input:");
        System.out.println(Arrays.toString(mas));

        System.out.println("\nOutput:");
        System.out.println("\nVariant 1:");
        ArrayList<Integer> list = new ArrayList<>(Arrays.stream(mas).collect(Collectors.toList()));
        System.out.println(list);

        System.out.println("\nVariant 2:");
        list = Arrays.stream(mas).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);

        System.out.println("\nVariant 3:");
        list = Arrays.stream(mas).collect(Collector.of( ArrayList::new,
                                                        (x, item) -> x.add(item),
                                                        (x, y) -> { x.addAll(y); return x; }));
        System.out.println(list);
    }

    /**
     * Дан список слов. Используя стрим вернуть список, из которого исключены слова длиннее заданного значения.
     */

    static void task5() {
        System.out.println(LINE);
        System.out.println("Filter string list by length");

        ArrayList<String> list = new ArrayList<>(Arrays.asList(
                "Mind",
                "Desert",
                "Snowfall",
                "Rendering",
                "Harem",
                "Fiancee",
                "Overlord",
                "Professional",
                "Coal",
                "Art",
                "Meltdown"
        ));
        System.out.println("Input:");
        System.out.println(list);

        System.out.println("\nOutput:");

        System.out.println("Words with length less then 6:");
        Stream<String> stream = list.stream().filter((x) -> x.length() <= 5);
        System.out.println(stream.collect(Collectors.toList()));

        System.out.println("Words with length less then 9:");
        stream = list.stream().filter((x) -> x.length() <= 8);
        System.out.println(stream.collect(Collectors.toList()));
    }

    /**
     * Дан текстовый файл по пути ./payload/java8/stream/wordCount.txt. Вернуть HashMap, где ключ – слово, а
     * значение – количество встреч слова в тексте. HashMap должен хранить только 10 самых частых слов
     */

    static void task6() throws Exception {
        System.out.println(LINE);
        System.out.println("Return HashMap with words and their count");
        Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI()));

        System.out.println("Input:\nwordCount.txt");

        HashMap<String, Long> map = new HashMap<>(fileStream.flatMap((x) -> Arrays.stream(x.split(" ")))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((x,y) -> Math.toIntExact(y.getValue() - x.getValue()))
                .limit(10)
                .collect(Collectors.toMap((x) -> x.getKey(), (x) -> x.getValue())) );

        /*HashMap<String, Long> map = fileStream.flatMap((x) -> Arrays.stream(x.split(" ")))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((x,y) -> Math.toIntExact(y.getValue() - x.getValue()))
                .limit(10)
                .collect(Collector.of(  HashMap::new,
                                        (x, item) -> x.put(item.getKey(), item.getValue()),
                                        (x, y) -> { x.putAll(y); return x; }) );*/

        System.out.println("\nOutput:");
        System.out.println(map);
    }

    /**
     * Дан текстовый файл по пути ./payload/java8/stream/wordCount.txt. Вернуть HashMap, где ключ – слово, а
     * значение – вероятность встречи слова в тексте. HashMap должен хранить только 10 самых частых слов
     */

    static void task7() throws Exception {
        System.out.println(LINE);
        System.out.println("Return HashMap with words and their probability");
        Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI()));

        System.out.println("Input:\nwordCount.txt");

        HashMap<String, Long> map = new HashMap<>(fileStream.flatMap((x) -> Arrays.stream(x.split(" ")))
                                            .collect(Collectors.groupingBy(String::toString, Collectors.counting())));

        int words = map.entrySet()
                        .stream()
                        .mapToInt((x) -> Math.toIntExact(x.getValue()))
                        .sum();

        HashMap<String, Float> freqMap = new HashMap<>(map.entrySet()
                .stream()
                .sorted((x,y) -> Math.toIntExact(y.getValue() - x.getValue()))
                .limit(10)
                .collect(Collectors.toMap((x) -> x.getKey(), (x) -> x.getValue() * 100 / Float.valueOf(words))) );

        System.out.println("\nOutput:");
        System.out.println(freqMap);
    }

    /**
     * Посчитать количество элементов в массиве
     */

    static void task8() {
        System.out.println(LINE);
        System.out.println("Return size of array");
        Integer[] mas = {1,2,3,4,5,6,7,8};

        System.out.println("Input:");
        System.out.println(Arrays.toString(mas));

        System.out.println("\nOutput:");
        long size = Arrays.stream(mas).count();
        System.out.println(size);
    }

    /**
     * Дан массив объектов (поля: id – Long, name - String, date - LocalDate ). Посчитать количество объектов, у
     * которых date лежит в пределах текущего года.
     */

    static void task9() {
        System.out.println(LINE);
        System.out.println("Return number of elements with current year");
        ArrayList<UserData> list = new ArrayList<>(Arrays.asList(
                new UserData(15,"Image.jpg", LocalDate.of(2019,5,1)),
                new UserData(9,"Star_Wars.mkv", LocalDate.of(2019,7,13)),
                new UserData(100,"Info.txt", LocalDate.of(2020,5,17)),
                new UserData(85,"Labs.docx", LocalDate.of(2022,5,5)),
                new UserData(113,"secret.rar", LocalDate.of(2019,5,1))
        ));

        System.out.println("Input:");
        System.out.println(list);

        LocalDate date = LocalDate.now();
        long count = list.stream().filter((x) -> x.getDate().getYear() == date.getYear()).count();
        System.out.println("\nOutput:\n" + count);
    }

    /**
     * Дан массив объектов (поля: id – Long, name - String, date - LocalDate ). Убедиться, есть ли в массиве
     * пользователь с name = admin
     */

    static void task10() {
        System.out.println(LINE);
        System.out.println("Check whether array contains name \"admin\" or not");
        ArrayList<UserData> list = new ArrayList<>(Arrays.asList(
                new UserData(15,"Image.jpg", LocalDate.of(2019,5,1)),
                new UserData(9,"Star_Wars.mkv", LocalDate.of(2019,7,13)),
                new UserData(100,"admin", LocalDate.of(2020,5,17)),
                new UserData(85,"Labs.docx", LocalDate.of(2022,5,5)),
                new UserData(113,"secret.rar", LocalDate.of(2019,5,1))
        ));

        System.out.println("Input:");
        System.out.println(list);

        boolean adminPresented = list.stream().anyMatch((x) -> x.getName().equals("admin"));
        System.out.println("\nOutput:\n" + adminPresented);

        list.remove(2);
        System.out.println("\nInput:");
        System.out.println(list);

        adminPresented = list.stream().anyMatch((x) -> x.getName().equals("admin"));
        System.out.println("\nOutput:\n" + adminPresented);
    }

    /**
     * Дан массив объектов (поля: id – Long, name - String, date - LocalDate ). Убедиться, лежат ли date всех объектов в
     * пределах текущего года.
     */

    static void task11() {
        System.out.println(LINE);
        System.out.println("Check whether all elements of ArrayList contains date with current year");
        ArrayList<UserData> list = new ArrayList<>(Arrays.asList(
                new UserData(15,"Image.jpg", LocalDate.of(2019,5,1)),
                new UserData(9,"Star_Wars.mkv", LocalDate.of(2019,7,13)),
                new UserData(100,"Info.txt", LocalDate.of(2020,5,17)),
                new UserData(85,"Labs.docx", LocalDate.of(2022,5,5)),
                new UserData(113,"secret.rar", LocalDate.of(2019,5,1))
        ));

        System.out.println("Input:");
        System.out.println(list);

        LocalDate date = LocalDate.now();
        boolean thisYear =  list.stream().allMatch((x) -> x.getDate().getYear() == date.getYear());
        System.out.println("\nOutput:\n" + thisYear);

        list.get(2).setDate(LocalDate.of(2019,5,17));
        list.get(3).setDate(LocalDate.of(2019,5,5));
        System.out.println("\nInput:");
        System.out.println(list);

        thisYear =  list.stream().allMatch((x) -> x.getDate().getYear() == date.getYear());
        System.out.println("\nOutput:\n" + thisYear);
    }

    /**
     * Дан массив. Найти минимальное число.
     */

    static void task12() {
        System.out.println(LINE);
        System.out.println("Return minimal element of array");
        Integer[] mas = {6, 43, 3, 87, 6, 16, 4, 21, 13, 38};

        System.out.println("Input:");
        System.out.println(Arrays.toString(mas));

        Integer min = Arrays.stream(mas).min(Integer::compareTo).get();
        System.out.println("\nOutput:\n" + min);
    }

    /**
     * Дан массив. Найти максимальное число.
     */

    static void task13() {
        System.out.println(LINE);
        System.out.println("Return maximal element of array");
        Integer[] mas = {6, 43, 3, 87, 6, 16, 4, 21, 13, 38};

        System.out.println("Input:");
        System.out.println(Arrays.toString(mas));

        Integer max = Arrays.stream(mas).max(Integer::compareTo).get();
        System.out.println("\nOutput:\n" + max);
    }

    /**
     * Дан массив точек (поля: x – float, y - float). Дана точка X с ненулевыми координатами. Найти ближайшую точку
     * из массива к X.
     */

    static void task14() {
        System.out.println(LINE);
        System.out.println("Return the nearest point to X");
        ArrayList<Point2D.Float> list = new ArrayList<>(Arrays.asList(
                new Point2D.Float(1.3f,6.4f),
                new Point2D.Float(10.5f,3.5f),
                new Point2D.Float(1.1f,4.7f),
                new Point2D.Float(10.3f,4.7f),
                new Point2D.Float(1.1f,7.6f),
                new Point2D.Float(1.5f,3.2f),
                new Point2D.Float(5.1f,8.5f),
                new Point2D.Float(6.3f,2.4f),
                new Point2D.Float(1.1f,5.7f),
                new Point2D.Float(8.4f,8.9f),
                new Point2D.Float(6.9f,4.8f)
        ));
        Point2D.Float point = new Point2D.Float(5.0f, 5.0f);

        System.out.println("Input:");
        list.stream().forEach((x) -> System.out.println(x));
        System.out.println("X: " + point);

        Point2D.Float nearest = list.stream().min((p1, p2) -> Double.compare(point.distance(p1), point.distance(p2))).get();
        System.out.println("\nOutput:");
        System.out.println(nearest);
    }

    /**
     * Дан массив точек (поля: x – float, y - float). Дана точка X с ненулевыми координатами. Найти наиболее
     * удаленную точку из массива к X.
     */

    static void task15() {
        System.out.println(LINE);
        System.out.println("Return the furthest point to X");
        ArrayList<Point2D.Float> list = new ArrayList<>(Arrays.asList(
                new Point2D.Float(1.3f,6.4f),
                new Point2D.Float(10.5f,3.5f),
                new Point2D.Float(1.1f,4.7f),
                new Point2D.Float(10.3f,4.7f),
                new Point2D.Float(1.1f,7.6f),
                new Point2D.Float(1.5f,3.2f),
                new Point2D.Float(5.1f,8.5f),
                new Point2D.Float(6.3f,2.4f),
                new Point2D.Float(1.1f,5.7f),
                new Point2D.Float(8.4f,8.9f),
                new Point2D.Float(6.9f,4.8f)
        ));
        Point2D.Float point = new Point2D.Float(5.0f, 5.0f);

        System.out.println("Input:");
        list.stream().forEach((x) -> System.out.println(x));
        System.out.println("X: " + point);

        Point2D.Float furthest = list.stream().max((p1, p2) -> Double.compare(point.distance(p1), point.distance(p2))).get();
        System.out.println("\nOutput:");
        System.out.println(furthest);
    }

    /**
     * Сгенерировать случайную последовательность из 100000 чисел с экспоненциальным распределением.
     * Рассчитать минимальное, максимальное, среднее, сумму, мат. ожидание, дисперсию, ср. квадратичное отклонение,
     * моду, медиану
     */

    static void task16() {
        System.out.println(LINE);
        System.out.println("Exponential Random stream");
        Supplier<Integer> expGenerator = () -> Math.toIntExact(Math.round( -1000 * Math.log(1 - Math.random()) ));

        System.out.println("Input:\nExponential Random generator");

        final int SIZE = 100000;

        IntSummaryStatistics stat = Stream.generate(expGenerator)
                                            .limit(SIZE)
                                            .collect(Collectors.summarizingInt((x) -> x));
        double variance = Stream.generate(expGenerator)
                                .limit(SIZE)
                                .mapToDouble(Double::valueOf)
                                .reduce(0.0, (x,y) -> x += Math.pow(y - stat.getAverage(), 2) / (SIZE - 1));
        double median = Stream.generate(expGenerator)
                                .limit(SIZE)
                                .sorted(Integer::compareTo)
                                .skip(SIZE / 2 - 1)
                                .limit(2)
                                .mapToInt(Integer::intValue)
                                .sum() / 2.0;

        int mode = Stream.generate(expGenerator)
                            .limit(SIZE)
                            .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                            .entrySet()
                            .stream()
                            .sorted((x,y) -> Long.compare(x.getValue(), y.getValue()))
                            .findFirst()
                            .get()
                            .getKey();

        double deviation = Math.sqrt(variance);

        System.out.println("\nOutput:");
        System.out.println("Min: " + stat.getMin());
        System.out.println("Max: " + stat.getMax());
        System.out.println("Average: " + stat.getAverage());
        System.out.println("Sum: " + stat.getSum());

        System.out.println("Expected value (M): " + stat.getAverage());
        System.out.println("Variance (D): " + variance);
        System.out.println("Median: " + median);
        System.out.println("Mode: " + mode);
        System.out.println("Deviation: " + deviation);


        System.out.println("\nNote: Lambda = 0.001");
    }

    /**
     * Дан массив строк. Вернуть массив всех возможных комбинаций.
     */

    static void task17() {
        System.out.println(LINE);
        System.out.println("Return all combinations of strings");
        String[] str = {"A", "B", "C", "D"};

        System.out.println("Input:");
        System.out.println(Arrays.toString(str));

        System.out.println("\nOutput:");

        System.out.println("\nVariant 1");
        Combinatorics.combine(str)
                     .forEach(System.out::println);

        System.out.println("\nVariant 2");
        Combinatorics.combineInt(str)
                     .forEach(System.out::println);
    }

    /**
     * Дан массив уникальных символов. Вернуть строку, являющуюся конкатенацией всех возможных вариантов
     * перестановок для массива.
     */

    static void task18() {
        System.out.println(LINE);
        System.out.println("Return all combinations of unique symbols in string");
        String[] str = {"A", "B", "C"};

        System.out.println("Input:");
        System.out.println(Arrays.toString(str));

        String result = Combinatorics.combineInt(str)
                                     .map((x) -> x.stream()
                                                  .reduce("", (z, w) -> z + w))
                                     .reduce("", (z,w) -> z + w);

        System.out.println("\nOutput:");
        System.out.println(result);
    }

    /**
     * Дано уравнение. Попытаться найти минимум и максимум на заданном диапазоне.
     */

    static void task19() {
        System.out.println(LINE);
        System.out.println("Return min and max values on giving range");

        final int BEGIN = 1;
        final int END = 5;

        System.out.println("Input: ");
        System.out.format("Range from %s to %s\n", BEGIN, END);

        DoubleSummaryStatistics stat =  DoubleStream.iterate(Double.valueOf(BEGIN), (x) -> x + 0.2)
                    .limit(Math.round((END - BEGIN) / 0.2))
                    .map((x) -> Math.pow(x, Math.exp(x)) - 3 * Math.sqrt(2 * Math.round(Math.log(x)/Math.log(3)))
                                                            / (Math.pow(x, 2) + Math.pow(5, x)) )
                    .summaryStatistics();

        System.out.println("\nOutput:");
        System.out.println("Minimal: " + stat.getMin());
        System.out.println("Maxiamal: " + stat.getMax());
    }

    /**
     * Рассчитать интеграл на заданном диапазоне с заданным шагом.
     */

    static void task20() {
        System.out.println(LINE);
        System.out.println("Return Integral");

        final double BEGIN = 1;
        final double END = 10;
        final double STEP = 0.001;

        System.out.println("Input: ");
        System.out.format("Range from %s to %s, step %s\n", BEGIN, END, STEP);

        double sum =  STEP * DoubleStream.iterate(Double.valueOf(BEGIN), (x) -> x + STEP)
                .limit(Math.round((END - BEGIN) / STEP))
                .map((x) ->  3 * Math.sqrt(2 * Math.round(Math.log(x)/Math.log(2)))
                             / (Math.pow(x, 2) + Math.pow(5, x)) )
                .sum();

        System.out.println("\nOutput:");
        System.out.println("Integral: " + sum);
    }

    /**
     * Применить к каждому элементу массива заданную функцию.
     */

    static void task21() {
        System.out.println(LINE);
        System.out.println("Apply function to all elements of stream");
        StringBuilder[] str = {
            new StringBuilder("Snowfall"),
            new StringBuilder("Overlord"),
            new StringBuilder("Meltdown"),
            new StringBuilder("Nuclear"),
            new StringBuilder("Keanu")
        };

        System.out.println("Input:");
        System.out.println(Arrays.toString(str));

        Stream<StringBuilder> stream = Arrays.stream(str).peek((x) -> x.append("_new"));

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(stream.toArray(StringBuilder[]::new)));
    }

    /**
     * Дан массив строк. Некоторые элементы могут содержать целочисленные или вещественные числа. Необходимо
     * вернуть примитимный стрим целых чисел ( вещественные необходимо округлить вниз)
     */

    static void task22() {
        System.out.println(LINE);
        System.out.println("Return IntStream");
        String[] str = {"A", "B123", "11", "A.5C", "Hello3.14world"};

        System.out.println("Input:");
        System.out.println(Arrays.toString(str));

        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?", Pattern.CASE_INSENSITIVE);

        IntStream stream = Arrays.stream(str)
                                      .filter((x) -> x.matches(".*\\d+.*"))
                                      .map((x) -> {
                                          Matcher matcher = pattern.matcher(x);
                                          if(matcher.find()) return Double.valueOf(x.substring(matcher.start(), matcher.end()));
                                          return null;
                                      })
                                      .filter((x) -> x != null)
                                      .mapToInt((x) -> Math.toIntExact(Math.round(x)));

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(stream.toArray()));
    }

    /**
     * Вернуть сумму нечетных чисел
     */

    static void task23() {
        System.out.println(LINE);
        System.out.println("Return sum of odd elements");
        int[] mas = {1,2,3,4,5,6,7,8,9,10};

        System.out.println("Input:");
        System.out.println(Arrays.toString(mas));

        int sum = Arrays.stream(mas)
                        .filter((x) -> x % 2 != 0)
                        .sum();

        System.out.println("\nOutput:");
        System.out.println(sum);
    }

    /**
     * Дан массив объектов (поля: id – Long, name - String, logged - Boolean ). Разделить на две части по флагу logged
     */

    static void task24() {
        System.out.println(LINE);
        System.out.println("Group array by logged flag");
        ArrayList<UserLogged> list = new ArrayList<>(Arrays.asList(
                new UserLogged(15,"Snowfall", true),
                new UserLogged(9,"Overlord", false),
                new UserLogged(100,"Meltdown", true),
                new UserLogged(85,"Nuclear", true),
                new UserLogged(99,"Keanu", false)
        ));

        System.out.println("Input:");
        System.out.println(list);

        Map result = list.stream().collect(Collectors.groupingBy(UserLogged::isLogged));

        System.out.println("\nOutput");
        System.out.println(result);
    }

    /**
     * Дан массив объектов (поля: id – Long, name - String, logged - Boolean ). Разделить по алфавиту. Если буква
     * содержит менее 5 записей, объединить их (например А-Г). Вернуть HashMap, где ключ: буква или диапазон, значение:
     * список пользователей, у которых имя лежит в заданном диапазоне (или на заданную букву)
     */

    static void task25() {
        System.out.println(LINE);
        System.out.println("Return first element of array");
        ArrayList<UserLogged> list = new ArrayList<>(Arrays.asList(
                new UserLogged(15,"Snowfall", true),
                new UserLogged(1,"Sun", true),
                new UserLogged(23,"Snow", true),
                new UserLogged(9,"Overlord", false),
                new UserLogged(100,"Meltdown", true),
                new UserLogged(89,"Steam", true),
                new UserLogged(85,"Nuclear", true),
                new UserLogged(37,"Slime", false),
                new UserLogged(99,"Keanu", false)
        ));

        System.out.println("Input:");
        System.out.println(list);

        HashMap<String, ArrayList<String>> result = list.stream()
                .map(UserLogged::getName)
                .collect(Collectors.groupingBy((x) -> x.substring(0,1)))
                .entrySet()
                .stream()
                .map((x) -> {
                    HashMap<String, ArrayList<String>> map = new HashMap<>();
                    map.put(x.getKey(), new ArrayList<>(x.getValue()));
                    return map;
                })
                .reduce(new HashMap<String, ArrayList<String>>(), (map, x) -> {
                    if(x.get(x.keySet().toArray()[0]).size() >= 5) map.putAll(x);
                    else {
                        Map.Entry<String, ArrayList<String>> entry = map.entrySet()
                                                                        .stream()
                                                                        .filter((y) -> y.getValue().size() < 5)
                                                                        .findFirst().orElse(null);
                        if(entry == null) map.putAll(x);
                        else {
                            String key = entry.getKey();
                            String key2 = (String) x.keySet().toArray()[0];

                            ArrayList<String> value = entry.getValue();
                            value.addAll(x.get(key2));

                            map.remove(key);
                            if(key.length() != 1) key = key.substring(0,1);
                            if(key.compareTo(key2) > 0) key = key + "-" + key2;
                            else key = key2 + "-" + key;
                            map.put(key, value);
                        }
                    }
                    return map;
                });

        System.out.println("\nOutput");
        System.out.println(result);
    }

    /**
     * Дан список списков строк. Вернуть строку по шаблону
     */

    static void task26() {
        System.out.println(LINE);
        System.out.println("Return html list");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(
                "Mind",
                "Desert",
                "Snowfall",
                "Rendering",
                "Harem",
                "Fiancee",
                "Overlord",
                "Professional",
                "Coal",
                "Art",
                "Meltdown"
        ));
        System.out.println("Input:");
        System.out.println(list);

        String result = list.stream().collect(Collectors.joining("</li>\n</ul>\n<ul>\n\t<li>", "<div>\n<ul>\n\t<li>", "</li>\n</ul>\n</div>"));

        System.out.println("\nOutput:");
        System.out.println(result);
    }
}
