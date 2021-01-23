/**
 * ExitCommand class which is a type of Command to be executed.
 */

public class ExitCommand extends Command {
    String type;

    /**
     * Handles Exit commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param t details of the task
     *
     */
    public ExitCommand(String t) {
        this.type = t;

    }

    String line = "------------------------------------------";

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param dataHandler handles the various tasks according to their type
     */

    public void execute(TaskList tasks, String input, DataHandler dataHandler) {
        if (input.equals("bye")) {
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
            return;
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return true;
    }
}
