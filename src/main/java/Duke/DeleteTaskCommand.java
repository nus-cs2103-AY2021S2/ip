package Duke;

public class DeleteTaskCommand extends Command {

    DeleteTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct Delete command.
     * If it is, it removes the task and prints the Delete message.
     * Otherwise, it prints the exception faced.
     */
    private void removeTask() {
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
