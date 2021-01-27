package duke.tasks;


public abstract class Task {
    protected boolean isDone;
    protected String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    //when task is done change the isDone to true
    public void taskDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }
    public abstract String writeToFileFormat();


    //format how task is printed out
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

}
