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

    public Duke() {
        this.list = new TaskList();

    }

    public Duke(TaskList list) {
        this.list = list;
    }

    /**
     * Adds a new task to list of tasks.
     * @param message task description
     * @param type identifier of task type
     * @param date time of task
     */
    public void addTask(String message, String type, String date) {
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
        }

        list.addItem(task);
        Ui.showAddTaskMessage(task, list.getLst());
    }

    /**
     * Removes a task from list of tasks.
     * @param id index of task to be removed
     */
    public void removeTask(String id) {
        int n = Integer.parseInt(id) - 1;
        Task task = list.getLst().get(n);
        list.removeItem(n);
        Ui.showRemoveTaskMessage(task, list.getLst());
    }

    /**
     * Marks a task as done.
     * @param id index of task to be marked as done
     */
    public void markAsDone(String id) {
        int n = Integer.parseInt(id) - 1;
        list.doneTask(n);
        Ui.showDoneTaskMessage(list.getLst(), n);

    }

    /**
     * Shows user list of current tasks.
     */
    public void showTasks() {
        Ui.showTasksToUser(list.getLst());
    }

    /**
     * Returns task list.
     * @return task list
     */
    public TaskList getList() {
        return list;
    }

    /**
     * Returns logo of chat bot.
     * @return logo of chat bot
     */
    public String getLogo() {
        return LOGO;
    }

    public int getNumberOfTasks() {
        return list.size();
    }

    public void showTasksContainingKeyword(String keyword) {
        Ui.showTasksToUser(list.tasksContainingKeyword(keyword));
    }

}
