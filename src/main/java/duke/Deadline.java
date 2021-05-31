package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private final LocalDate time;

    /**
     * Deadline constructor where the boolean isDone is set to false.
     *
     * @param name name of Deadline
     * @param date date of Deadline in yyyy-mm-dd (e.g. 2021-01-31)
     */
    Deadline(String name, String date) {
        super(name);
        this.time = LocalDate.parse(date);
    }

    /**
     * Deadline constructor where the boolean isDone is given.
     *
     * @param name name of Deadline
     * @param date date of Deadline in yyyy-mm-dd (e.g. 2021-01-31)
     * @param isDone value that boolean isDone will be set to
     */
    Deadline(String name, String date, boolean isDone) {
        super(name, isDone);
        this.time = LocalDate.parse(date);
    }

    /**
     * Provides the format for which the Deadline will be saved in the txt file.
     *
     * @return a string in the format to be saved in the txt file
     */
    @Override
    String saveName() {
        return String.format("deadline1!1%s1!1%s1!1%b", super.getName(),
                this.time.toString(), super.getIsDone());
    }

    /**
     * Checks if the Deadline is to be done on the day.
     *
     * @param date the day that is given in yyy-mm-dd format (e.g. 2021-01-31)
     * @return true only if Deadline falls on the given day and !isDone
     */
    @Override
    boolean isOnDay(String date) {
        LocalDate day = LocalDate.parse(date);
        if (day.equals(this.time)) {
            return !super.getIsDone();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
