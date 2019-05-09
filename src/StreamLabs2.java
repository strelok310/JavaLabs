import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamLabs2 {

    public static void main(String args[]) throws Exception {
        System.out.println("Stream from ArrayList");
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,3,4,5,6,7,2,1,4));
        Stream oddStream = list.stream().filter((x) -> x % 2 == 1);
        System.out.println(Arrays.toString(oddStream.toArray(Integer[]::new)));


    }

}
