public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Mark the task as done and return a success message from getTask()
    public String markAsDone() {
        this.isDone = true;
        return getTask();
    }

    // Get the status icon depending on whether the task is done or not
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return X symbols if done, else return blank space
    }

    // Return the task in the format [tick / X] <Task Description>
    public String getTask() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}