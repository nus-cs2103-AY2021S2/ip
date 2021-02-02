package duke;

public class DoneTaskCommand extends Command {

    public DoneTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct Done command.
     * If it is, it marks the task as done and prints the Done message.
     * Otherwise, it prints the exception faced.
     */
    private void doTask() {
        try {
            if (parser.canParseIndexCommand(input, taskList.getSize())) {
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

    /**
     * Executes the done task command.
     */
    @Override
    public void execute() {
        doTask();
    }
}
