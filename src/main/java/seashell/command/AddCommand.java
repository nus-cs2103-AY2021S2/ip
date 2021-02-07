package seashell.command;

import seashell.SaveHandler;
import seashell.SeashellException;
import seashell.TaskList;
import seashell.task.*;

public class AddCommand implements Command {
    private TaskType taskType;
    private String taskName;
    private String dateTime;

    public AddCommand(TaskType taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateTime = null;
    }

    public AddCommand(TaskType taskType, String taskName, String dateTime) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateTime = dateTime;
    }


    @Override
    public String execute(TaskList taskListObj, SaveHandler saveHandler) throws SeashellException {
        StringBuilder sb = new StringBuilder();
        if (this.taskType.equals(TaskType.TODO)) {
            Todo newTask = new Todo(taskName);
            taskListObj.taskList.add(newTask);
            saveHandler.addTaskToSaveFile(newTask);
            sb.append("Added " + newTask + " to the task list!\n");
        } else if (this.taskType.equals(TaskType.DEADLINE)) {
            Deadline newTask = new Deadline(taskName, this.dateTime);
            taskListObj.taskList.add(newTask);
            saveHandler.addTaskToSaveFile(newTask);
            sb.append("Added " + newTask + " to the task list!\n");
        } else {
            Event newTask = new Event(taskName, this.dateTime);
            taskListObj.taskList.add(newTask);
            saveHandler.addTaskToSaveFile(newTask);
            sb.append("Added " + newTask + " to the task list!\n");
        }
        sb.append("You now have " + taskListObj.taskList.size() + " items in the task list");
        return sb.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
