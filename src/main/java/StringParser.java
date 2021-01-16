public class StringParser {
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
}
