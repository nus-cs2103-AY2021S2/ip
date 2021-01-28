package percy.command;

import percy.task.TaskList;
import percy.ui.Ui;
import percy.storage.Storage;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    public ListCommand() {
        super(false);
    }

    /**
     * Executes the Todo command which creates a Todo Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) {
        return Ui.makeListMsg(taskList);
    }
}
