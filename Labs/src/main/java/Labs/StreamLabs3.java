package Labs;

import Utils.Streams2.UserData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class StreamLabs3 {
    public static void main(String args[]) {
        Task1();
        Task2();
    }

    static void Task1() {
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

    static void Task2() {
        System.out.println("\nReturn first element of array");
        ArrayList<UserData> list = new ArrayList<>(Arrays.asList(
                new UserData(15,"Image.jpg", LocalDate.of(2019,5,1)),
                new UserData(9,"Star_Wars.mkv", LocalDate.of(2019,7,13)),
                new UserData(100,"Info.txt", LocalDate.of(2020,5,17)),
                new UserData(85,"Labs.docx", LocalDate.of(2022,5,5)),
                new UserData(100,"secret.rar", LocalDate.of(2019,5,1))
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
}
