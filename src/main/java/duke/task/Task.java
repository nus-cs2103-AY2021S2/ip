package duke.task;

/**
 * Basic task class from which the specific tasks are formed
 */
public class Task {
    String task;
    boolean done = false;
    String[] divideCommand;

    Task(String task) {
        this.task = task;
        divideCommand = task.split(" ");
    }

    public String getType(){
        return divideCommand[0];
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTaskName() {
        return this.task;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[X] " + this.task;
        } else {
            return "[-] " + this.task;
        }
    }
}
