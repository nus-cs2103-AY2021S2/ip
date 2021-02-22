package duke.tasktype;

public class Deadline extends Task {
    public Deadline (String name){
        super(name);
        this.type = "D";
    }

    public Deadline(String name, String dateTime){
        super(name,dateTime);
        this.type = "D";
    }
}
