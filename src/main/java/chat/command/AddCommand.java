package chat.command;

import chat.ChatException;
import chat.TaskList;
import chat.Storage;
import chat.Ui;
import chat.task.Task;
import chat.task.Todo;
import chat.task.Deadline; 
import chat.task.Event;

/**
 * A type of command that deals with adding tasks to list of tasks
 * from a TaskList object.
 */
public class AddCommand extends Command{
    
    String inputStr;

    /**
     * Initialises AddCommand object.
     *
     * @param inputStr Inputted command string from user to Chat the Cat.
     */
    public AddCommand(String inputStr) { 
        this.inputStr = inputStr;
    }

    /**
     * Method that adds tasks to list of tasks from TaskList object.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     * @throws ChatException If format of command is wrong.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException {
        
        Task task;
        
        if (this.inputStr.startsWith("todo")) {
            task = Todo.createTodo(this.inputStr);
        } else if (this.inputStr.startsWith("deadline")) {
            task = Deadline.createDeadline(this.inputStr);
        } else {
            //inputStr starts with event
            task = Event.createEvent(this.inputStr);
        }
        
        taskList.getTasks().add(task);
        storage.save(taskList);
        ui.showAddSuccess(task, taskList.getTasks().size());
    }
    
}

