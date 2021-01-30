package yoda.command;

import yoda.task.Task;
import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;
public class EditCommand extends Command{

    public EditCommand(String[] details){
        super(details);
        taskType = Input.valueOf(details[0]);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskToBeEdited = Integer.parseInt(details[1]) - 1;
        Task task = taskList.accessTask(taskToBeEdited);
        if (taskType == Input.DELETE) {
            taskList.deleteTask(taskToBeEdited);
            System.out.println("Noted! I've deleted this task:"
                    + task
                    + "Now you have " + taskList.length() + "tasks left in the list!");
        } else if (taskType == Input.DONE) {
            taskList.markTaskAsDone(taskToBeEdited);
            System.out.println("Noice! I've marked this task as done:"
                    + task
                    + "Now you have " + taskList.length() + "tasks left in the list!");
        }
        storage.write(taskList);
    }
}
