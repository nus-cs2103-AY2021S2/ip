package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.TodoTask;

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
     * @throws DukeException if the format of the Todo command is invalid.
     */
    @Override
    public String run(Storage storage, TaskList taskList) throws DukeException {
        if (fullCmdStrArray.length == 1) { // handle todo without parameters
            throw new DukeException("Sorry human, please enter a name for this task.");
        }
        String taskName = fullCmd.substring(5); // remove "todo "
        TodoTask newTodoTask = new TodoTask(taskName);

        taskList.add(newTodoTask);
        storage.saveTaskList(taskList);
        return ui.returnAddToListMsg(newTodoTask, taskList);
    }

}
