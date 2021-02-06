package seashell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    /**
     * Create a deadline object with specified name and
     * @param name
     */
    protected Event(String name, String at) {
        super(name);
        this.at = LocalDate.parse(at);
    }

    protected Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }


    @Override
    public Event setDone() {
        Event doneTask = new Event(this.getName(), this.at);
        doneTask.setDone();
        return doneTask;
    }

    @Override
    public String getSaveText() {
        return "E | " + super.getSaveText() + " /at " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeFormatter.ofPattern("MMM dd yyyy").format(this.at) + ")";
    }

}
