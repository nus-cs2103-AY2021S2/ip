package duke;

public class FindCommand extends Command {

    public FindCommand(String command, String input, TaskList taskList) {
        super(command, input, taskList);
    }

    /**
     * Checks if the user input is formatted into a correct find command.
     * If it is, it prints the find message.
     * Otherwise, it prints the exception faced.
     */
    private void find() {
        try {
            if (parser.canParseFindCommand(input)) {
                String keyword = parser.parseKeyword(input);
                TaskList tempTaskList = new TaskList();
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.getTask(i);
                    if (task.hasKeyWord(keyword)) {
                        tempTaskList.addTask(task);
                    }
                }
                if (tempTaskList.size() == 0) {
                    throw new NoSuchKeywordException(keyword);
                } else {
                    ui.printFoundTaskList(tempTaskList);
                }
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    @Override
    public void execute() {
        find();
    }
}
