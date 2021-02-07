package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * AddCommand class which is a type of Command to be executed.
 */

public class AddCommand extends Command {
    private String userInput;

    /**
     * Handles Todo, Deadline and Event commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     *
     */
    public AddCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     * @throws DukeException in case input is invalid
     */
    public String execute(TaskList tasks, String input, Storage storage) {
        assert !input.isEmpty() : "Input should not be blank.";
        String type = input.split(" ")[0];

        try {
            if (type.equals("todo")) {
                return tasks.addToDo(input);
            } else if (type.equals("deadline")) {
                return tasks.addDeadline(input);
            } else if (type.equals("event")) {
                return tasks.addEvent(input);
            }
            assert false : "All task commands should be handled.";
        } catch (DukeException e) {
            return e.getMessage();
        }
        return "";
    }

    /**
     * Checks if it is time to exit Duke.
     */

    public boolean isExit() {
        return false;
    }
}
