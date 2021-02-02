package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.WrongFormatDukeException;

public class HelpCommand extends Command {

    public HelpCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct help command.
     * If it is, help message is printed.
     * Otherwise, it prints the exception faced.
     */
    private void help() {
        try {
            if (parser.canParseHelpCommand(input)) {
                ui.printHelp();
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    @Override
    public void execute() {
        help();
    }
}
