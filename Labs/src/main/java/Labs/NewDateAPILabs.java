package Labs;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class NewDateAPILabs {
    private static final String LINE = "\n==============================================================================================================\n";

    public static void main(String[] args) throws Exception {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
    }

    /**
     * Вернуть текущую дату с временем
     */

    static void task1() {
        System.out.println(LINE);
        System.out.println("1) Return current date and time\n");

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }

    /**
     * Вернуть текущее время
     */

    static void task2() {
        System.out.println(LINE);
        System.out.println("2) Return current time\n");

        LocalTime time = LocalTime.now();
        System.out.println(time);
    }

    /**
     * Вернуть текущую дату
     */

    static void task3() {
        System.out.println(LINE);
        System.out.println("3) Return current date\n");

        LocalDate date = LocalDate.now();
        System.out.println(date);
    }

    /**
     * Вернуть текущую дату с временем, изменить месяц на февраль, год на 2012.
     */

    static void task4() {
        System.out.println(LINE);
        System.out.println("4) Get current date and time and change month to February and year to 2012\n");

        LocalDateTime dateTime = LocalDateTime.now().withMonth(2).withYear(2012);
        System.out.println(dateTime);
    }

    /**
     * Создать дату 23 Марта 1974 года. Посчитать сколько дней назад это было
     */

    static void task5() {
        System.out.println(LINE);
        System.out.println("5) Get days since 23 March 1974\n");

        LocalDate date = LocalDate.of(1974, 3, 23);
        System.out.println(date.until(LocalDate.now(), ChronoUnit.DAYS));
    }

    /**
     * Создать время из строки
     */

    static void task6() {
        System.out.println(LINE);
        System.out.println("6) Create time from string\n");

        LocalTime time = LocalTime.parse("20:15:30");
        System.out.println(time);
    }

    /**
     * Создать ZonedDateTime из строки в формате ISO 8601
     */

    static void task7() {
        System.out.println(LINE);
        System.out.println("7) Create time from string in ISO 8601\n");

        //ZonedDateTime zonedDateTime = ZonedDateTime.parse("2007-12-03T10:15:30+03:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2007-12-03T10:15:30+03:00");
        System.out.println(zonedDateTime);
    }
}
