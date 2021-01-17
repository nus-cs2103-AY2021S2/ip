public class listItem{
    private final boolean doneStatus;
    private final String task;
    public listItem(String task){
        this.task = task;
        this.doneStatus = false;
    }

    public listItem(String task, boolean isDone){
        this.task = task;
        this.doneStatus = isDone;
    }

    public boolean isDoneStatus() {
        return this.doneStatus;
    }

    public String getTask() {
        return this.task;
    }

    public listItem markAsDone(){
        return new listItem(this.task, true);
    }

    @Override
    public String toString() {
        return (doneStatus == true ? "[X] " : "[ ] ") + this.task;
    }
}