package duke.ui;

import duke.exceptions.TaskNumberNotExistException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Handles the generation of all responses to be displayed to the app's users.
 */
public class Ui {

    /**
     * Returns the message to be shown right when the application is started.
     *
     * @return A <code>String</code> to respond to the starting up of the application.
     */
    public static String getWelcomeResponse() {
        return "Hello! I'm Duke!" + "\n" + "What can I do for you?";
    }

    /**
     * Returns the message to be shown when the app is shutting down.
     * That is, when the ByeCommand is executed.
     *
     * @return A <code>String</code> to respond to the shutting down of the application.
     */
    public static String getByeResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Computes and returns the message to be shown when the tasks in the to-do list
     * are to be displayed.
     *
     * @param tasks A <code>TaskList</code> object, representing a collection of <Task>Task</Task>
     *              objects to be displayed.
     */
    public static String getListResponse(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "You have no tasks in your list yet :)";
        } else {
            return "Here are the task(s) in your list:" + "\n" + tasks.getTaskListAsString();
        }
    }

    /**
     * Computes and returns the message to be shown when a particular task in the
     * to-do list is to be marked as done.
     *
     * @param doneTask The <code>Task</code> marked as done if any, else null.
     * @param index    Index of the <code>Task</code> (if any) that was marked as done.
     * @return A <code>String</code> to respond to the marking of a <code>Task</code> as done.
     */
    public static String getDoneResponse(Task doneTask, int index) {
        try {
            if (null == doneTask) {
                throw new TaskNumberNotExistException(index);
            }
        } catch (TaskNumberNotExistException e) {
            return e.getMessage();
        }

        return "Nice! I've marked this task as done:" + "\n" + doneTask.getStatusString();
    }

    /**
     * Computes and returns the message to be shown when a particular task is to be
     * deleted from the to-do list.
     *
     * @param deletedTask The deleted <code>Task</code> if any, else null.
     * @param index       Index of the <code>Task</code> (if any) that was deleted.
     * @return A <code>String</code> to respond to the deletion of a <code>Task</code>.
     */
    public static String getDeleteResponse(Task deletedTask, int index) {
        try {
            if (null == deletedTask) {
                throw new TaskNumberNotExistException(index);
            }
        } catch (TaskNumberNotExistException e) {
            return e.getMessage();
        }

        return "Noted. I've removed this task:" + "\n" + deletedTask.getStatusString();
    }

    /**
     * Computes and returns the message to be shown when the to-do list is searched for tasks
     * matching certain input keywords. That is, when the FindCommand is executed.
     *
     * @param tasks   A <code>TaskList</code> object containing only task(s) with descriptions
     *                that match the keyword(s) used for the search.
     * @param keyword Keyword(s) used for the search
     */
    public static String getFindResponse(TaskList tasks, String keyword) {
        if (tasks.getSize() == 0) {
            return "There are no tasks matching the '" + keyword + "' in your list :O";
        } else {
            return "Here are the matching tasks in your list:" + "\n" + tasks.getTaskListAsString();
        }
    }

    /**
     * Computes and returns the message to be shown a new task is added to the to-do list.
     * That is, when the AddToDoCommand, the AddDeadlineCommand or the AddEventCommand is executed.
     *
     * @param tasks   A <code>TaskList</code> object to which a new <code>Task</code>
     *                object was added.
     * @param newTask The added <code>Task</code> object.
     * @return A <code>String</code> to respond to the adding of a new <code>Task</code>.
     */
    public static String getAddTaskResponse(TaskList tasks, Task newTask) {
        return "Got it. I've added this task:" + "\n"
                + newTask.getStatusString() + "\n"
                + "Now you have " + tasks.getSize() + " task(s) in the list.";
    }
}
