package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private final LocalDate time;

    /**
     * Event constructor where the boolean isDone is set to false
     * @param a name of Event
     * @param b date of Event in yyyy-mm-dd (e.g. 2021-01-31)
     */
    Event(String a, String b) {
        super(a);
        this.time = LocalDate.parse(b);
    }

    /**
     * Event constructor where the boolean isDone is given
     * @param a name of Event
     * @param b date of Event in yyyy-mm-dd (e.g. 2021-01-31)
     * @param c value that boolean isDone will be set to
     */
    Event(String a, String b, boolean c) {
        super(a, c);
        this.time = LocalDate.parse(b);
    }

    /**
     * Provides the format for which the Event will be saved in the txt file
     * @return a string in the format to be saved in the txt file
     */
    @Override
    String saveName() {
        return String.format("event1!1%s1!1%s1!1%b", super.getName(),
                this.time.toString(), super.getIsDone());
    }

    /**
     * Checks if the Event is to be done on the day
     * @param s the day that is given in yyy-mm-dd format (e.g. 2021-01-31)
     * @return true only if Event falls on the given day and !isDone
     */
    @Override
    boolean onDay(String s) {
        LocalDate day = LocalDate.parse(s);
        if (day.equals(this.time)) {
            return !super.getIsDone();
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
