package Labs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NewDateAPILabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        task1();
        task2();
        task3();
    }

    static void task1() {
        System.out.println(LINE);
        System.out.println("1) Return current date and time");

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("\n" + dateTime);
    }

    static void task2() {
        System.out.println(LINE);
        System.out.println("2) Return current time");

        LocalTime time = LocalTime.now();
        System.out.println("\n" + time);
    }

    static void task3() {
        System.out.println(LINE);
        System.out.println("3) Return current date");

        LocalDate date = LocalDate.now();
        System.out.println("\n" + date);
    }
}
