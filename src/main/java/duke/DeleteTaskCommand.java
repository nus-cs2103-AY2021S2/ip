package duke;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    public void removeTask() {
        try {
            if (parser.canParseIndexCommand(input, taskList.getSize())) {
                int index = parser.parseIndex(input);
                Task deleted = taskList.deleteTask(index);
                ui.printDeleteTask(deleted);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    @Override
    public void execute() {
        removeTask();
    }
}
