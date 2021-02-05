import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected List<Task> taskList;

    /**
     * Creates a new instance of <code>TaskList</code>.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns this task list.
     *
     * @param taskNo Task number.
     * @return Task for the given task number.
     */
    public Task getTask(int taskNo) {
        return this.taskList.get(taskNo - 1);
    }

    /**
     * Adds task to task list.
     *
     * @param taskType Type of task.
     * @param description Description of task.
     * @param isReadingFile True if a file is being read, false if a file is not being read.
     * @throws DukeException If description is not given in the correct format.
     */
    public void addTask(TaskType taskType, String description, boolean isReadingFile) throws DukeException, IOException {
        Task newTask = new Task(description);
        if (taskType == TaskType.TODO) {
            newTask = new ToDoTask(description);
        } else if (taskType == TaskType.DEADLINE) {
            String[] descriptionArr = description.split(" /by ");
            if (descriptionArr.length == 1) {
                throw new DukeException("Your description is not given in the correct format!");
            }
            newTask = new DeadlineTask(descriptionArr[0], descriptionArr[1]);
        } else if (taskType == TaskType.EVENT) {
            String[] descriptionArr = description.split(" /at ");
            if (descriptionArr.length == 1) {
                throw new DukeException("Your description is not given in the correct format!");
            }
            newTask = new EventTask(descriptionArr[0], descriptionArr[1]);
        }
        this.taskList.add(newTask);
        if (!isReadingFile) {
            FileManager.appendToFile("data/duke.txt", newTask.toString());
            System.out.println("Got it. I've added this task: \n"
                    + "  " + newTask + "\n"
                    + "Now you have " + this.taskList.size() + " tasks in the list.");
        }
    }

    /**
     * Deletes task from task list.
     *
     * @param taskNo Task number.
     * @throws DukeException If task number does not exist.
     */
    public void deleteTask(int taskNo) throws DukeException, IOException {
        if (taskNo > this.taskList.size()) {
            throw new DukeException("☹ OOPS!!! This task number does not exist.");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + this.taskList.get(taskNo - 1));
        this.taskList.remove(taskNo - 1);
        String taskOrTasks = (this.taskList.size() <= 1)
                ? " task"
                : " tasks";
        System.out.println("Now you have " + this.taskList.size() + taskOrTasks + " in the list.");
        FileManager.deleteLine("data/duke.txt", taskNo);

    }

    /**
     * Print tasks in task list.
     */
    public void printTaskList() {
        if (this.taskList.size() == 0) {
            System.out.println("There are currently no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= this.taskList.size(); i++) {
                System.out.println(i + "." + this.taskList.get(i - 1));
            }
        }
    }

}
