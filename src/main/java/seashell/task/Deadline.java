package seashell.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a new instance of Deadline object with the specified name and date time
     * @param name
     * @param by
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    /**
     * Creates a new instance of Deadline object with the specified name, date time and whether it is done
     * @param name
     * @param by
     * @param isDone
     */
    public Deadline(String name, LocalDate by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    protected Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public Deadline setDone() {
        Deadline doneTask = new Deadline(this.getName(), this.by, true);
        return doneTask;
    }

    @Override
    public String getSaveText() {
        return "D | " + super.getSaveText() + " /by " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeFormatter.ofPattern("MMM dd yyyy").format(this.by) + ")";
    }

}
