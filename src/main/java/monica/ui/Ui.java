package monica.ui;

import monica.task.Task;
import monica.task.TaskList;

/**
 * Represents the responses to the user.
 */
public class Ui {

    /**
     * Displays the welcome message.
     * @return string
     */
    public static String showWelcome() {
        return "Hey! I'm Monica, your best friend.\nWhat can I do for you?\n";
    }

    /**
     * Displays the number of tasks in the task list.
     */
    public String showNumberOfTasks(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " task"
                + (tasks.getSize() > 1 ? "s in the list." : " in the list.\n");
    }

    /**
     * Displays a message after a task is added to the list.
     * @param task Task to be added.
     * @param tasks Task List.
     */
    public String showAdded(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n  " + task
                + "\n" + showNumberOfTasks(tasks);
    }

    /**
     * Displays a message after a task is deleted from the list.
     * @param task Task to be deleted.
     * @param tasks Task List.
     */
    public String showDeleted(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n  " + task
                + "\n" + showNumberOfTasks(tasks);
    }

    /**
     * Displays a message after a task is done.
     * @param id Task ID.
     * @param tasks Task List.
     */
    public String showDone(int id, TaskList tasks) {
        return "Nice! I've marked this task as done:\n" + tasks.getTask(id) + "\n";
    }

    /**
     * Displays all the tasks when the user requests to view task list.
     * @param tasks Task List.
     */
    public String showList(TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        if (tasks.getSize() == 0) {
            output.append("There is no task in the list.");
        } else {
            for (int i = 1; i <= tasks.getSize(); i++) {
                output.append(i).append(". ").append(tasks.getTask(i)).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Displays all the tasks that contain the keyword.
     * @param keyword Keyword to be found.
     * @param tasks Task List used to find the matching tasks.
     */
    public String showFound(String keyword, TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 1;

        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                output.append(count).append(". ").append(tasks.getTask(i)).append("\n");
                count++;
            }
        }

        if (count == 1) {
            output.append("There is no matching task in the list. You can try another keyword.");
        }

        return output.toString();
    }


    /**
     * Displays error messages when an exception is caught.
     * @param error Error being caught.
     */
    public String showError(Exception error) {
        return error.toString();
    }

    /**
     * Displays farewell message.
     */
    public static String showFarewell() {
        final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";
        final String COUNT_DOWN = "I'm leaving in 3 seconds...\n";
        return BYE_MESSAGE + COUNT_DOWN;
    }

    /**
     * Displays all correct command formats to users in alphabetical order.
     */
    public static String showHelp() {
        final String GUIDANCE_MESSAGE = "You can enter any command from the below list:\n";
        final String BYE_COMMAND = "bye\n";
        final String DEADLINE_COMMAND = "deadline taskName /by yyyy-MM-dd HHmm\n";
        final String DELETE_COMMAND = "delete taskIndex\n";
        final String DONE_COMMAND  = "done taskIndex\n";
        final String EVENT_COMMAND = "event taskName /at yyyy-MM-dd HHmm\n";
        final String FIND_COMMAND = "find\n";
        final String HELP_COMMAND = "help\n";
        final String LIST_COMMAND = "list\n";
        final String TODO_COMMAND = "todo taskName\n";

        return GUIDANCE_MESSAGE + BYE_COMMAND + DEADLINE_COMMAND + DELETE_COMMAND
                + DONE_COMMAND + EVENT_COMMAND + FIND_COMMAND
                + HELP_COMMAND + LIST_COMMAND + TODO_COMMAND;
    }
}
