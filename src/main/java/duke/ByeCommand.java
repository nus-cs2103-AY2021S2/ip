package duke;

public class ByeCommand extends Command {
    public ByeCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    private String bye() {
        try {
            if (input.equals("bye")) {
                return ui.printBye();
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }

    @Override
    public String execute() {
        return bye();
    }
}
