import java.time.LocalDate;

public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }
    public Deadline(String name, LocalDate by, boolean done) {
        super(name);
        this.by = by;
        this.done = done;
    }

    @Override
    public void addTask(int count) throws DukeException {
        if(this.name.equals("")) {
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            super.addTask(count);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getMonth() + " " + by.getDayOfMonth()+ " "+ by.getYear() + ")";
    }
}

