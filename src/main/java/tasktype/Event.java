package duke.tasktype;

public class Event extends Task {
    public Event(String name){
        super(name);
        this.type = "E";
    }

    public Event(String name, String dateTime){
        super(name, dateTime);
        this.type = "E";
    }
}
