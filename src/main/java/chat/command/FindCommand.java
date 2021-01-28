package chat.command;

import chat.*;
import chat.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    
    String inputStr;
    
    public FindCommand(String inputStr) {
        this.inputStr = inputStr;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        String keyWord = this.inputStr.replace("find","").strip();
        TaskList foundTaskList = new TaskList(new ArrayList<Task>());
        for (Task task : tasks.getTaskList()) {
            if (task.getName().contains(keyWord)) {
                foundTaskList.getTaskList().add(task);
            }
        }
        if (foundTaskList.getTaskList().isEmpty()) {
            throw new ChatException("Sorry! No results found.");
        } else {
            ui.list(foundTaskList);
        }
        
    }
}
