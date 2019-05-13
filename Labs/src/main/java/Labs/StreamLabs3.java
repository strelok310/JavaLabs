package Labs;

import Utils.Streams2.UserData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLabs3 {
    public static void main(String args[]) throws Exception {
        task1();
        task2();
        task3();
        task4();
    }

    static void task1() {
        System.out.println("\nReturn first element of array");
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

        System.out.println("Output:");
        System.out.println(item.toStringItem());

        //==============================================================================================================

        dataList.clear();
        item = dataList.stream().findFirst().orElse(new UserData(3,"Picture.png", LocalDate.of(2018,6,15)));

        System.out.println("Output (not found):");
        System.out.println(item.toStringItem());
    }

    static void task2() {
        System.out.println("\nReturn first element of array");
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

        System.out.println("Output:");
        System.out.println(item.toStringItem());

        //==============================================================================================================

        list.clear();
        item = list.stream().filter((x) -> {
            if(x.name == "Info.txt" && x.date.isAfter(date)) return true;
            return false;
        }).findFirst().orElse(null);

        System.out.println("Output (not found):");
        if(item == null) System.out.println("Null");
        else System.out.println("not Null");

        Arrays.stream(int.class.getDeclaredMethods()).forEach((x) -> System.out.println(x));
    }

    static void task3() throws Exception {
        System.out.println("\nCount words and sort");
        Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource("wordCount.txt").toURI()));

        System.out.println("Intput:\nwordCount.txt");

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
    }

    static void task4() throws Exception {
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8);
        //ArrayList<Integer> list = stream.collect(Collectors.toList());

        Method a = Class.class.getDeclaredMethod("getPrimitiveClass", String.class);
        a.setAccessible(true);
        Class<?> b = (Class<?>)(a.invoke(null, "int"));

        System.out.println(b.getDeclaredFields().length);
        Arrays.stream(b.getDeclaredFields()).forEach((x) -> System.out.println(x));


    }

    static void asd() throws Exception {
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set("hello", "bue".toCharArray());
        System.out.println("hello");
    }
}






































