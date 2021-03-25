package duke;

import java.util.Date;

public class Deadline extends Task {
    public Date date;

    public Deadline(String description, Date date) {
        super(description, "[D]");
        this.date = date;
    }

    @Override
    public String toString(){
        return this.typeBox + this.checkBox + " " + this.description + "/by " + this.date;
    }
}