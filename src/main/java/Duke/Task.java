package Duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Mark the task as done and return a success message from getTask()
    public void markAsDone() {
        this.isDone = true;
    }

    // Get the status icon depending on whether the task is done or not
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X symbols if done, else return blank space
    }

    // Message that a task has been successfully added
    public String successMessage(int taskListSize) {
        return String.format("Got it. I've added this task:\n" 
                + "  %s\n" + "Now you have %d tasks in the list.\n",
                this.toString(), taskListSize);
    }

    // Message that a task has been successfully deleted
    public String deleteMessage(int taskListSize) {
        return String.format("Noted. I've removed this task:\n" 
                + "  %s\n" + "Now you have %d tasks in the list.\n",
                this.toString(), taskListSize);
    }

    @Override
    public String toString() {
        return description;
    }
}