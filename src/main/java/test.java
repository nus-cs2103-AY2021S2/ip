import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        String test = "meeting | low | at: Feb 12 2021";
        String[] arr = test.split("[|]");
        String second = arr[2].trim();
        String[] prepositionAndDate = second.split("[\\s]");

        System.out.println(Arrays.toString(prepositionAndDate));

    }
}
