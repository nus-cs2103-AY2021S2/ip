package datetime;

import java.time.LocalDateTime;

/**
 * This is a wrapper class for a local kiwiDate kiwiTime object, wrapped with functions that parse
 * user input strings to dateTime fields for tasks.
 */
public class KiwiDateTime {
    private boolean hasDate; // tracked internally by kiwi date and kiwi time
    private boolean hasTime;
    private final KiwiDate kiwiDate;
    private final KiwiTime kiwiTime;
    private static final String delimiter = ":"; // needs to not clash with storage delimiter

    public static KiwiDateTime of(int day, int month, int year, int hour, int min) {
        return new KiwiDateTime(KiwiDate.of(day, month, year), KiwiTime.of(hour, min));
    }

    // todo possible inheritance: subclasses for datetimes
    // or just customize toStrings not to print minute / hour if don't have, or year if this year
    @Override
    public String toString() {
        return (kiwiDate + " " + kiwiTime).trim();
    }

    static void print(Object... objects) {
        for (Object o : objects) {
            System.out.println(o);
        }
    }


    public static int getCurrYear() {
        return LocalDateTime.now().getYear();
    }

    private KiwiDateTime(KiwiDate d, KiwiTime t) {
        this.kiwiDate = d;
        this.kiwiTime = t;
    }

    public static KiwiDateTime ofThisYear(int day, int month, int hour, int minute) {
        return new KiwiDateTime(KiwiDate.of(day, month), KiwiTime.of(hour, minute));
    }

    public static KiwiDateTime ofThisYear(int day, int month, int hour) {
        return new KiwiDateTime(KiwiDate.of(day, month), KiwiTime.of(hour));
    }

    public static KiwiDateTime ofThisYear(int day, int month) {
        return new KiwiDateTime(KiwiDate.of(day, month), KiwiTime.ofEmpty());
    }


    public String unparse() {
        return String.format("%s%s%s", this.kiwiDate.unparse(delimiter), this.delimiter, this.kiwiTime.unparse(delimiter));
    }

    public static KiwiDateTime parse(String strToParse) {
        String[] str = strToParse.split(KiwiDateTime.delimiter);
        int[] values = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            values[i] = Integer.parseInt(str[i]);
        }

        return KiwiDateTime.of(values[0], values[1], values[2], values[3], values[4]);
    }

    public static void main(String[] args) {
        ParseKiwiDateTime p = new ParseKiwiDateTime();
        KiwiDateTime k = p.parse("1/4 6pm");
        System.out.println(k.unparse());
        System.out.println(parse(k.unparse()));
    }

}

/*
need the following parsing functions
only kiwiTime --> set kiwiDate to today [/]
no kiwiTime --> set kiwiTime.isEmpty [/]
no year --> set year to this year [/]

and then natural language processing
 */

