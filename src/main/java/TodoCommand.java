import java.io.IOException;

/**
 * To do command that creates a to do task.
 */
public class TodoCommand extends Command {

    private String command;

    /**
     * Constructs a todo command.
     *
     * @param command user command.
     */
    public TodoCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the Todo command by creating a new Todo task.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If list command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        String description = "";
        String[] commandArr = command.split(" ");
        if (commandArr.length == 1) {
            throw new DukeMissingInputException("OOPS! The description of a todo cannot be empty.");
        }
        for (int i = 1; i < commandArr.length; i++) {
            description += (commandArr[i] + " ");
        }
        try {
            description = description.trim();
            Todo newTodo = new Todo(description);
            taskList.add(newTodo);
            storage.save(taskList.getTaskList());
            return ui.showTaskAdded(newTodo);
        } catch (IOException e) {
            throw new DukeIoException("File error: Could not save.");
        }
    }
}
