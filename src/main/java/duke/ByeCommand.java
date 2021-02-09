package duke;

public class ByeCommand extends Command {

    public ByeCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Gets the String form of the bye command.
     *
     * @return String of bye command.
     */
    private String bye() {
        try {
            if (input.equals("bye")) {
                return ui.getFarewell();
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes Bye command.
     *
     * @return String of bye command.
     */
    @Override
    public String execute() {
        return bye();
    }
}
