package duke.task;
import duke.dukeexception.DukeException;

public class Task {
    /** Status of the task */
    protected boolean isDone;
    /** Name of the task */
    protected String name;

    /**
     * Class constructor.
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the status of the current task to completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Adds a task to taskList.
     *
     * @param count  the current count of tasks in the taskList.
     * @throws DukeException  If an input or output
     *                      exception occurred
     */
    public String addTask(int count) throws DukeException {
        String output = "";
        if (this.name.equals("todo")) {
            throw new DukeException("      OOPS!!! The description of a todo cannot be empty.");
        } else {
            output = "     Got it. I've added this task:\n";
            output += "       " + this.toString() + "\n";
            if (count == 1) {
                output += "     Now you have " + count + " task in the list.\n";
            } else {
                output += "     Now you have " + count + " tasks in the list.\n";
            }

        }
        return output;

    }

    /**
     * Overrides toString method.
     *
     * @return the string representation of a task.
     */
    @Override
    public String toString() {
        String outString;
        if (this.isDone) {
            outString = "[X] " + this.name;
        } else {
            outString = "[ ] " + this.name;
        }
        return outString;
    }
}
