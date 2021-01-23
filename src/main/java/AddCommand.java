/**
 * AddCommand class which is a type of Command to be executed.
 */

public class AddCommand extends Command {
    String type;

    /**
     * Handles Todo, Deadline and Event commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param t details of the task
     *
     */

    public AddCommand(String t) {
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
        String type = input.split(" ")[0];

        if (type.equals("todo")) {
            tasks.addToDo(input);
            dataHandler.saveData(tasks);
        } else if (type.equals("deadline")) {
            tasks.addDeadline(input);
            dataHandler.saveData(tasks);
        } else if (type.equals("event")) {
            tasks.addEvent(input);
            dataHandler.saveData(tasks);
        } else {
            new InvalidInstructionException();
            return;
        }
    }

    /**
     * Checks if it is time to exit Duke.
     */

    public boolean isExit() {
        return false;
    }
}
