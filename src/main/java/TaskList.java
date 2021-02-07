import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class that encapsulate, control, and evaluate a list of task.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor of TaskList object
     */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor of TaskList object
     * @param tasks List of Task
     */
    TaskList(List<Task> tasks) {
        List<Task> newTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            newTasks.add(task);
        }
        this.tasks = newTasks;
    }

    /**
     * Iterate Task object in list and ask Ui to print them
     */
    public String iterateList() {
        if (this.tasks.size() == 0) {
            return Ui.showMessage("There are no task in your list");
        }
        String output = Ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            output += Ui.showMessage(String.valueOf(i + 1) + "." + this.tasks.get(i));
        }
        return output;
    }

    /**
     * Set a task to be done and ask Ui to print the completion
     * @param input The String representation of the index of the task
     * @throws DukeException Index out of bound
     */
    public String finishATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.set(index, this.tasks.get(index).finishTask());
            return Ui.finishTaskMessage(this.tasks.get(index).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of done cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of done exceed the tasks' list.");
        }
    }

    /**
     * Delete a task and ask Ui to print the completion
     * @param input The String representation of the index of the task
     * @throws DukeException Index out of bound
     */
    public String deleteATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task deleted = this.tasks.get(index);
            this.tasks.remove(index);
            return Ui.deleteTaskMessage(deleted.toString(), this.tasks.size());
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of delete cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of delete exceed the tasks' list.");
        }
    }

    /**
     * Add task to the current TaskList and ask Ui to print the completion
     * @param task Task that is going to be added
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return Ui.addTaskMessage(task.toString(), this.tasks.size());
    }

    /**
     * Finding Tasks that match the keyword and ask Ui to print them
     * @param keyword Keyword to find in list of Tasks
     */
    public String findTasks(String keyword) {
        List<Task> temp = new ArrayList<>();
        Predicate<Task> findKeywordFromTask = task -> task.toString().contains(keyword);
        for (Task task: this.tasks) {
            if (findKeywordFromTask.test(task)) {
                temp.add(task);
            }
        }
        if (temp.size() == 0) {
            return Ui.showMessage("No matching task");
        }
        String output = Ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < temp.size(); i++) {
            output += Ui.showMessage(String.valueOf(i + 1) + "." + temp.get(i));
        }
        return output;

    }

    /**
     * Get List of Task
     * @return List of Task
     */
    public List<Task> getTasks() {
        return this.tasks;
    }


}

