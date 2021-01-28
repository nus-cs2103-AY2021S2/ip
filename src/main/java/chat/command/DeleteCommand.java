package chat.command;

import chat.ChatException;
import chat.TaskList;
import chat.Storage;
import chat.Ui;
import chat.task.Task;

/**
 * A type of command that deals with deleting tasks from a list of tasks
 * from a TaskList object.
 */

public class DeleteCommand extends Command{
    
    String inputStr;

    /**
     * Initialises DeleteCommand object.
     * 
     * @param inputStr Inputted command string from user to Chat the Cat.
     */
    public DeleteCommand(String inputStr) { 
        this.inputStr = inputStr;
    }

    /**
     * Method that deletes tasks from list of tasks from TaskList object.
     * 
     * @param tasks TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     * @throws ChatException If format of command is wrong.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        Task task = checkCommandIndex(tasks, "delete", this.inputStr);
        tasks.getTaskList().remove(task);
        ui.printDeleteSuccess(task, tasks.getTaskList().size());
        storage.save(tasks);
    }
    
}
