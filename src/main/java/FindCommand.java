import java.util.List;

/**
 * Represents a command to find tasks that contain a certain keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed, in this case should be "find"
     */
    public FindCommand(String fullCommand, String typeOfCommand) {
        super(fullCommand, typeOfCommand);
    }

    /**
     * Prints out all the tasks that contain the user specified keyword
     * @param tasks the task list consisting all the tasks
     * @return a String consisting a list of the tasks with the user specified keyword
     * @throws DukeException if no keyword is specified, or if user inputs more than one keyword
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("Ooh lah lah! Please give me a keyword to search for!");
        } else if (inputs.length > 2) {
            throw new DukeException("Pardon! I can't search for more than one keyword.");
        } else { }
        String keyword = inputs[1];
        List<Task> toPrint = tasks.findKeyword(keyword);
        for (int i = 1; i < toPrint.size() + 1; i++) {
            response = response + i + "." + toPrint.get(i - 1).toString() + "\n";
        }
        return "Here are the matching tasks in your list:\n" + response;
    }
}
