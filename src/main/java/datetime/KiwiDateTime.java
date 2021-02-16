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

    public static void main(String[] args) {
        // todo, any of the java methods throw a datetime exception...
        print(LocalDateTime.now());

        print(
                KiwiDateTime.ofThisYear(21, 4),
                KiwiDateTime.ofThisYear(31, 3, 12),
                KiwiDateTime.ofThisYear(5, 2, 23, 11),
                KiwiDate.of(21, 4),
                KiwiTime.of(13, 3)
        );
    }
}

/*
need the following parsing functions
only kiwiTime --> set kiwiDate to today [/]
no kiwiTime --> set kiwiTime.isEmpty [/]
no year --> set year to this year [/]

and then natural language processing
 */

