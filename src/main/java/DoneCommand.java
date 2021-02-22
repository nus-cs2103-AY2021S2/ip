/**
 * Represents a command to mark a task as done within the task list.
 */

public class DoneCommand extends Command {
    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed, in this case should be "done"
     */
    public DoneCommand(String fullCommand, String typeOfCommand) {
        super(fullCommand, typeOfCommand);
    }

    /**
     * Marks a task within the task list as done
     * @param tasks the task list consisting the task to be marked as done
     * @return a response to be displayed to the user after marking the task as done
     * @throws DukeException if user did not type in a task number
     */
    public String execute(TaskList tasks) throws DukeException {
        String response;
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("Ooh lah lah! Please tell me what to mark as done!");
        }
        int taskNo = Integer.parseInt(String.valueOf(fullCommand.charAt(5))) - 1;
        String toPrint = tasks.makeDone(taskNo);
        response = "Parfait! I've marked this task as done:\n" + toPrint;
        return response;
    }
}
