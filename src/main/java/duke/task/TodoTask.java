package duke.task;

/**
 * This class handles the TodoTask.
 */
public class TodoTask extends Task {
    String[] divideCommand;

    public TodoTask(String task) {
        super(task);
        divideCommand = task.split(" ");
    }


    public String getName() {
        return divideCommand[1] + " " + divideCommand[2];
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[T][X] " + getName();
        } else {
            return "[T][-] " + getName();
        }
    }
}
