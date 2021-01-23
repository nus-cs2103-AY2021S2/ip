/**
 * DoneCommand class which is a type of Command to be executed.
 */

public class DoneCommand extends Command {
    String type;

    /**
     * Handles Done commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param t details of the task
     *
     */
    public DoneCommand(String t) {
        this.type = t;

    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param dataHandler handles the various tasks according to their type
     */

    public void execute(TaskList tasks, String input, DataHandler dataHandler) {
        if (Integer.parseInt(input.split(" ")[1]) > tasks.getSize()) {
            new InvalidInstructionException();
            return;
        } else {
            tasks.markDone(input);
            dataHandler.saveData(tasks);
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return false;
    }
}
