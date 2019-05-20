package Labs;

import Utils.Streams2.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamLabs2 {
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
    }

    /**
     * Отфильтровать массив, оставив только нечетные элементы
     */

    static void task1() {
        System.out.println(LINE);
        System.out.println("Return odd elements");
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,3,4,5,6,7,2,1,4));

        System.out.println("Input:");
        System.out.println(list);

        Stream oddStream = list.stream().filter((x) -> x % 2 == 1);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(oddStream.toArray(Integer[]::new)));
    }

    /**
     * Отфильтровать массив объектов (по полю createdAt, класс создайте сами, в качестве createdAt используйте
     * LocalDateTime), оставив только созданные ранее заданной даты
     */

    static void task2() {
        System.out.println(LINE);
        System.out.println("\nFilter Persons List by createdAt");
        ArrayList<Person> personsList = new ArrayList<>(Arrays.asList(
                new Person("Ivan","Ivanow", LocalDateTime.of(2019,1,15,5,17,53)),
                new Person("Alex","Alex", LocalDateTime.of(2019,4,8,10,55,26)),
                new Person("Peter","Peter", LocalDateTime.of(2019,3,29,12,3,8)),
                new Person("Nikita","Nikita", LocalDateTime.of(2019,2,28,9,15,46))
        ));
        LocalDateTime date = LocalDateTime.of(2019,3,9,15,26,46);

        System.out.println("Input:");
        System.out.println(personsList);
        System.out.println("Date: " + date);

        Stream dateStream = personsList.stream().filter((x) -> x.createdAt.isBefore(date));

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(dateStream.toArray(Person[]::new)));
    }

    /**
     * К классу из предыдущего примера добавьте поле logged типа Boolean. Отфильтровать массив объектов, оставив
     * только созданные ранее заданной даты и с установленным полем logged в true.
     */

    static void task3() {
        System.out.println(LINE);
        System.out.println("\nFilter Persons List by createdAt and logged");
        ArrayList<LoggedPerson> loggedPersonsList = new ArrayList<>(Arrays.asList(
                new LoggedPerson("Ivan","Ivanow", LocalDateTime.of(2019,1,15,5,17,53)),
                new LoggedPerson("Alex","Alex", LocalDateTime.of(2019,4,8,10,55,26)),
                new LoggedPerson("Peter","Peter", LocalDateTime.of(2019,3,29,12,3,8)),
                new LoggedPerson("Nikita","Nikita", LocalDateTime.of(2019,2,28,9,15,46))
        ));
        loggedPersonsList.get(0).setLogged(true);
        LocalDateTime date = LocalDateTime.of(2019,3,9,15,26,46);

        System.out.println("Input:");
        System.out.println(loggedPersonsList);
        System.out.println("Date: " + date);

        Stream dateLoggedStream = loggedPersonsList.stream().filter((x) -> x.createdAt.isBefore(date) && x.isLogged());

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(dateLoggedStream.toArray(LoggedPerson[]::new)));
    }

    /**
     * Верните стрим, состоящий только из второй половины элементов входного массива
     */

    static void task4() {
        System.out.println(LINE);
        System.out.println("\nStream with second half of the array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> secondHalfStream = numList.stream().skip(numList.size()/2 + numList.size()%2);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(secondHalfStream.toArray(Integer[]::new)));
    }

    /**
     * Верните стрим, из одного элемента на заданной позиции. В случае отсутствии такого  элемента верните 42.
     * Запрещено использовать условный оператор.
     */

    static void task5() {
        System.out.println(LINE);
        System.out.println("\nReturn selected element of Array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        System.out.println("\nOutput:");
        int value = numList.stream().skip(5).findFirst().orElse(42);
        System.out.println("6) " + value);
        value = numList.stream().skip(12).findFirst().orElse(42);
        System.out.println("12) " + value);
    }

    /**
     * Верните стрим из массива, исключив дубликаты
     */

    static void task6() {
        System.out.println(LINE);
        System.out.println("\nReturn array with unique elements");
        ArrayList<Integer> duplicatesList = new ArrayList<>(Arrays.asList(1,1,3,4,3,6,7,8));

        System.out.println("Input:");
        System.out.println(duplicatesList);

        Stream<Integer> duplicatesStream = duplicatesList.stream().distinct();

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(duplicatesStream.toArray(Integer[]::new)));
    }

    /**
     * Добавьте к каждой строке массива “_outdated”
     */

    static void task7() {
        System.out.println(LINE);
        System.out.println("\nAdd _outdated to each element of array");
        ArrayList<String> addList = new ArrayList<>(Arrays.asList("log1", "log2", "log3"));

        System.out.println("Input:");
        System.out.println(addList);

        Stream<String> addStream = addList.stream().map((x) -> x + "_outdated");

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(addStream.toArray(String[]::new)));
    }

    /**
     * Создайте класс с полями name типа String, date типа  LocalDate. Добавьте “_outdated” в случае если оно
     * отсутсвует, перед расширением, к name для объектов, у которых date менее текущей даты
     */

    /**
     * Напечатать на консоль каждый элемент массива, используя Stream
     */

    static void task8() {
        System.out.println(LINE);
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
            if(!x.getName().matches(".*_outdated(\\.[a-zA-Z0-9]+)?$")) {
                Matcher matcher = Pattern.compile("\\.[a-zA-Z0-9]+$").matcher(x.getName());
                if(matcher.find()) {
                    x.setName( x.getName().substring(0,matcher.start()) + "_outdated" +
                            x.getName().substring(matcher.start(), matcher.end()) );
                }
                else x.setName(x.getName() + "_outdated");
            }
        });

        System.out.println("\nOutput:");
        //System.out.println(Arrays.toString(addfileStream.toArray(FileTest[]::new)));
        System.out.println("[");
        addfileStream.forEach((x) -> System.out.println(x.toString() + ","));
        System.out.println("]");
    }

    /**
     * Верните стрим, состоящий только из первой половины элементов входного массива
     */

    static void task9() {
        System.out.println(LINE);
        System.out.println("\nStream with first half of the array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> firstHalfStream = numList.stream().limit(numList.size()/2 + numList.size()%2);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(firstHalfStream.toArray(Integer[]::new)));
    }

    /**
     * Верните стрим, состоящий только из трети элементов по середине
     */

    static void task10() {
        System.out.println(LINE);
        System.out.println("\nStream with third in the center of array");
        ArrayList<Integer> numList2 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

        System.out.println("Input:");
        System.out.println(numList2);

        Stream<Integer> thirdStream = numList2.stream().skip(numList2.size()/3).limit(numList2.size()/3);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(thirdStream.toArray(Integer[]::new)));
    }

    /**
     * Верните стрим, состоящий из двух элементов перед серединой
     */

    static void task11() {
        System.out.println(LINE);
        System.out.println("\nStream with 2 elements before the center of array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> centerStream = numList.stream().skip(numList.size()/2-2).limit(2);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(centerStream.toArray(Integer[]::new)));
    }

    /**
     * Верните отсортированный стрим в порядке убывания
     */

    static void task12() {
        System.out.println(LINE);
        System.out.println("\nRevert sort array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(numList);

        Stream<Integer> sortedStream = numList.stream().sorted((x,y) -> y - x);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(sortedStream.toArray(Integer[]::new)));
    }

    /**
     * Создайте класс с полями id типа Long, name типа String, date типа LocalDate. Верните отсортированный стрим.
     * Правило сортировки:
     * Сначала по полю LocalDate по убыванию, затем по полю id по возрастанию, затем по полю name в алфавитном порядке.
     * Использование циклов и условных операторов допускается только в компараторе.
     */

    static void task13() {
        System.out.println(LINE);
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
            int result = y.getDate().compareTo(x.getDate());
            if(result == 0) result = Long.compare(x.getId(), y.getId());
            if(result == 0) result = x.getName().compareTo(y.getName());
            return result;
        });

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(dataStream.toArray(UserData[]::new)));
    }

    /**
     * Отсортировать коллекцию строк по алфавиту и убрать дубликаты
     */

    static void task14() {
        System.out.println(LINE);
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

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(stringStream.toArray(String[]::new)));
    }

    /**
     * Дан массив строк по шаблону <префикс>_<число>. Отсортирвоат ьпо числу по убыванию, исключить
     * повторения (чисел). Вернуть стрим в формате <префикс>_<число>.
     */

    static void task15() {
        System.out.println(LINE);
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

        Stream strnumStream = strnumList.stream()
                                        .map((x) -> {
                                            String str[] = x.split("_");
                                            return new MyPair(Integer.parseInt(str[1]), str[0]);
                                        })
                                        .distinct()
                                        .sorted((x,y) -> y.getValue() - x.getValue())
                                        .map((x) -> x.toString());

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(strnumStream.toArray(String[]::new)));
    }

    /**
     * Создайте натуральный стрим для целых чисел,
     * для чисел с плавающей точкой двойной точности
     */

    static void task16() {
        System.out.println(LINE);
        System.out.println("\nCreate IntStream");
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));

        System.out.println("Input:");
        System.out.println(intList);

        IntStream intStream = intList.stream().mapToInt(x -> x);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(intStream.toArray()));

        //==============================================================================================================

        System.out.println("\nCreate DoubleStream");
        ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(1.3, 2.8, 3.1, 4.2, 5.6, 6.9, 7.0, 8.5));

        System.out.println("Input:");
        System.out.println(doubleList);

        DoubleStream doubleStream = doubleList.stream().mapToDouble(x -> x);

        System.out.println("\nOutput:");
        System.out.println(Arrays.toString(doubleStream.toArray()));
    }

    /**
     * Объедините List Listов в один Stream.
     */

    static void task17() {
        System.out.println(LINE);
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

        System.out.println("\nOutput:");
        System.out.println(fullList);

        //Variant 2
        ArrayList<String> list = new ArrayList<>(stringList.stream().flatMap((x) -> x.stream()).collect(Collectors.toList()));

        System.out.println("\nOutput:");
        System.out.println(list);
    }

    /**
     * Дан массив строк по шаблону: <text>:<text>:<text>:…:<text>. Вернуть массив элементов <text>
     */

    static void task18() {
        System.out.println(LINE);
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

        System.out.println("\nOutput:");
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

        System.out.println("\nOutput:");
        System.out.println(fullList);
    }
}
