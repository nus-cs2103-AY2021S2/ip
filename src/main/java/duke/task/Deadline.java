package duke.task;

import java.time.LocalDate;

public class Deadline extends Task{

    protected LocalDate by;

    public Deadline(String description, LocalDate by){
        super(description);
        this.by = by;
        this.type = "deadline";
    }

    public String getBy(){
        return this.by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s %s %s)",
                        this.by.getMonth(), this.by.getDayOfMonth(), this.by.getYear());
    }
}
