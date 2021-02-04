package chat.command;

import chat.*;
import chat.task.Task;

import java.util.ArrayList;

/**
 * A type of command that finds tasks based on keywords given by user
 */
public class FindCommand extends Command {
    
    String inputStr;

    /**
     * Initialises FindCommand object.
     * 
     * @param inputStr Inputted command string from user to Chat the Cat.
     */
    public FindCommand(String inputStr) {
        this.inputStr = inputStr;
    }

    /**
     * Method that finds tasks from list of tasks from TaskList object.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     * @throws ChatException If no tasks match keyword given by user.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException {
        String keyWord = this.inputStr.replace("find","").strip();
        TaskList foundTaskList = new TaskList(new ArrayList<Task>());
        for (Task task : taskList.getTasks()) {
            if (task.getName().contains(keyWord)) {
                foundTaskList.getTasks().add(task);
            }
        }
        if (foundTaskList.getTasks().isEmpty()) {
            throw new ChatException("Sorry! No results found.");
        } else {
            ui.list(foundTaskList);
        }
        
    }
}
