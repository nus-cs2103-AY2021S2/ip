public class ListTaskCommand extends Command {

    ListTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    private void listTask() {
        try {
            if (parser.isCorrectList(input)) {
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
