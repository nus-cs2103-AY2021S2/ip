package duke.tasks;


public abstract class Task {
    protected boolean isDone;
    protected String taskName;

    /**
     *  Task constructor.
     *
     *  @param taskName represents the name of the task.
     */
    public Task(String taskName) {
        assert taskName.length() > 0 : "Error: you must enter a task name for the task";
        this.taskName = taskName;
        this.isDone = false;
    }

    //when task is done change the isDone to true
    public void setTaskDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }
    public abstract String writeToFileFormat();


    /**
     *  Formats the task to string format.
     *
     *  @return String that is in the correct format.
     */
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

}
