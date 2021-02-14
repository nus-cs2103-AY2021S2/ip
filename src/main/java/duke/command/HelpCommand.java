package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * ByeCommand class which is a type of Command to be executed.
 */
public class HelpCommand extends Command {
    private String userInput;
    private final String line = "------------------------------------------";

    /**
     * Handles Help commands.
     * Triggers the DataHandler to handle the commands.
     *
     * @param input details of the task
     *
     */
    public HelpCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the Command in DataHandler.
     *
     * @param tasks list of tasks where this new task is added to
     * @param input details of the task
     * @param storage handles the various tasks according to their type
     */

    public String execute(TaskList tasks, String input, Storage storage) {
        assert !input.isEmpty() : "Input should not be blank.";

        String print = "Here is a list of available commands : \n"
                + "\tTo add tasks: \n"
                + "\t \t - todo <task name> \n"
                + "\t \t - deadline <task name> /by <DD-MM-YYYY HH:mm> \n"
                + "\t \t - event <task name> /at <DD-MM-YYYY HH:mm> \n"
                + "\tTo delete task: \n" + "\t \t - delete <task number> \n"
                + "\tTo update task information: \n" + "\t \t - update <task number> /to <task details> \n"
                + "\tTo mark task as done: \n" + "\t \t - done <task number> \n"
                + "\tTo view all tasks added: \n" + "\t \t - list \n"
                + "\tTo find tasks by keyword: \n" + "\t \t - find <keyword> \n"
                + "\tTo view all available commands again: \n" + "\t \t - help \n"
                + "\tTo exit Duke: \n" + "\t \t - bye \n";




        return print;
    }

    /**
     * Checks if it is time to exit Duke.
     */
    public boolean isExit() {
        return true;
    }
}
