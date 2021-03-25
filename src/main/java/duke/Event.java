package duke;

import java.util.Date;

public class Event extends Task {
    public Date start;
    public Date end;

    public Event(String description, Date start, Date end) {
        super(description, "[E]");
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString(){
        return this.typeBox + this.checkBox + " " + this.description + "/from " + this.start + " to " + this.end;
    }
}