package duke;

public class Task {
    public String taskName;
    public boolean taskDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[X] " + this.taskName;
        } else {
            ans = "[ ] " + this.taskName;
        }
        return ans;
    }

    public void markAsDone() {
        this.taskDone = true;
    }
}
