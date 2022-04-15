package owen.task;

import java.util.ArrayList;
import java.util.List;

import owen.OwenException;

/**
 * A list that keeps track of tasks.
 */
public class TaskList {
    private enum TaskType {
        TODO,
        EVENT,
        DEADLINE,
    }

    private final List<Task> taskList;

    /**
     * Creates empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets number of tasks in task list.
     *
     * @return Number of tasks in task list.
     */
    public int getNumTasks() {
        return this.taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add to the list.
     * @return Copy of the task list with the new task added.
     * @throws OwenException Task could not be parsed.
     */
    public TaskList addTask(String task) throws OwenException {
        String[] taskSplit = task.split(" ", 2);
        String taskTypeString = taskSplit[0];

        TaskType taskType;
        try {
            taskType = TaskType.valueOf(taskTypeString.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new OwenException("Unknown task type...");
        }

        String taskArgs;
        if (taskSplit.length > 1) {
            taskArgs = taskSplit[1];
        } else {
            taskArgs = "";
        }

        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new TodoTask(taskArgs);
            break;
        case EVENT:
            newTask = new EventTask(taskArgs);
            break;
        case DEADLINE:
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
     *
     * @param taskNumber The number of the task to get.
     * @return The nth task of the list.
     */
    public Task getTask(int taskNumber) {
        taskNumber -= 1;
        return this.taskList.get(taskNumber);
    }

    /**
     * Marks the nth task as done.
     *
     * @param taskNumber The number of the task to mark as done.
     * @return Copy of the task list with the nth task marked as done.
     * @throws OwenException Task number does not exist.
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

    /**
     * Deletes the nth task from the task list.
     *
     * @param taskNumber The number of the task to delete.
     * @return Copy of the task list with the nth task deleted.
     * @throws OwenException Task number does not exist.
     */
    public TaskList deleteTask(int taskNumber) throws OwenException {
        if (this.taskList.size() < taskNumber || taskNumber <= 0) {
            throw new OwenException("Task " + taskNumber + " does not exist...");
        }

        taskNumber -= 1;
        List<Task> newTaskList = new ArrayList<>(this.taskList);
        newTaskList.remove(taskNumber);
        return new TaskList(newTaskList);
    }

    /**
     * Finds tasks that contain a search string.
     *
     * @param searchString String to search within task descriptions.
     * @return Tasks that contain search string.
     */
    public String findTask(String searchString) {
        List<String> taskListStrings = new ArrayList<>();

        String taskListFormat = "%d. %s";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String description = task.getDescription();
            if (description.contains(searchString)) {
                taskListStrings.add(String.format(taskListFormat, i + 1, task));
            }
        }

        return String.join("\n", taskListStrings);
    }

    /**
     * Gets list of tasks that are due within specified days.
     *
     * @param days Days to check if tasks are due by.
     * @return List of tasks that are due.
     */
    public String getDueTasks(long days) {
        List<String> taskListStrings = new ArrayList<>();

        String taskListFormat = "%d. %s";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.isDue(days)) {
                taskListStrings.add(String.format(taskListFormat, i + 1, task));
            }
        }

        return String.join("\n", taskListStrings);
    }

    /**
     * Serializes TaskList into a String.
     *
     * @return Serialized version of TaskList as a String.
     */
    public String serialize() {
        String[] taskStrings = new String[this.taskList.size()];
        for (int i = 0; i < this.taskList.size(); i++) {
            taskStrings[i] = this.taskList.get(i).serialize();
        }
        return String.join("\n", taskStrings);
    }

    /**
     * Deserializes Task and add it to TaskList.
     *
     * @param taskString Task string to deserialize.
     * @return Updated TaskList with deserialized task added.
     * @throws OwenException Task could not be deserialized.
     */
    public TaskList deserializeTask(String taskString) throws OwenException {
        assert taskString.length() > 0 : "taskString cannot be empty";
        List<Task> newTaskList = new ArrayList<>(this.taskList);

        String taskTypeString = taskString.split(" | ", 2)[0];
        TaskType taskType = TaskType.valueOf(taskTypeString);

        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = TodoTask.deserialize(taskString);
            break;
        case EVENT:
            newTask = EventTask.deserialize(taskString);
            break;
        case DEADLINE:
            newTask = DeadlineTask.deserialize(taskString);
            break;
        default:
            newTask = null;
        }

        newTaskList.add(newTask);
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
