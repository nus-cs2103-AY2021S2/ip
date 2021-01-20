import java.util.*;

class Task {
    private boolean done;
    private String taskName;
    private String type;

    public Task(String taskName, String type) {
        this.done = false;
        this.taskName = taskName;
        this.type = type;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String doneString = "[ ]";
        String typeString = "[ ]";
        if (done) {
            doneString = "[X]";
        }
        if (this.type.equals("todo")) {
            typeString = "[T]";
        } else if (this.type.equals("deadline")) {
            typeString = "[D]";
        } else if (this.type.equals("event")) {
            typeString = "[E]";
        } else {}
        return typeString + doneString + " " + taskName;
    }
}