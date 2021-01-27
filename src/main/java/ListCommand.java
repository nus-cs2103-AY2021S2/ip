/**
 * Represents a command to list out all the lists
 * within the task list
 */

public class ListCommand extends Command {

    /**
     * Constructor takes in a <code>fullCommand</code>, the full user input
     * consisting of the desired command, and a <code>action</code> which
     * in this case should specify "list"
     * @param fullCommand the full user input
     * @param action should be "list"
     */
    public ListCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    /**
     * Prints out all the tasks in the task list
     * @param tasks the task list containing all the tasks
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 0) {
            throw new DukeException("OOPS! Please enter a command or say bye so I can go back to sleep!");
        }
        Ui.print(
                Aligner.align("Here are the tasks in your list:"));
        tasks.listing();
        Ui.printLine();
    }
}
