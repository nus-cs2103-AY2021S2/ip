package duke.task;

public abstract class ListItem {
    private final boolean doneStatus;
    private final String task;
    public ListItem(String task){
        this.task = task;
        this.doneStatus = false;
    }

    public ListItem(String task, boolean isDone){
        this.task = task;
        this.doneStatus = isDone;
    }

    public boolean getDoneStatus() {
        return this.doneStatus;
    }

    public String getTask() {
        return this.task;
    }

    public abstract ListItem markAsDone();

    public abstract String getDate();

    @Override
    public String toString() {
        return (doneStatus == true ? "[X] " : "[ ] ") + this.task;
    }
}