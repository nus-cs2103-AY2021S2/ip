package duke.duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * A chat bot manager for handling task list operations.
 */
public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final TaskList list;

    /**
     * Creates a {@code Duke} object with an empty task list.
     */
    public Duke() {
        this.list = new TaskList();

    }

    /**
     * Creates a {@code Duke} object with a task list.
     * @param list Task list.
     */
    public Duke(TaskList list) {
        this.list = list;
    }

    /**
     * Adds a new task to list of tasks.
     * @param message Task description.
     * @param type Identifier of task type.
     * @param date Time of task.
     */
    public String addTask(String message, String type, String date) {
        Task task = null;
        switch (type) {
        case "todo":
            task = new Todo(message);
            break;
        case "deadline":
            task = new Deadline(message, date);
            break;
        case "event":
            task = new Event(message, date);
            break;
        default:
            System.out.println("No task added");
        }

        list.addItem(task);
        return Ui.showAddTaskMessage(task, list.getLst());
    }

    /**
     * Removes a task from list of tasks.
     * @param id Index of task to be removed.
     */
    public String removeTask(int id) {
        int n = id - 1;
        Task task = list.getLst().get(n);
        list.removeItem(n);
        return Ui.showRemoveTaskMessage(task, list.getLst());
    }

    /**
     * Marks a task as done.
     * @param id index of task to be marked as done
     */
    public String markAsDone(int id) {
        int n = id - 1;
        list.doneTask(n);
        return Ui.showDoneTaskMessage(list.getLst(), n);

    }

    /**
     * Shows user list of current tasks.
     */
    public String showTasks() {
        return Ui.showTasksToUser(list.getLst());
    }

    /**
     * Returns task list.
     * @return Task list
     */
    public TaskList getList() {
        return list;
    }

    /**
     * Returns logo of chat bot.
     * @return Logo of chat bot
     */
    public String getLogo() {
        return LOGO;
    }

    /**
     * Returns number of tasks in task list.
     * @return Number of tasks in task list.
     */
    public int getNumberOfTasks() {
        return list.size();
    }

    /**
     * Returns String of tasks containing a keyword.
     * @param keyword keyword to be searched.
     * @return String of tasks containing a keyword.
     */
    public String showTasksContainingKeyword(String keyword) {
        return Ui.showTasksToUser(list.tasksContainingKeyword(keyword));
    }

}
