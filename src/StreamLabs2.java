import Utils.FileTest;
import Utils.Person;

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
        Stream oddStream = list.stream().filter((x) -> x % 2 == 1);
        System.out.println(Arrays.toString(oddStream.toArray(Integer[]::new)));

        System.out.println("\nSort Persons List by createdAt");
        ArrayList<Person> personsList = new ArrayList<>(Arrays.asList(
            new Person("Ivan","Ivanow", LocalDateTime.of(2019,1,15,5,17,53)),
            new Person("Alex","Alex", LocalDateTime.of(2019,4,8,10,55,26)),
            new Person("Peter","Peter", LocalDateTime.of(2019,3,29,12,3,8)),
            new Person("Nikita","Nikita", LocalDateTime.of(2019,2,28,9,15,46))
        ));
        LocalDateTime date = LocalDateTime.of(2019,3,9,15,26,46);
        Stream dateStream = personsList.stream().filter((x) -> x.createdAt.isBefore(date));
        System.out.println(Arrays.toString(dateStream.toArray(Person[]::new)));

        System.out.println("\nSort Persons List by createdAt and logged");
        personsList.get(0).logged = true;
        Stream dateLoggedStream = personsList.stream().filter((x) -> x.createdAt.isBefore(date) && x.logged);
        System.out.println(Arrays.toString(dateLoggedStream.toArray(Person[]::new)));

        System.out.println("\nStream of even elements id");
        ArrayList<Integer> evenList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        Stream<Integer> evenStream = evenList.stream().skip(evenList.size()/2);
        System.out.println(Arrays.toString(evenStream.toArray(Integer[]::new)));

        System.out.println("\nReturn selected element of Array");
        int value = evenList.stream().skip(5).findFirst().orElse(42);
        System.out.println("6) " + value);
        value = evenList.stream().skip(12).findFirst().orElse(42);
        System.out.println("12) " + value);

        System.out.println("\nReturn array with unique elements");
        ArrayList<Integer> duplicatesList = new ArrayList<>(Arrays.asList(1,1,3,4,3,6,7,8));
        Stream<Integer> duplicatesStream = duplicatesList.stream().distinct();
        System.out.println(Arrays.toString(duplicatesStream.toArray(Integer[]::new)));

        System.out.println("\nAdd _outdated to each element of array");
        ArrayList<String> addList = new ArrayList<>(Arrays.asList("log1", "log2", "log3"));
        Stream<String> addStream = addList.stream().map((x) -> x + "_outdated");
        System.out.println(Arrays.toString(addStream.toArray(String[]::new)));

        System.out.println("\nAdd _outdated to name");
        ArrayList<FileTest> addfilesList = new ArrayList<>(Arrays.asList(
                new FileTest("1", LocalDate.of(2001, 11, 3)),
                new FileTest("1.txt", LocalDate.of(2001,11,3)),
                new FileTest("1_outdated.txt", LocalDate.of(2001,11,3)),
                new FileTest("1", LocalDate.of(2021,11,3))
        ));
        Stream<FileTest> addfileStream = addfilesList.stream();
        System.out.println(Arrays.toString(addfileStream.toArray(FileTest[]::new)));

        String asd = "qwe asd.txt";
        Pattern pattern = Pattern.compile(".{1}\\w+$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(asd);
        while(matcher.find()) {
            System.out.println(asd.substring(matcher.start(), matcher.end()));
        }

    }

}
