package Duke;

public class DoneTaskCommand extends Command {

    DoneTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct Done command.
     * If it is, it marks the task as done and prints the Done message.
     * Otherwise, it prints the exception faced.
     */
    private void doTask() {
        try {
            if (parser.isCorrectIndexCommand(input, taskList.size())) {
                int index = parser.parseIndex(input);
                taskList.doneTask(index);
                ui.printDoneTask(taskList.getTask(index));
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    @Override
    public void execute() {
        doTask();
    }
}
