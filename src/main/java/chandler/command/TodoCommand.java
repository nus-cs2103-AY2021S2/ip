package chandler.command;

import chandler.ChandlerException;
import chandler.Storage;
import chandler.TaskList;
import chandler.task.TodoTask;
import chandler.ui.Ui;

/**
 * The TodoCommand class encapsulates information and methods about a TodoCommand.
 */
public class TodoCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a Todo Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying todo messages to the CLI.
     */
    public TodoCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    /**
     * Processes the todo command by adding a new todo task to the list of tasks,
     * writing it into the saved data file of tasks and displaying a message on the CLI.
     *
     * @param storage The storage object that writes data to the saved data file of tasks.
     * @param taskList The list of tasks.
     * @throws ChandlerException if the format of the Todo command is invalid.
     */
    @Override
    public String run(Storage storage, TaskList taskList) throws ChandlerException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Tasklist cannot be null";

        if (fullCmdStrArray.length == 1) { // handle todo without parameters
            throw new ChandlerException(ui.todoFormatError());
        }
        String taskName = fullCmd.substring(5); // remove "todo "
        TodoTask newTodoTask = new TodoTask(taskName);
        taskList.add(newTodoTask);

        storage.saveTaskList(taskList); // update saved task list
        return ui.returnAddToListMsg(newTodoTask, taskList);
    }

}
