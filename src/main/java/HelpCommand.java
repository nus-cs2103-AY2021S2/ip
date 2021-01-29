public class HelpCommand extends Command {

    HelpCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    public void help() {
        try {
            if (parser.isCorrectHelp(input)) {
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
