package myDuke;

import java.time.LocalDate;

public class Event extends Task {
    LocalDate date;

    Event(LocalDate date, String s, boolean b) {
        super(s, b);
        this.date = date;
    }

    @Override
    public Event setAsDone() {
        return new Event(this.date, this.info, true);
    }

    @Override
    public Event setAsUndone() {
        return new Event(this.date, this.info, false);
    }

    String getTimeDisplay() { //format: MMM dd yyyy
        String year = Integer.toString(this.date.getYear());
        String month = this.date.getMonth().name().substring(0,3);
        String day = this.date.getDayOfMonth() > 9
                ? Integer.toString(this.date.getDayOfMonth())
                : "0" + Integer.toString(this.date.getDayOfMonth());

        return month + " " + day + " " + year;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.getTimeDisplay() + ")";
    }
}