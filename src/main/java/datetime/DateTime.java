package datetime;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This is a wrapper class for a local date time object, wrapped with functions that parse
 * user input strings to dateTime fields for tasks.
 */
public class DateTime {
    private LocalDateTime dateTime;

    private DateTime(LocalDateTime ldt) {
        this.dateTime = ldt;
    }

    // todo possible inheritance: subclasses for datetimes
    // or just customize toStrings not to print minute / hour if don't have, or year if this year
    @Override
    public String toString() {
        return String.format("%d/%d %d:%d",
                dateTime.getDayOfMonth(),
                1, //dateTime.getMonth(),
                dateTime.getHour(),
                dateTime.getMinute());
    }

    static void print(Object... objects) {
        for (Object o : objects) {
            System.out.println(o);
        }
    }


    public static void main(String[] args) {
        print(LocalDateTime.now());

        //            TimeUnit.SECONDS.sleep(1);
        print(LocalDateTime.now());

        LocalDateTime a =
                LocalDateTime.of(2021, 4, 23, 6, 38);

//        print(a, new DateTime(a));

        DateTime d = new DateTime(a);
        print(d);

//        LocalDateTime b = LocalDateTime.of(2021, 4, 21, 6);
    }
}

/*
need the following parsing functions
only time --> set date to today
no time --> set time.isEmpty
no year --> set year to this year

and then natural language processing
 */
