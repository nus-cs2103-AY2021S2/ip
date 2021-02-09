package duke;

public class HelpCommand extends Command {

    public HelpCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct help command.
     * If it is, help message is printed.
     * Otherwise, it prints the exception faced.
     *
     * @return String of help command.
     */
    private String help() {
        try {
            if (parser.canParseHelpCommand(input)) {
                return ui.getHelp();
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the help command.
     *
     * @return String of help command.
     */
    @Override
    public String execute() {
        return help();
    }
}
