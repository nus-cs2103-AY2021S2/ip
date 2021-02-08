package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Handles all messages displayed to the app's users. Note that this class DOES NOT handle
 * any logic regarding the creation, adding, modifying or deletion of tasks.
 */
public class Ui {

    /**
     * Prints message for when the app starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke.Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints message for when the app terminates.
     */
    public void handleBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message for when users list the existing tasks.
     *
     * @param tasks A <code>TaskList</code> object, representing a collection of <Task>Task</Task>
     *              objects to be printed.
     */
    public void handleList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no tasks in your list yet :)");
        } else {
            System.out.println("Here are the task(s) in your list:");
            tasks.printTasks();
        }
    }

    /**
     * Prints message for when users mark a particular tasks as done.
     *
     * @param doneTask The <code>Task</code> object marked as done.
     */
    public void handleDone(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask.getStatusString());
    }

    /**
     * Prints message for when users delete a partciular task from the to-do list.
     *
     * @param deletedTask The deleted <code>Task</code> object.
     */
    public void handleDelete(Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.getStatusString());
    }

    /**
     * Prints message for when users add a new task to the to-do list.
     *
     * @param tasks   A <code>TaskList</code> object to which a new <code>Task</code>
     *                object was added.
     * @param newTask The added <code>Task</code> object.
     */
    public void handleAddTask(TaskList tasks, Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getStatusString());
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
    }
}
