package duke.commands;

import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private int indexOfTaskToDelete;

    public DeleteCommand(TaskList taskList, int indexOfTaskToDelete) {
        super(taskList);
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }

    public TaskList execute() {
        TaskList tasks = this.getTaskList();
        tasks.remove(this.indexOfTaskToDelete - 1);
        return tasks;
    }

    public String toString() {
        String message = "Noted. I've removed this task: \n";
        message += this.getTaskList().get(this.indexOfTaskToDelete - 1).toString() + "\n";
        message += " Now you have " + (this.getTaskList().size() - 1) + " tasks in the list.";
        return message;
    }
}