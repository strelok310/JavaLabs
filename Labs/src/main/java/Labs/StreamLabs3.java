package Labs;

import Utils.Streams2.UserData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    }

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

        System.out.println("Input:");
        System.out.println(dataList);

        //==============================================================================================================

        UserData item = dataList.stream().findFirst().orElse(new UserData(3,"Picture.png", LocalDate.of(2018,6,15)));

        System.out.println("\nOutput:");
        System.out.println(item.toStringItem());

        //==============================================================================================================

        dataList.clear();
        item = dataList.stream().findFirst().orElse(new UserData(3,"Picture.png", LocalDate.of(2018,6,15)));

        System.out.println("\nOutput (not found):");
        System.out.println(item.toStringItem());
    }

    static void task2() {
        System.out.println(LINE);
        System.out.println("Return first element of array");
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
        //LocalDate date = LocalDate.of(2019,5,13);
        System.out.println("Current date: " + date);

        //==============================================================================================================

        UserData item = list.stream().filter((x) -> {
            if(x.name == "Info.txt" && x.date.isAfter(date)) return true;
            return false;
        }).findFirst().orElse(null);

        System.out.println("\nOutput:");
        System.out.println(item.toStringItem());

        //==============================================================================================================

        list.clear();
        item = list.stream().filter((x) -> {
            if(x.name == "Info.txt" && x.date.isAfter(date)) return true;
            return false;
        }).findFirst().orElse(null);

        System.out.println("\nOutput (not found):");
        if(item == null) System.out.println("Null");
        else System.out.println("not Null");

        Arrays.stream(int.class.getDeclaredMethods()).forEach((x) -> System.out.println(x));
    }

    static void task3() throws Exception {
        System.out.println(LINE);
        System.out.println("Count words and sort");
        Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI()));

        System.out.println("Intput:\nwordCount.txt");

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

        System.out.println("Output:");
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

    static void task4() {
        System.out.println(LINE);
        System.out.println("Create ArrayList from stream");
        Integer[] mas = {1,2,3,4,5,6,7,8};

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
                /*.sorted((x,y) -> {
                    int value = Math.toIntExact(y.getValue() - x.getValue());
                    if(value == 0) value = y.getKey().compareTo(x.getKey());
                    return value;
                })*/
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
}






































