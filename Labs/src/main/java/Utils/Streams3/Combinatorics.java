package Utils.Streams3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Combinatorics {
    private Combinatorics() {}

    public static Stream<ArrayList<String>> combine(Stream<ArrayList<String>> stream, int size, String[] str) {
        stream = stream.flatMap( (x) ->
                Arrays.stream(str)
                        .map((y) -> {
                            if(x.contains(y)) return x;

                            ArrayList<String> list = new ArrayList<>();
                            list.addAll(x);
                            list.add(y);

                            return list;
                        })
                        .filter((y) -> y.size() == size + 1)
        );
        if(size < str.length - 1) return combine(stream, size + 1, str);
        return stream;
    }

    public static Stream<ArrayList<String>> combineInt(String[] strArray) {
        Stream<ArrayList<Integer>> stream = IntStream.range(0, strArray.length)
                .mapToObj((x) -> new ArrayList(Arrays.asList(Integer.valueOf(x))));

        return combineProcess(stream, 1, strArray.length)
                .map((x) -> x.stream().map((y) ->
                        strArray[y.intValue()])
                        .collect(Collector.of( ArrayList::new,
                                ArrayList::add,
                                (z, w) -> { z.addAll(w); return z; })
                        )
                );
    }

    private static Stream<ArrayList<Integer>> combineProcess(Stream<ArrayList<Integer>> stream, int count, int size) {
        stream = stream.flatMap((x) ->
                IntStream.range(0, size).mapToObj((y) -> {
                    if(x.contains(y)) return x;

                    ArrayList<Integer> list = new ArrayList<>();
                    list.addAll(x);
                    list.add(y);

                    return list;
                })
                        .filter((y) -> y.size() == count + 1)
        );
        if(count < size - 1) return combineProcess(stream, count + 1, size);
        return stream;
    }
}
