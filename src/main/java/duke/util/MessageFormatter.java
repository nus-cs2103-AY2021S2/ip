package duke.util;

import java.util.ListIterator;

import duke.task.Task;

/**
 * Class containing methods that formats Duke's messages to the user.
 */
public class MessageFormatter {
    /**
     * Formats Duke's message to the user after adding a task.
     *
     * @param taskAdded The task added by Duke.
     * @param tasks The list of tasks.
     * @return A String that lets the user know which task was added, as well as the
     *          current size of the list of tasks.
     */
    public String formatAddMsg(Task taskAdded, TaskList tasks) {
        return ("Got it. I've added this task:\n" + taskAdded
                + "\nYou have "
                + tasks.getSize()
                + (tasks.getSize() == 1 ? " task" : " tasks")
                + " in your list");
    }

    /**
     * Formats Duke's message to the user after removing a task.
     *
     * @param taskRemoved The task removed by Duke.
     * @param tasks The list of tasks.
     * @return A String that lets the user know which task was deleted, as well as the
     *          current size of the list of tasks.
     */
    public String formatRemoveMsg(Task taskRemoved, TaskList tasks) {
        return ("I've removed this task:\n"
                + taskRemoved
                + "\nYou have "
                + tasks.getSize()
                + (tasks.getSize() == 1 ? " task" : " tasks")
                + " in your list");
    }

    /**
     * Formats Duke's message to the user after setting a task as completed.
     *
     * @param taskDone The task that was set as completed.
     * @return A String that lets the user know which task was set as completed.
     */
    public String formatDoneMsg(Task taskDone) {
        return ("Nice! I have marked this task as done:\n"
                + taskDone);
    }

    /**
     * Formats Duke's message to the user after setting a task as not complete.
     *
     * @param taskUndone The task that was set as not complete.
     * @return A String that lets the user know which task was set as not complete.
     */
    public String formatUndoDone(Task taskUndone) {
        return ("I have marked this task as not done:\n"
                + taskUndone);
    }

    /**
     * Formats Duke's message to the user after searching for tasks that matches
     * the keyword provided by the user.
     *
     * @param matchingTasks A list of tasks that matches the keyword provided by the user.
     * @return A String that displays all the tasks that matches the keyword provided.
     */
    public String formatFindMsg(TaskList matchingTasks) {
        if (matchingTasks.getSize() == 0) {
            return "There are no tasks with such keyword!";
        }
        ListIterator<Task> iterator = matchingTasks.getIterator();
        String msg = "I have found tasks with the given keyword:\n";
        while (iterator.hasNext()) {
            Task task = iterator.next();
            msg += (task + (iterator.hasNext() ? "\n" : ""));
        }
        return msg;
    }

    /**
     * Formats Duke's message to the user after retrieving all user's tasks.
     *
     * @param allTasks All tasks of the user.
     * @return A String that displays all tasks of the user.
     */
    public String formatListMsg(TaskList allTasks) {
        if (allTasks.getSize() == 0) {
            return "There are no tasks to display!";
        }
        ListIterator<Task> iterator = allTasks.getIterator();
        String msg = "Your tasks:\n";
        int count = 1;
        while (iterator.hasNext()) {
            Task task = iterator.next();
            msg += (count + "."
                    + task
                    + (iterator.hasNext() ? "\n" : ""));
            count++;
        }
        return msg;
    }

    /**
     * Formats Duke's message to the user after undoing an Add command.
     *
     * @param removedTask The task that was removed.
     * @param tasks The list of tasks.
     * @return A String that lets the user know which task was removed.
     */
    public String formatUndoAdd(Task removedTask, TaskList tasks) {
        return ("ADD undone, the following task is removed:\n"
                + removedTask
                + "\nYou have "
                + tasks.getSize()
                + (tasks.getSize() == 1 ? " task" : " tasks")
                + " in your list");
    }

    /**
     * Formats Duke's message to the user after undoing a Delete command.
     *
     * @param addedTask The task that was added back into the list.
     * @param tasks The list of tasks.
     * @return A String that lets the user know which task was added back into the list.
     */
    public String formatUndoDelete(Task addedTask, TaskList tasks) {
        return ("DELETE undone, the following task is added:\n"
                + addedTask
                + "\nYou have "
                + tasks.getSize()
                + (tasks.getSize() == 1 ? " task" : " tasks")
                + " in your list");
    }
}
