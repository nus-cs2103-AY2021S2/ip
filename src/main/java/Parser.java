import java.util.Scanner;

public class Parser {

    public static String[] parse(String string) {
        String[] inputArray = string.split(" ", 2);
        return inputArray;
    }
}
