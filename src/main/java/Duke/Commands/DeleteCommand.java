package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import java.io.IOException;

/**
 * A command that represents deleting a <code>Duke.Tasks.Task</code> from a <code>TaskList</code>
 */
public class DeleteCommand extends Command {

    int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command and removes the task from the <code>TaskList</code>.
     * <code>Duke.Ui</code> prints the task deleted.
     * <code>Duke.Storage</code> helps the task to be deleted from the text file.
     *
     * @param taskList contains the task list and operations to manipulate the list
     * @param ui       deals with interactions with the user
     * @param storage  deals with loading tasks from the file and saving tasks in the file
     * @return String that consists of deleted task
     * @throws IOException   is thrown when there is an error related to input and output
     * @throws DukeException is thrown when there is an error related to duke
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        String message;
        message = ui.showDeleteTask(taskList, taskIndex);
        taskList.deleteTask(taskIndex);
        storage.writeToFile(taskList);

        return message;
    }
}
