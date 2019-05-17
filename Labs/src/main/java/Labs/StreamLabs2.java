package Labs;

import Utils.Streams2.*;
import Utils.Tmp;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamLabs2 {

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
    }

    static void task1() {
        System.out.println("Return odd elements");
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,3,4,5,6,7,2,1,4));

        System.out.println("Input:");
        System.out.println(list);

        Stream oddStream = list.stream().filter((x) -> x % 2 == 1);

        System.out.println("Output:");
        System.out.println(Arrays.toString(oddStream.toArray(Integer[]::new)));
    }

    static void task2() {
        System.out.println("\nSort Persons List by createdAt");
        ArrayList<Person> personsList = new ArrayList<>(Arrays.asList(
                new Person("Ivan","Ivanow", LocalDateTime.of(2019,1,15,5,17,53)),
                new Person("Alex","Alex", LocalDateTime.of(2019,4,8,10,55,26)),
                new Person("Peter","Peter", LocalDateTime.of(2019,3,29,12,3,8)),
                new Person("Nikita","Nikita", LocalDateTime.of(2019,2,28,9,15,46))
        ));

        System.out.println("Input:");
        System.out.println(personsList);

        LocalDateTime date = LocalDateTime.of(2019,3,9,15,26,46);
        Stream dateStream = personsList.stream().filter((x) -> x.createdAt.isBefore(date));

        System.out.println("Output:");
        System.out.println(Arrays.toString(dateStream.toArray(Person[]::new)));
    }

    static void task3() {
        System.out.println("\nSort Persons List by createdAt and logged");
        ArrayList<LoggedPerson> loggedPersonsList = new ArrayList<>(Arrays.asList(
                new LoggedPerson("Ivan","Ivanow", LocalDateTime.of(2019,1,15,5,17,53)),
                new LoggedPerson("Alex","Alex", LocalDateTime.of(2019,4,8,10,55,26)),
                new LoggedPerson("Peter","Peter", LocalDateTime.of(2019,3,29,12,3,8)),
                new LoggedPerson("Nikita","Nikita", LocalDateTime.of(2019,2,28,9,15,46))
        ));
        loggedPersonsList.get(0).logged = true;

        System.out.println("Input:");
        System.out.println(loggedPersonsList);

        LocalDateTime date = LocalDateTime.of(2019,3,9,15,26,46);
        Stream dateLoggedStream = loggedPersonsList.stream().filter((x) -> x.createdAt.isBefore(date) && x.logged);

        System.out.println("Output:");
        System.out.println(Arrays.toString(dateLoggedStream.toArray(LoggedPerson[]::new)));
    }

    static void task4() {
        System.out.println("\nStream with second half of the array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> secondHalfStream = numList.stream().skip(numList.size()/2 + numList.size()%2);

        System.out.println("Output:");
        System.out.println(Arrays.toString(secondHalfStream.toArray(Integer[]::new)));
    }

    static void task5() {
        System.out.println("\nReturn selected element of Array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        System.out.println("Output:");
        int value = numList.stream().skip(5).findFirst().orElse(42);
        System.out.println("6) " + value);
        value = numList.stream().skip(12).findFirst().orElse(42);
        System.out.println("12) " + value);
    }

    static void task6() {
        System.out.println("\nReturn array with unique elements");
        ArrayList<Integer> duplicatesList = new ArrayList<>(Arrays.asList(1,1,3,4,3,6,7,8));

        System.out.println("Input:");
        System.out.println(duplicatesList);

        Stream<Integer> duplicatesStream = duplicatesList.stream().distinct();

        System.out.println("Output:");
        System.out.println(Arrays.toString(duplicatesStream.toArray(Integer[]::new)));
    }

    static void task7() {
        System.out.println("\nAdd _outdated to each element of array");
        ArrayList<String> addList = new ArrayList<>(Arrays.asList("log1", "log2", "log3"));

        System.out.println("Input:");
        System.out.println(addList);

        Stream<String> addStream = addList.stream().map((x) -> x + "_outdated");

        System.out.println("Output:");
        System.out.println(Arrays.toString(addStream.toArray(String[]::new)));
    }

    static void task8() {
        System.out.println("\nAdd _outdated to name and print result using forEach");
        ArrayList<FileTest> addfilesList = new ArrayList<>(Arrays.asList(
                new FileTest("1", LocalDate.of(2001, 11, 3)),
                new FileTest("1.txt", LocalDate.of(2001,11,3)),
                new FileTest("1_outdated.txt", LocalDate.of(2001,11,3)),
                new FileTest("1", LocalDate.of(2021,11,3))
        ));

        System.out.println("Input:");
        System.out.println(addfilesList);

        Stream<FileTest> addfileStream = addfilesList.stream().peek( (x) -> {
            if(!x.name.matches(".*_outdated(\\.[a-zA-Z0-9]+)?$")) {
                Matcher matcher = Pattern.compile("\\.[a-zA-Z0-9]+$").matcher(x.name);
                if(matcher.find()) {
                    x.name = x.name.substring(0,matcher.start()) + "_outdated" +
                            x.name.substring(matcher.start(), matcher.end());
                }
                else x.name = x.name + "_outdated";
            }
        });

        System.out.println("Output:");
        //System.out.println(Arrays.toString(addfileStream.toArray(FileTest[]::new)));
        System.out.println("[");
        addfileStream.forEach((x) -> System.out.println(x.toString() + ","));
        System.out.println("]");
    }

    static void task9() {
        System.out.println("\nStream with first half of the array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> firstHalfStream = numList.stream().limit(numList.size()/2 + numList.size()%2);

        System.out.println("Output:");
        System.out.println(Arrays.toString(firstHalfStream.toArray(Integer[]::new)));
    }

    static void task10() {
        System.out.println("\nStream with third in the center of array");
        ArrayList<Integer> numList2 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

        System.out.println("Input:");
        System.out.println(numList2);

        Stream<Integer> thirdStream = numList2.stream().skip(numList2.size()/3).limit(numList2.size()/3);

        System.out.println("Output:");
        System.out.println(Arrays.toString(thirdStream.toArray(Integer[]::new)));
    }

    static void task11() {
        System.out.println("\nStream with 2 elements before the center of array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> centerStream = numList.stream().skip(numList.size()/2-2).limit(2);

        System.out.println("Output:");
        System.out.println(Arrays.toString(centerStream.toArray(Integer[]::new)));
    }

    static void task12() {
        System.out.println("\nRevert sort array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> sortedStream = numList.stream().sorted((x,y) -> y - x);

        System.out.println("Output:");
        System.out.println(Arrays.toString(sortedStream.toArray(Integer[]::new)));
    }

    static void task13() {
        System.out.println("\nRevert sort of user class array");
        ArrayList<UserData> dataList = new ArrayList<>(Arrays.asList(
                new UserData(15,"asd", LocalDate.of(2019,5,1)),
                new UserData(9,"asd", LocalDate.of(2019,7,13)),
                new UserData(100,"asd", LocalDate.of(2019,5,1)),
                new UserData(85,"asd", LocalDate.of(2022,5,5)),
                new UserData(100,"bcd", LocalDate.of(2019,5,1)),
                new UserData(26,"asd", LocalDate.of(2016,5,12)),
                new UserData(15,"asd", LocalDate.of(2014,5,10)),
                new UserData(7,"asd", LocalDate.of(2019,3,8)),
                new UserData(26,"asd", LocalDate.of(2018,5,10)),
                new UserData(15,"asd", LocalDate.of(2018,5,10)),
                new UserData(100,"qwe", LocalDate.of(2019,5,1))
        ));

        System.out.println("Input:");
        System.out.println(dataList);

        Stream dataStream = dataList.stream().sorted((x,y) -> {
            int result = y.date.compareTo(x.date);
            if(result == 0) result = Long.compare(x.id, y.id);
            if(result == 0) result = x.name.compareTo(y.name);
            return result;
        });

        System.out.println("Output:");
        System.out.println(Arrays.toString(dataStream.toArray(UserData[]::new)));
    }

    static void task14() {
        System.out.println("\nStream with sorted unique strings");
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(
                "Peach",
                "Apple",
                "Grass",
                "Tree",
                "Apple",
                "Wolf",
                "Tree",
                "Pineapple",
                "Elephant",
                "Animal",
                "Banana"
        ));

        System.out.println("Input:");
        System.out.println(stringList);

        Stream stringStream = stringList.stream().distinct().sorted();

        System.out.println("Output:");
        System.out.println(Arrays.toString(stringStream.toArray(String[]::new)));
    }

    static void task15() {
        System.out.println("\nStream with sorted unique strings");
        ArrayList<String> strnumList = new ArrayList<>(Arrays.asList(
                "Peach_5",
                "Apple_6",
                "Grass_23",
                "Tree_16",
                "Apple_67",
                "Wolf_16",
                "Tree_1",
                "Pineapple_23",
                "Elephant_5",
                "Animal_23",
                "Banana_16"
        ));

        System.out.println("Input:");
        System.out.println(strnumList);

        Stream strnumStream = strnumList.stream().map((x) -> {
            String str[] = x.split("_");
            return new MyPair(Integer.parseInt(str[1]), str[0]);
        }).distinct().sorted((x,y) -> y.value - x.value).map((x) -> x.toString());

        System.out.println("Output:");
        System.out.println(Arrays.toString(strnumStream.toArray(String[]::new)));
    }

    static void task16() {
        System.out.println("\nCreate IntStream");
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(intList);

        IntStream intStream = intList.stream().mapToInt(x -> x);

        System.out.println("Output:");
        System.out.println(Arrays.toString(intStream.toArray()));

        //==============================================================================================================

        System.out.println("\nCreate DoubleStream");
        ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(1.3, 2.8, 3.1, 4.2, 5.6, 6.9, 7.0, 8.5));

        System.out.println("Input:");
        System.out.println(doubleList);

        DoubleStream doubleStream = doubleList.stream().mapToDouble(x -> x);

        System.out.println("Output:");
        System.out.println(Arrays.toString(doubleStream.toArray()));
    }

    static void task17() {
        System.out.println("\nStream with sorted unique strings");
        ArrayList<String> strList1 = new ArrayList<>(Arrays.asList("Peach","Apple"));
        ArrayList<String> strList2 = new ArrayList<>(Arrays.asList("Grass","Tree","Wolf","Pineapple"));
        ArrayList<String> strList3 = new ArrayList<>(Arrays.asList("Elephant","Animal","Banana"));
        ArrayList<ArrayList<String>> stringList = new ArrayList<>(Arrays.asList(strList1, strList2, strList3));

        System.out.println("Input:");
        System.out.println(stringList);

        //Variant 1
        ArrayList<String> fullList = stringList.stream()
                                                .reduce(new ArrayList<>(), (x,y) -> {
                                                    x.addAll(y);
                                                    return x;
                                                });

        System.out.println("Output:");
        System.out.println(fullList);

        //Variant 2
        ArrayList<String> list = new ArrayList<>(stringList.stream().flatMap((x) -> x.stream()).collect(Collectors.toList()));

        System.out.println("Output:");
        System.out.println(list);
    }

    static void task18() {
        System.out.println("\nSplit strings by symbol \":\"");
        ArrayList<String> strList = new ArrayList<>(Arrays.asList(
                "Peach:Apple:Grass",
                "Tree:Apple",
                "Wolf",
                "Tree:Pineapple:Elephant:Animal:Banana"
        ));

        System.out.println("Input:");
        System.out.println(strList);

        //==============================================================================================================

        //Variant 1
        Stream<String> stream = strList.stream().flatMap((x) -> Arrays.stream(x.split(":")));

        System.out.println("Output:");
        System.out.println(Arrays.toString(stream.toArray(String[]::new)));

        //==============================================================================================================

        //Variant 2
        ArrayList<String> fullList = strList.stream()
                .map((x) -> new ArrayList<String>(Arrays.asList(x.split(":"))))
                .reduce((x,y) -> {
                    x.add("");
                    x.addAll(y);
                    return x;
                })
                .orElse(new ArrayList<String>());

        System.out.println("Output:");
        System.out.println(fullList);
    }

    static void asd() {
        /*Consumer<String> wtf = (s) -> System.out.println("hi, "+s);

        Consumer<Consumer<String>> qwe = (x) -> {
            x.accept("Darth Vader");
            System.out.println("Have a nice day!");
        };*/

        /*Consumer<Tmp> qwe = (x) -> {
            x.apply();
            System.out.println("Have a nice day!");
        };

        Function<String, Tmp> func = (x) -> () -> System.out.println(x);

        qwe.accept(func.apply("Darth Vader"));*/
    }



}
