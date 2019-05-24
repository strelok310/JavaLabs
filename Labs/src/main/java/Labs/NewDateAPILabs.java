package Labs;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

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
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
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

    /**
     * Получить строку в формате ISO 8601 для текущей даты и времени для региона UTC
     */

    static void task8() {
        System.out.println(LINE);
        System.out.println("8) Get string in ISO 8601 with date and time for UTC\n");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(zonedDateTime);
        //System.out.println(zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    /**
     * Получить строку в формате ISO 8601 для текущей даты и времени для региона Europe/Minsk
     */

    static void task9() {
        System.out.println(LINE);
        System.out.println("9) Get string in ISO 8601 with date and time for Europe/Minsk\n");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Minsk"));
        System.out.println(zonedDateTime);
        //System.out.println(zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    /**
     * Добавить к текущей дате для региона UTC одну декаду
     */

    static void task10() {
        System.out.println(LINE);
        System.out.println("10) Add decade to current UTC date\n");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC")).plusDays(10);
        System.out.println(zonedDateTime);
    }

    /**
     * Получить Duration между текущей датой для регионов UTC и Europe/Minsk
     */

    static void task11() {
        System.out.println(LINE);
        System.out.println("11) Get Duration between UTC and Europe/Minsk\n");

        ZonedDateTime dateUTC = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime dateMinsk = ZonedDateTime.now(ZoneId.of("Europe/Minsk"));

        System.out.println("UTC: " + dateUTC);
        System.out.println("Europe/Minsk: " + dateMinsk);
        System.out.println(Duration.between(dateUTC, dateMinsk));
    }

    /**
     * Получить Duration между текущей датой для регионов UTC и GMT
     */

    static void task12() {
        System.out.println(LINE);
        System.out.println("12) Get Duration between UTC and GMT\n");

        ZonedDateTime dateUTC = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime dateGMT = ZonedDateTime.now(ZoneId.of("GMT"));

        System.out.println("UTC: " + dateUTC);
        System.out.println("GMT: " + dateGMT);
        System.out.println(Duration.between(dateUTC, dateGMT));
    }

    /**
     * Получить Period между текущей датой и вашим днем рождения (с годом)
     */

    static void task13() {
        System.out.println(LINE);
        System.out.println("13) Get Period between current date and birthday\n");

        LocalDate birtday = LocalDate.of(1995,10,3);

        System.out.println(birtday.until(LocalDate.now()));
        System.out.println(Period.between(birtday, LocalDate.now()));
    }

    /**
     * Получить дату следующего четверга
     */

    static void task14() {
        System.out.println(LINE);
        System.out.println("14) Get date of next thursday\n");

        LocalDate date = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        System.out.println(date);
    }

    /**
     * Получить дату второй субботы текущего месяца текущего года
     */

    static void task15() {
        System.out.println(LINE);
        System.out.println("15) Get date of second saturday of current month and year\n");

        LocalDate date = LocalDate.now()
                                  .with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY))
                                  .plusWeeks(1);
        System.out.println(date);
    }

    /**
     * Преобразовать ZonedDateTime дял ващего региона в LocalDateTime для региона UTC.
     */

    static void task16() {
        System.out.println(LINE);
        System.out.println("16) Convert time in local zone in ZonedDateTime to LocalDateTime UTC\n");

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Minsk"));
        LocalDateTime localDateTime = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();

        System.out.println("ZonedDateTime: " + zonedDateTime);
        System.out.println("LocalDateTime: " + localDateTime);
    }

}
