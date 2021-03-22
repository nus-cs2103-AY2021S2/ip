package seashell.command;

import static java.util.Objects.requireNonNull;

import seashell.TaskList;
import seashell.task.Deadline;
import seashell.task.Event;
import seashell.task.TaskType;
import seashell.task.Todo;


public class AddCommand implements Command {
    private TaskType taskType;
    private String taskName;
    private String dateTime;

    /**
     * Creates a new instance of AddCommand, with the specified task type and task name
     * @param taskType
     * @param taskName
     */
    public AddCommand(TaskType taskType, String taskName) {
        requireNonNull(taskType, taskName);
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateTime = "";
    }

    /**
     * Creates a new instance of AddCommand, with the specified task type, task name and date time
     * @param taskType
     * @param taskName
     */
    public AddCommand(TaskType taskType, String taskName, String dateTime) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateTime = dateTime;
    }


    @Override
    public String execute(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        if (this.taskType.equals(TaskType.TODO)) {
            Todo newTask = new Todo(taskName);
            taskList.add(newTask);
            sb.append("Added ").append(newTask).append(" to the task list!\n");
        } else if (this.taskType.equals(TaskType.DEADLINE)) {
            Deadline newTask = new Deadline(taskName, this.dateTime);
            taskList.add(newTask);
            sb.append("Added ").append(newTask).append(" to the task list!\n");
        } else {
            Event newTask = new Event(taskName, this.dateTime);
            taskList.add(newTask);
            sb.append("Added ").append(newTask).append(" to the task list!\n");
        }
        sb.append("You now have ").append(taskList.size()).append(" items in the task list");
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
