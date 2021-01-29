package duke;

public class DoneTaskCommand extends Command {

    public DoneTaskCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    public void doTask() {
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

    @Override
    public void execute() {
        doTask();
    }
}
