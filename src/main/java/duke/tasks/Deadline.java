package duke.tasks;

public class Deadline extends Task {
    protected String after;
    public Deadline (String info, String after) {
        super(info, taskType.Deadline);
        this.after = after;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + this.after + ")";
    }
}

