/**
 * Represents a command to delete a task from the task list
 */

public class DeleteCommand extends Command {
    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed, in this case should be "delete"
     */
    public DeleteCommand(String fullCommand, String typeOfCommand) {
        super(fullCommand, typeOfCommand);
    }

    /**
     * Deletes a task from the given task list
     * @param tasks the task list consisting the task to be deleted
     * @return a response to be displayed to the user after deleting the task from the
     * task list
     * @throws DukeException if user did not enter a task number to be deleted
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response;
        String[] splitInputs = this.fullCommand.split(" ");
        if (splitInputs.length == 1) {
            throw new DukeException("OOPS! Please give me the number of the task that " +
                    "you want to delete!");
        }
        int taskNo = Integer.parseInt(splitInputs[1]) - 1;
        String toPrint = tasks.remove(taskNo);
        response = "Alright! I've removed this task:\n" + toPrint + "\n" + "Now you have a whopping "
                    + tasks.size() + " task(s) in the list.";
        return response;
    }
}
