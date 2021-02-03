/**
 * To do command that creates a to do task.
 */
public class TodoCommand extends Command {

    private String command;

    /**
     * Constructor method.
     *
     * @param command
     */
    public TodoCommand(String command) {
        this.command = command;
    }

    /**
     * Execute method for To do command.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If list command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        String description = "";
        String[] commandArr = command.split(" ");
        if (command.equals("todo")) {
            throw new DukeMissingInputException("OOPS! The description of a todo cannot be empty.");
        } else {
            for (int i = 1; i < commandArr.length; i++) {
                description += (commandArr[i] + " ");
            }
        }
        description = description.trim();
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        storage.save(taskList.getTaskList());
        return ui.showTaskAdded(newTodo);
    }

    /**
     * Indicates whether command is an exit command.
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

