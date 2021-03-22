package seashell.command;

import seashell.storage.SaveHandler;
import seashell.TaskList;
import seashell.task.*;

import static java.util.Objects.requireNonNull;

public class AddCommand implements Command {
    private TaskType taskType;
    private String taskName;
    private String dateTime;

    public AddCommand(TaskType taskType, String taskName) {
        requireNonNull(taskType, taskName);
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateTime = "";
    }

    public AddCommand(TaskType taskType, String taskName, String dateTime) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateTime = dateTime;
    }


    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) {
        StringBuilder sb = new StringBuilder();
        if (this.taskType.equals(TaskType.TODO)) {
            Todo newTask = new Todo(taskName);
            taskList.add(newTask);
            saveHandler.addTaskToSaveFile(newTask);
            sb.append("Added " + newTask + " to the task list!\n");
        } else if (this.taskType.equals(TaskType.DEADLINE)) {
            Deadline newTask = new Deadline(taskName, this.dateTime);
            taskList.add(newTask);
            saveHandler.addTaskToSaveFile(newTask);
            sb.append("Added " + newTask + " to the task list!\n");
        } else {
            Event newTask = new Event(taskName, this.dateTime);
            taskList.add(newTask);
            saveHandler.addTaskToSaveFile(newTask);
            sb.append("Added " + newTask + " to the task list!\n");
        }
        sb.append("You now have " + taskList.size() + " items in the task list");
        return sb.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof AddCommand
                && this.taskType.equals(((AddCommand) other).taskType)
                && this.taskName.equals(((AddCommand) other).taskName)
                && this.dateTime.equals(((AddCommand) other).dateTime));
    }
}
