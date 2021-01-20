import java.util.*;

class Task {
    private boolean done;
    private String taskName;
    private String type;
    private String condition;

    public Task(String taskName, String type, String condition) {
        // Thought of incorporating errors into Task instead, but decided to put it in Kobe.
//        System.out.println("taskName: " + taskName);
//        if (taskName.equals("")) {
//            String errMessage = "Oh no! Kobe doesn't want your " + type + " to be empty!";
////            System.out.println("THROWING ERROR1");
//            throw new CustomExceptions.IncompleteDecriptionException(errMessage);
//        }
//        if (!(type.equals("todo") || type.equals("deadline") || type.equals("event"))) {
//            String errMessage = "Oh no! Kobe doesn't know what you mean!";
////            System.out.println("THROWING ERROR2");
//            throw new CustomExceptions.IncorrectDecriptionException(errMessage);
//        }
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