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
        assert inputStr != "";
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
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        
        String keyword = this.inputStr.replace("find","").strip();
        TaskList foundTaskList = getFoundTaskList(keyword, taskList);
        if (foundTaskList.getTasks().isEmpty()) {
            throw new ChatException("Sorry! No results found.");
        } 
        ui.list(foundTaskList);
    }

    /**
     * Given a particular keyword, this function returns a TaskList object containing list of tasks 
     * that have that keyword in it's name.
     * 
     * @param keyword Find tasks based on this keyword.
     * @param taskList TaskList object containing all tasks.
     * @return TaskList object containing list of tasks that fit the keyword.
     */
    public TaskList getFoundTaskList(String keyword, TaskList taskList) {
        assert taskList != null;
        assert keyword != "";
        
        String keyWord = this.inputStr.replace("find","").strip();
        TaskList foundTaskList = new TaskList(new ArrayList<Task>());
        for (Task task : taskList.getTasks()) {
            if (task.getName().contains(keyword)) {
                foundTaskList.getTasks().add(task);
            }
        }
        return foundTaskList;
    }
    
}
