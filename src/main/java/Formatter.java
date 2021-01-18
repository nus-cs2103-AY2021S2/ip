public abstract class Formatter {
    public static String formatOut(String output) {
        String opening = "\t\"----------------------------------------\n";
        String closing = "\n\t----------------------------------------\"\n";
        return opening + "\t" + output + closing;
    }
}
