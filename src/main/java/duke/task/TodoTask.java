package duke.task;
/**
 * This class handles the TodoTask.
 */
public class TodoTask extends Task {
    public TodoTask(String task) {
        super(task);
    }

    String[] divideCommand = task.split(" ");

    public String createTask(String[] c) {
        String result = "";
        for (int i = 1; i < c.length; i++) {
            result += i == c.length - 1 ? c[i] : c[i] + " ";
        }
        return result;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "T||X " + createTask(divideCommand);
        } else {
            return "T||0 " + createTask(divideCommand);
        }
    }
}
