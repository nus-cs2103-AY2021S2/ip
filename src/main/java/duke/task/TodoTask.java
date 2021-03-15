package duke.task;

/**
 * This class handles the TodoTask.
 */
public class TodoTask extends Task {
    /**
     * Constructor of todoTask
     *
     * @param task
     */
    public TodoTask(String task) {
        super(task);
        divideCommand = task.split(" ");
    }


    public String getName() {
       String result = "";
        for(int i = 1; i < divideCommand.length; i++){
            result += i == divideCommand.length - 1
                    ? divideCommand[i] : divideCommand[i] + " ";
        }
       return result;
    }

    /**
     * String representation of todoTask
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[T][X] " + getName();
        } else {
            return "[T][-] " + getName();
        }
    }
}
