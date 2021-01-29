package Duke;

public class DeleteTaskCommand extends Command {

    DeleteTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    public void removeTask() {
        try {
            if (parser.isCorrectIndexCommand(input, taskList.size())) {
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
