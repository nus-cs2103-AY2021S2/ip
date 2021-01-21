import java.util.List;
import java.util.ArrayList;

/**
 * A list that keeps track of tasks.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets number of tasks in task list.
     * @return Number of tasks in task list.
     */
    public int getNumTasks() {
        return this.taskList.size();
    }

    /**
     * Adds a task to the task list.
     * @param task Task to add to the list.
     * @return Copy of the task list with the new task added.
     */
    public TaskList addTask(String task) throws OwenException {
        String[] taskSplit = task.split(" ", 2);
        String taskType = taskSplit[0];
        
        String taskArgs;
        if (taskSplit.length > 1) {
            taskArgs = taskSplit[1];
        } else {
            taskArgs = "";
        }

        Task newTask;

        switch (taskType) {
        case "todo":
            newTask = new TodoTask(taskArgs);
            break;
        case "event":
            newTask = new EventTask(taskArgs);
            break;
        case "deadline":
            newTask = new DeadlineTask(taskArgs);
            break;
        default:
            newTask = null;
        }

        List<Task> newTaskList = new ArrayList<>(this.taskList);
        newTaskList.add(newTask);
        return new TaskList(newTaskList);
    }

    /**
     * Gets the nth task from the list.
     * @param taskNumber The number of the task to get.
     * @return The nth task of the list.
     */
    public Task getTask(int taskNumber) {
        taskNumber -= 1;
        return this.taskList.get(taskNumber);
    }

    /**
     * Marks the nth task as done.
     * @param taskNumber The number of the task to mark as done.
     * @return Copy of the task list with the nth task marked as done.
     */
    public TaskList markAsDone(int taskNumber) throws OwenException {
        if (this.taskList.size() < taskNumber || taskNumber <= 0) {
            throw new OwenException("Task " + taskNumber + " does not exist...");
        }

        taskNumber -= 1;
        List<Task> newTaskList = new ArrayList<>(this.taskList);
        newTaskList.set(taskNumber, newTaskList.get(taskNumber).markAsDone());
        return new TaskList(newTaskList);
    }

    @Override
    public String toString() {
        String[] taskListStrings = new String[this.taskList.size()];
        String taskListFormat = "%d. %s";
        for (int i = 0; i < this.taskList.size(); i++) {
            taskListStrings[i] = String.format(taskListFormat, i + 1, this.taskList.get(i));
        }
        return String.join("\n", taskListStrings);
    }
}
