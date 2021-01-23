package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringParser {
    private static final DateTimeFormatter SCAN_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static LocalDateTime parseTime(String str) {
        return LocalDateTime.from(SCAN_FORMAT.parse(str));
    }

    public static String newLiner(String str, int length) {
        StringBuilder resultStr = new StringBuilder();
        while (str.length() > length) {
            resultStr.append(str, 0, length - 1).append("\n");
            str = str.substring(length - 1);
        }
        return resultStr.toString() + str + "\n";
    }

    public static String underlineGenerator(int i) {
        return "_".repeat(i) + "\n";
    }

    public static boolean isBlank(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
