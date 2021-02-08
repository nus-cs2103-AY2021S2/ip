package duke;

public class HelpCommand extends Command {

    public HelpCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct help command.
     * If it is, help message is printed.
     * Otherwise, it prints the exception faced.
     */
    private String help() {
        try {
            if (parser.canParseHelpCommand(input)) {
                return ui.printHelp();
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }

    /**
     * Executes the help command.
     */
    @Override
    public String execute() {
        return help();
    }
}
