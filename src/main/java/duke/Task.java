package duke;
/**
 * Stores information of a Task - the description and a boolean flag to indicate if the task is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String letterCode;

    protected Task(String description,String letterCode) {
        this.description = description;
        this.letterCode = letterCode;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        isDone = true;
    }

    public String getLetterCode(){
        return this.letterCode;
    }

    public String getSavedStringFormat() {
        String s = (isDone) ? "1" : "0";
        return letterCode + " | " +  s + " | " + description;
    }

    @Override
    public String toString() {
        String mark = (isDone ? "X" : " ");
        return "[" + mark + "] " + description;
    }

    public String getDescription() {
        return this.description;
    }

}
