package duke;

import java.util.List;

import duke.task.Task;

/**
 * <code>Ui</code> class handles the interactions with the user.
 */
public class Ui {
    /**
     * Constructor for Ui class.
     */
    public Ui() {
    }

    /**
     * Generates a welcome message when the Duke application starts up.
     *
     * @return Welcome message.
     */
    public String welcome() {
        return "Hey there! I'm DUKE, how may I help you?";
    }

    /**
     * Generates a goodbye message when the
     * user terminates the Duke application.
     *
     * @return Goodbye message.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates the list of existing tasks when user input "list" command.
     *
     * @param list List of existing tasks.
     * @return String message of the list of existing tasks.
     */
    public String printTaskList(List<Task> list) {
        if (list.size() == 0) {
            return "There is currently no task in your list.";
        } else {
            assert list.size() > 0 : "List size must be greater than 0";

            String firstReply = "Here are the tasks in your list:\n";
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                int number = 1 + i;
                stringBuilder.append("     ").append(number).append(". ").append(list.get(i)).append("\n");
            }
            stringBuilder.insert(0, firstReply);
            return stringBuilder.toString();
        }
    }

    /**
     * Prints a validation message when user adds a new Task to the list.
     *
     * @param newTask New Task added by the user.
     * @param list List of existing tasks.
     * @return String message of the task added to the list.
     */
    public String printAddTask(Task newTask, List<Task> list) {
        return "Yes sir! I've added this task:\n"
                + "            " + newTask + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Prints a validation message when user marks a Task as done.
     *
     * @param taskNumber Number of the task that the user marked as done.
     * @param list List of existing tasks.
     * @return String message of the task marked as done.
     */
    public String printDoneTask(int taskNumber, List<Task> list) {
        return "Nice! I've marked this task as done:\n"
                + "        " + list.get(taskNumber);
    }

    /**
     * Prints a validation message when user deletes a task from the list.
     *
     * @param taskNumber Number of the task that the user wants to delete.
     * @param list List of existing tasks.
     * @return String message of the deleted task.
     */
    public String printDeleteTask(int taskNumber, List<Task> list) {
        return "Noted. I've removed this task:\n"
                + "        " + list.get(taskNumber)
                + "\nNow you have " + (list.size() - 1) + " tasks in the list.";
    }

    /**
     * Prints a validation message when user finds a task by searching for a keyword.
     *
     * @param tempList List of tasks containing the keyword.
     * @return String message of the tasks containing the keyword.
     */
    public String printFindKeyword(List<Task> tempList) {
        String firstReply = "Here are the matching tasks in you list:\n";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < tempList.size(); i++) {
            int number = i + 1;
            stringBuilder.append("     ").append(number).append(". ").append(tempList.get(i)).append("\n");
        }
        stringBuilder.append(firstReply);
        return stringBuilder.toString();
    }
}
