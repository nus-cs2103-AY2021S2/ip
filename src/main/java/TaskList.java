import java.util.List;

/**
 * Represents a list of tasks for a user.
 * A TaskList also handles operations for the tasks in the list.
 */
public class TaskList {

    protected List<Task> tasks;

    /**
     * Creates a TaskList.
     * @param tasks Tasks to be added into the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param taskNumber Task number of the task that is to be deleted.
     */
    public void delete(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Prints out all the tasks in the task list.
     */
    public String list() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + (i + 1) + "." + tasks.get(i).toString();
        }
        return output;
    }

    /**
     * Returns a Task, based on the task number specified by users.
     * @param taskNumber Task number of selected task.
     * @return Task.
     */
    public Task find(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Finds the tasks that contain the keyword input by user.
     * @param keyword Keyword input by user.
     */
    public String findWithKeyword(String keyword) {
        String output = "";
        int numberOfMatches = 0;
        int tracker = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            boolean isPresent = task.toLowerCase().contains(keyword.toLowerCase());
            if (isPresent) {
                if (tracker == 0) {
                    output = Ui.responseToFind() + "\n";
                    tracker++;
                }
                output = output + task + "\n";
                numberOfMatches++;
            }
        }
        if (numberOfMatches == 0) {
            output = Ui.responseToNoMatches();
        }
        return output;
    }

    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

}
