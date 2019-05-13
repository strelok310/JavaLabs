package Labs;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamLabs1 {

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

    public static void task1() throws Exception {
        System.out.println("Stream from ArrayList");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a1", "a2", "a3"));
        Stream<String> listStream = list.stream();
        System.out.println(Arrays.toString(listStream.toArray(String[]::new)));
    }

    public static void task2() throws Exception {
        System.out.println("\nStream from enumeration");
        Stream<String> enumStream = Stream.of("a1", "a2", "a3");
        System.out.println(Arrays.toString(enumStream.toArray(String[]::new)));
    }

    public static void task3() throws Exception {
        System.out.println("\nStream from array");
        String arr[] = new String[] {"a1", "a2", "a3"};
        Stream<String> arrayStream = Arrays.stream(arr);
        System.out.println(Arrays.toString(arrayStream.toArray(String[]::new)));
    }

    public static void task4() throws Exception {
        System.out.println("\nStream from file");
        //Stream<String> fileStream = Files.lines(Paths.get("stream_example.txt"));
        Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource("stream_example.txt").toURI()));
        System.out.println(Arrays.toString(fileStream.toArray(String[]::new)));
    }

    public static void task5() throws Exception {
        System.out.println("\nStream from string");
        String str = "A string";
        IntStream intStream = str.chars();
        Stream<String> stringStream = intStream.mapToObj((x) -> Character.toString((char)x));
        System.out.println(Arrays.toString(stringStream.toArray(String[]::new)));
    }

    public static void task6() throws Exception {
        System.out.println("\nParallel stream");
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("a1", "a2", "a3"));
        Stream<String> parallelStream = list2.parallelStream();
        System.out.println(Arrays.toString(parallelStream.toArray(String[]::new)));
    }

    public static void task7() throws Exception {
        System.out.println("\nInfinite stream");
        Stream<Integer> infStream = Stream.iterate(2, (x) -> x * x);
        System.out.println(Arrays.toString(infStream.limit(5).toArray(Integer[]::new)));
    }

    public static void task8() throws Exception {
        System.out.println("\nFibonacci stream");
        Stream fibStream = Stream.iterate(new int[]{0,1}, (x) -> new int[]{ x[1] , x[0] + x[1] }).map((x) -> x[0]);
        /*int fib[] = new int[] {-1,0,1};
        Stream<Integer> fibStream = Stream.generate(() -> {
            fib[0]++;
            if(fib[0] < 2) return fib[0];
            else
            {
                int val = fib[1] + fib[2];
                fib[1] = fib[2];
                fib[2] = val;
                return val;
            }
        });*/
        System.out.println(Arrays.toString(fibStream.limit(11).toArray(Integer[]::new)));
    }

}
