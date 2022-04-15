package main.duke.tasktype;

public class Deadline extends Task {
    /**
     * Constructor for basic deadline
     * @param name task information
     */
    public Deadline (String name){
        super(name);
        this.type = "D";
    }

    /**
     * Constructor for deadline with date time format
     * @param name task information
     * @param dateTime Date time format
     */
    public Deadline(String name, String dateTime){
        super(name,dateTime);
        this.type = "D";
    }
}
