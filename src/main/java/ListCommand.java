/**
 * Represents a command to list out all the tasks
 * within the task list.
 */

public class ListCommand extends Command {

    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed, in this case should be "list"
     */
    public ListCommand(String fullCommand, String typeOfCommand) {
        super(fullCommand, typeOfCommand);
    }

    /**
     * Prints out all the tasks in the task list
     * @param tasks the task list consisting all the tasks
     * @return a String consisting a list of the tasks
     * @throws DukeException if no command is specified
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response;
        String[] splitInputs = this.fullCommand.split(" ");
        if (splitInputs.length == 0) {
            throw new DukeException("Ooh lah lah! Please enter a command or say bye so I can go back to sleep!");
        }
        response = "Here are the tasks in your list:\n" + tasks.listAllTasks();
        return response;
    }
}
