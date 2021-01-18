import java.util.*;

public class Task {
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

    //format how task is printed out
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

}
