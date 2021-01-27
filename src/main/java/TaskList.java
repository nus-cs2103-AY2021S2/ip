import java.util.ArrayList;
import java.util.List;

/**
 * Class that encapsulate, control, and evaluate a list of task.
 */
public class TaskList {
    static final String LINE_AFTER_COMMAND = "____________________________________________________________";

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
    public void iterateList() {
        if (this.tasks.size() == 0) {
            Ui.showMessage("There are no task in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Ui.showMessageInALine(String.valueOf(i + 1) + "." + this.tasks.get(i));
        }
        Ui.printLine();
    }

    /**
     * Set a task to be done
     * @param input The String representation of the index of the task
     * @throws DukeException Index out of bound
     */
    public void finishATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.set(index, this.tasks.get(index).finishTask());
            Ui.finishTaskMessage(this.tasks.get(index).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of done cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of done exceed the tasks' list.");
        }
    }

    /**
     * Delete a task
     * @param input The String representation of the index of the task
     * @throws DukeException Index out of bound
     */
    public void deleteATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task deleted = this.tasks.get(index);
            this.tasks.remove(index);
            Ui.deleteTaskMessage(deleted.toString(), this.tasks.size());
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of delete cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of delete exceed the tasks' list.");
        }
    }

    /**
     * Add task to the current TaskList
     * @param task Task that is going to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        Ui.addTaskMessage(task.toString(), this.tasks.size());
    }

    /**
     * Get List of Task
     * @return List of Task
     */
    public List<Task> getTasks() {
        return this.tasks;
    }


}

