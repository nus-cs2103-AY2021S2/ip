package main.duke.tasktype;

public class Event extends Task {
    /**
     * Constructor for basic event
     * @param name task information
     */
    public Event(String name){
        super(name);
        this.type = "E";
    }

    /**
     * Constructor for event with date time format
     * @param name task information
     * @param dateTime Date time format
     */
    public Event(String name, String dateTime){
        super(name, dateTime);
        this.type = "E";
    }
}
