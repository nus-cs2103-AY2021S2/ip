package duke;

public class ListTaskCommand extends Command {

    public ListTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    private void listTask() {
        try {
            if (parser.canParseListCommand(input)) {
                ui.printTaskList(taskList);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    @Override
    public void execute() {
        listTask();
    }
}
