import Utils.*;
import kotlin.Pair;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLabs2 {

    public static void main(String args[]) throws Exception {
        System.out.println("Stream from ArrayList");
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,3,4,5,6,7,2,1,4));
        System.out.println(list);
        Stream oddStream = list.stream().filter((x) -> x % 2 == 1);
        System.out.println(Arrays.toString(oddStream.toArray(Integer[]::new)));

        //==============================================================================================================

        System.out.println("\nSort Persons List by createdAt");
        ArrayList<Person> personsList = new ArrayList<>(Arrays.asList(
            new Person("Ivan","Ivanow", LocalDateTime.of(2019,1,15,5,17,53)),
            new Person("Alex","Alex", LocalDateTime.of(2019,4,8,10,55,26)),
            new Person("Peter","Peter", LocalDateTime.of(2019,3,29,12,3,8)),
            new Person("Nikita","Nikita", LocalDateTime.of(2019,2,28,9,15,46))
        ));
        System.out.println(personsList);
        LocalDateTime date = LocalDateTime.of(2019,3,9,15,26,46);
        Stream dateStream = personsList.stream().filter((x) -> x.createdAt.isBefore(date));
        System.out.println(Arrays.toString(dateStream.toArray(Person[]::new)));

        //==============================================================================================================

        System.out.println("\nSort Persons List by createdAt and logged");
        ArrayList<LoggedPerson> loggedPersonsList = new ArrayList<>(Arrays.asList(
                new LoggedPerson("Ivan","Ivanow", LocalDateTime.of(2019,1,15,5,17,53)),
                new LoggedPerson("Alex","Alex", LocalDateTime.of(2019,4,8,10,55,26)),
                new LoggedPerson("Peter","Peter", LocalDateTime.of(2019,3,29,12,3,8)),
                new LoggedPerson("Nikita","Nikita", LocalDateTime.of(2019,2,28,9,15,46))
        ));
        loggedPersonsList.get(0).logged = true;
        System.out.println(loggedPersonsList);
        Stream dateLoggedStream = loggedPersonsList.stream().filter((x) -> x.createdAt.isBefore(date) && x.logged);
        System.out.println(Arrays.toString(dateLoggedStream.toArray(LoggedPerson[]::new)));

        //==============================================================================================================

        System.out.println("\nStream with second half of the array");
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        System.out.println(numList);
        Stream<Integer> secondHalfStream = numList.stream().skip(numList.size()/2 + numList.size()%2);
        System.out.println(Arrays.toString(secondHalfStream.toArray(Integer[]::new)));

        //==============================================================================================================

        System.out.println("\nReturn selected element of Array");
        System.out.println(numList);
        int value = numList.stream().skip(5).findFirst().orElse(42);
        System.out.println("6) " + value);
        value = numList.stream().skip(12).findFirst().orElse(42);
        System.out.println("12) " + value);

        //==============================================================================================================

        System.out.println("\nReturn array with unique elements");
        ArrayList<Integer> duplicatesList = new ArrayList<>(Arrays.asList(1,1,3,4,3,6,7,8));
        System.out.println(duplicatesList);
        Stream<Integer> duplicatesStream = duplicatesList.stream().distinct();
        System.out.println(Arrays.toString(duplicatesStream.toArray(Integer[]::new)));

        //==============================================================================================================

        System.out.println("\nAdd _outdated to each element of array");
        ArrayList<String> addList = new ArrayList<>(Arrays.asList("log1", "log2", "log3"));
        System.out.println(addList);
        Stream<String> addStream = addList.stream().map((x) -> x + "_outdated");
        System.out.println(Arrays.toString(addStream.toArray(String[]::new)));

        //==============================================================================================================

        System.out.println("\nAdd _outdated to name");
        ArrayList<FileTest> addfilesList = new ArrayList<>(Arrays.asList(
                new FileTest("1", LocalDate.of(2001, 11, 3)),
                new FileTest("1.txt", LocalDate.of(2001,11,3)),
                new FileTest("1_outdated.txt", LocalDate.of(2001,11,3)),
                new FileTest("1", LocalDate.of(2021,11,3))
        ));
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
        //System.out.println(Arrays.toString(addfileStream.toArray(FileTest[]::new)));
        System.out.println("[");
        addfileStream.forEach((x) -> System.out.println(x.toString() + ","));
        System.out.println("]");

        //==============================================================================================================

        System.out.println("\nStream with first half of the array");
        System.out.println(numList);
        Stream<Integer> firstHalfStream = numList.stream().limit(numList.size()/2 + numList.size()%2);
        System.out.println(Arrays.toString(firstHalfStream.toArray(Integer[]::new)));

        //==============================================================================================================

        System.out.println("\nStream with third in the center of array");
        ArrayList<Integer> numList2 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        System.out.println(numList2);
        Stream<Integer> thirdStream = numList2.stream().skip(numList2.size()/3).limit(numList2.size()/3);
        System.out.println(Arrays.toString(thirdStream.toArray(Integer[]::new)));

        //==============================================================================================================

        System.out.println("\nStream with 2 elements before the center of array");
        System.out.println(numList);
        Stream<Integer> centerStream = numList.stream().skip(numList.size()/2-2).limit(2);
        System.out.println(Arrays.toString(centerStream.toArray(Integer[]::new)));

        //==============================================================================================================

        System.out.println("\nRevert sort array");
        System.out.println(numList);
        Stream<Integer> sortedStream = numList.stream().sorted((x,y) -> y - x);
        System.out.println(Arrays.toString(sortedStream.toArray(Integer[]::new)));

        //==============================================================================================================

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
        System.out.println(dataList);
        Stream dataStream = dataList.stream().sorted((x,y) -> {
            int result = y.date.compareTo(x.date);
            if(result == 0) result = Long.compare(x.id, y.id);
            if(result == 0) result = x.name.compareTo(y.name);
            return result;
        });
        System.out.println(Arrays.toString(dataStream.toArray(UserData[]::new)));

        //==============================================================================================================

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
        System.out.println(stringList);
        Stream stringStream = stringList.stream().distinct().sorted();
        System.out.println(Arrays.toString(stringStream.toArray(String[]::new)));

        //==============================================================================================================

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
        System.out.println(strnumList);
        Stream strnumStream = strnumList.stream().map((x) -> {
            String str[] = x.split("_");
            return new MyPair(Integer.parseInt(str[1]), str[0]);
        }).distinct().sorted((x,y) -> y.value - x.value).map((x) -> x.toString());
        System.out.println(Arrays.toString(strnumStream.toArray(String[]::new)));

        //==============================================================================================================

        System.out.println("\nStream with sorted unique strings");
        ArrayList<String> strList1 = new ArrayList<>(Arrays.asList("Peach","Apple"));
        ArrayList<String> strList2 = new ArrayList<>(Arrays.asList("Grass","Tree","Wolf","Pineapple"));
        ArrayList<String> strList3 = new ArrayList<>(Arrays.asList("Elephant","Animal","Banana"));
        ArrayList<ArrayList<String>> strList = new ArrayList<>(Arrays.asList(strList1, strList2, strList3));
        System.out.println(strList);
        ArrayList<String> fullList = strList.stream().reduce((x,y) -> {
            ArrayList<String> fullstrList = new ArrayList<>();
            fullstrList.addAll(x);
            fullstrList.addAll(y);
            return fullstrList;
        }).orElse(new ArrayList<>());
        System.out.println(fullList);
        
    }

}
