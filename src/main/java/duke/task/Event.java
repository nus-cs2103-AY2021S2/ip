package duke.task;

import java.time.LocalDate;

public class Event extends Task{

    protected LocalDate at;

    public Event(String description, LocalDate at){
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(at: %s %s %s)",
                this.at.getMonth(), this.at.getDayOfMonth(), this.at.getYear());
    }
}
