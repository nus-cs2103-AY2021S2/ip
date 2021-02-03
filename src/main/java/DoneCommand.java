/**
 * Represents a command to mark a task as done within the task list
 */

public class DoneCommand extends Command {
    /**
     * Constructor takes in a <code>fullCommand</code>, the full user input
     * consisting of the desired command, and a <code>action</code> which
     * in this case should specify "done"
     * @param fullCommand the full user input
     * @param action should be "done"
     */
    public DoneCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    /**
     * Marks a task within the task list as done
     * @param tasks the task list consisting the task to be marked as done
     * @throws DukeException if user did not type in a task number
     */
    public String execute(TaskList tasks) throws DukeException {
        String response;
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("OOPS! Please tell me what to mark as done!");
        }
        int taskNo = Integer.parseInt(String.valueOf(fullCommand.charAt(5))) - 1;
        String toPrint = tasks.makeDone(taskNo);
        response = "Good job! I've marked this task as done:\n" + toPrint;
        return response;
    }
}
