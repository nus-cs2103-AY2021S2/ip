package duke.command;

import duke.exception.InvalidInstructionException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

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
     * @param storage handles the various tasks according to their type
     * @throws IOException in case file is corrupt
     */

    public void execute(TaskList tasks, String input, Storage storage) throws IOException {
        String type = input.split(" ")[0];

        if (type.equals("todo")) {
            tasks.addToDo(input);
            storage.saveData(tasks);
        } else if (type.equals("deadline")) {
            tasks.addDeadline(input);
            storage.saveData(tasks);
        } else if (type.equals("event")) {
            tasks.addEvent(input);
            storage.saveData(tasks);
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
