public abstract class Formatter {
    public static String formatOut(String output) {
        String opening = "\"----------------------------------------\n";
        String closing = "----------------------------------------\"\n";
        String combined = opening + output + "\n" + closing;

        String[] strArray = combined.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s: strArray) {
            stringBuilder.append("\t").append(s).append("\n");
        }

        return stringBuilder.toString();
    }

    public static String formatList(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(String.valueOf(i + 1)).append(". ").append(strArray[i]);
        }
        return stringBuilder.toString();
    }
}
