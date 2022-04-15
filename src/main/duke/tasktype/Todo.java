package main.duke.tasktype;

public class Todo extends Task {
    /**
     * Constructor for Todo
     * @param name task information
     */
    public Todo (String name){
        super(name);
        this.type = "T";
    }
}
