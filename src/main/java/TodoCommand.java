import java.util.List;

public class TodoCommand extends Command{

    private String command;

    public TodoCommand(String command){
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException {
        String description = "";
        String[] commandArr = command.split(" ");
        if (command.equals("todo")) {
            throw new DukeMissingInputException("OOPS!!! The description of a todo cannot be empty.");
        } else {

            for (int i = 1; i < commandArr.length; i++) {
                description += (commandArr[i] + " ");
            }
        }
        description = description.trim();
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        ui.showTaskAdded(newTodo);
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
