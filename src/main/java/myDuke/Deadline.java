package myDuke;

import java.time.LocalDate;

/**
 * Represents a Deadline Task
 */
public class Deadline extends Task {
    LocalDate deadline;

    Deadline(LocalDate deadline, String s, boolean b) {
        super(s, b);
        this.deadline = deadline;
    }

    @Override
    public Deadline setAsDone() {
        return new Deadline(this.deadline, this.info, true);
    }

    @Override
    public Deadline setAsUndone() {
        return new Deadline(this.deadline, this.info, false);
    }

    String getTimeDisplay() { //format: MMM dd yyyy
        String year = Integer.toString(this.deadline.getYear());
        String month = this.deadline.getMonth().name().substring(0,3);
        String day = this.deadline.getDayOfMonth() > 9
                ? Integer.toString(this.deadline.getDayOfMonth())
                : "0" + Integer.toString(this.deadline.getDayOfMonth());

        return month + " " + day + " " + year;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.getTimeDisplay() + ")";
    }
}