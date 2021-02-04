package chat.command;

import chat.ChatException;
import chat.TaskList;
import chat.Storage;
import chat.Ui;
import chat.task.Task;

/**
 * A type of command that deals with marking tasks from list of tasks 
 * from TaskList object, as done once completed.
 */
public class DoneCommand extends Command {

    String inputStr;

    /**
     * Initialises DoneCommand object.
     *
     * @param inputStr Inputted command string from user to Chat the Cat.
     */
    public DoneCommand(String inputStr) { 
        this.inputStr = inputStr; 
    }

    /**
     * Method that marks tasks as done from list of tasks from TaskList object.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     * @throws ChatException If format of command is wrong.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException {
        
        Task task = checkCommandIndex(taskList, "done", this.inputStr);
        
        if (task.getIsDone()) {
            throw new ChatException(String.format("Task already completed\n%s", task));
        } else {
            task.completed();
            storage.save(taskList);
            ui.showWellDone(task);
        }
    }
    
}
