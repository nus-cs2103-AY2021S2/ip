package duke;

public class DoneTaskCommand extends Command {

    public DoneTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct Done command.
     * If it is, it marks the task as done and prints the Done message.
     * Otherwise, it prints the exception faced.
     *
     * @return String of done task command.
     */
    private String doTask() {
        try {
            if (parser.canParseIndexCommand(input, taskList.getSize())) {
                int index = parser.parseIndex(input, taskList.getSize());
                assert index < taskList.getSize() && index >= 0: "Index out of bounds";
                taskList.doneTask(index);
                return ui.getDoneTask(taskList.getTask(index));
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the done task command.
     *
     * @return String of done task command.
     */
    @Override
    public String execute() {
        return doTask();
    }
}
