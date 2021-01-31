package duke.task;
import java.time.LocalDate;
import duke.dukeException.DukeException;

public class Event extends Task{
    protected LocalDate at;

    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    public Event(String name, LocalDate at, boolean done) {
        super(name);
        this.at = at;
        this.done = done;
    }

    @Override
    public void addTask(int count) throws DukeException {
        if(this.name.equals("")) {
            throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            super.addTask(count);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.getMonth() + " " + at.getDayOfMonth() + " " + at.getYear() + ")";
    }
}

