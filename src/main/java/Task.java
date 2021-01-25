import java.util.*;

class Task {
    private boolean done;
    private String taskName;
    private String type;
    private String condition;

    public Task(String taskName, String type, String condition) {
        this.done = false;
        this.taskName = taskName;
        this.type = type;
        this.condition = condition;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String doneString = "[ ]";
        String typeString = "[ ]";
        String conditionString = "";

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

        if (!this.condition.equals("")) {
            if (this.condition.substring(0, 2).equals("by")) {
                conditionString = "(by: " + condition.substring(3) + ")";
            } else if (this.condition.substring(0, 2).equals("at")) {
                conditionString = "(at: " + condition.substring(3) + ")";
            }
        }


        return typeString + doneString + " " + taskName + " " + conditionString;
    }
}