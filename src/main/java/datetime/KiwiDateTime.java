package datetime;

/**
 * This is a wrapper class for a local kiwiDate kiwiTime object, wrapped with functions that parse
 * user input strings to dateTime fields for tasks.
 */
public class KiwiDateTime {

    private final KiwiDate kiwiDate;
    private final KiwiTime kiwiTime;
    private static final String delimiter = ":"; // needs to not clash with storage delimiter

    public static KiwiDateTime of(int day, int month, int year, int hour, int min) {
        return new KiwiDateTime(KiwiDate.of(day, month, year), KiwiTime.of(hour, min));
    }

    @Override
    public String toString() {
        return (kiwiDate + " " + kiwiTime).trim();
    }

    static void print(Object... objects) {
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    private KiwiDateTime(KiwiDate d, KiwiTime t) {
        this.kiwiDate = d;
        this.kiwiTime = t;
    }


    /**
     * Creates a string representation of a KiwiDateTime object to be usable in storage.
     * @return
     */
    public String unparse() {
        return String.format("%s%s%s", this.kiwiDate.unparse(delimiter), delimiter, this.kiwiTime.unparse(delimiter));
    }

    /**
     * Converts a string stored in storage to a KiwiDateTime object. Does the opposite of unparse().
     *
     * @param strToParse a string from hard disk storage that represents a KiwiDateTime object
     * @return
     */
    public static KiwiDateTime parse(String strToParse) {
        String[] str = strToParse.split(KiwiDateTime.delimiter);
        int[] values = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            values[i] = Integer.parseInt(str[i]);
        }

        return KiwiDateTime.of(values[0], values[1], values[2], values[3], values[4]);
    }

}
