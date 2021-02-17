package duke;

import java.util.Scanner;

import duke.tasks.*;

/**
 * Ui class to manage user input and Duke output
 */
public class Ui {
    private static final String SPACE = "     ";
    private static final String DIVIDER = "    -------------------------------------------->>>>\n";
    private final Scanner sc;

    /**
     * Ui constructor
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     *  welcome message
     */
    public String printWelcomeText() {
        return DIVIDER + SPACE + "Konichiwa~ Watashi wa Duke desu! \n" + SPACE
                + SPACE + "What can I do for you today?\n"
                + DIVIDER;
    }

    public String printHelpMessage() {
        return DIVIDER + SPACE + "How to add tasks to the list:\n"
                + SPACE + "For Todo, type \n"
                + SPACE + "'todo <task name>'\n"
                + SPACE + "For Deadline, type \n"
                + SPACE + "'deadline <task name> /by: <YYYY-MM-DD>'\n"
                + SPACE + "For Event, type\n"
                + SPACE + "'event <task name> /at: <YYYY-MM-DD>'\n"
                + SPACE + "\n"
                + SPACE + "How to edit tasks in list:\n"
                + SPACE + "Type 'edit <task number> /name <new name>'\n"
                + SPACE + "and I will change the name of that task\n"
                + SPACE + "Type 'edit <task number> /time <new time>'\n"
                + SPACE + "and I will change the time of that task\n"
                + SPACE + "when applicable\n"
                + SPACE + "\n"
                + SPACE + "Type 'done <task number>'\n"
                + SPACE + "and I will mark it as done!\n"
                + SPACE + "Type 'find <keyword>'\n"
                + SPACE + "and I will return all search results\n"
                + SPACE + "Type 'list'\n"
                + SPACE + "and I will return list of tasks\n"
                + SPACE + "Type 'delete <task number>'\n"
                + SPACE + "and I will delete that task\n"
                + SPACE + "Type 'bye' to exit"
                + DIVIDER;
    }

    /**
     * Prints exit message
     */
    public String printGoodbye() {
        return DIVIDER + SPACE + "Sayonara, matta ne~ \n" + DIVIDER;
    }

    private String printOnlyList(TaskList list) {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result = result + SPACE + (i + 1) + ". " + list.getTask(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Prints list of tasks in duke output format
     *
     * @param list List of tasks
     */
    public String printList(TaskList list) {
        String resultString = DIVIDER;

        if (list.size() == 0) {
            resultString = resultString + SPACE + "Ara, the list is empty right now!\n";
        } else {
            resultString = resultString + SPACE + "Hai~ Current tasks are: \n" + printOnlyList(list);

        }

        resultString = resultString + DIVIDER;
        return resultString;
    }

    /**
     * Prints message when a task is marked as done
     *
     * @param task Given task that is marked as done
     */
    public String printCompletedMsg(Task task) {
        return DIVIDER + SPACE + "Otsukare! I've marked this task as done: \n" + SPACE + "  "
                + task.toString() + "\n" + DIVIDER;
    }

    /**
     * Prints message when a task is deleted from list
     *
     * @param task Task to be deleted
     * @param size Number of tasks left in list
     */
    public String printDeleteMsg(Task task, int size) {
        return DIVIDER + SPACE + "Yayyyy! I've removed the task: \n" + SPACE + "  " + task.toString() + "\n"
                + SPACE + "Now you have " + size + " task(s) left!\n" + DIVIDER;
    }

    /**
     * Prints message when a task is added into list
     *
     * @param task Task that is added
     * @param size Number of tasks now on list
     */
    public String printTask(Task task, int size) {
        return DIVIDER + SPACE + "Ayyyyy I've added the task: \n" + SPACE + "  " + task.toString() + "\n"
                + SPACE + "Now you have " + size + " task(s) in the list.\n" + DIVIDER;
    }

    /**
     * Prints tasks in given search result list
     *
     * @param taskList Given list of tasks
     */
    public String printSearchResults(TaskList taskList) {
        String resultString = DIVIDER;
        if (taskList.size() == 0) {
            resultString = resultString + SPACE + "There are no matching results D:\n" + DIVIDER;
        } else {
            resultString = resultString + SPACE + "Hai~ The search results are: \n"
                    + printOnlyList(taskList) + DIVIDER;
        }
        return resultString;

    }
    public String printNameEdit(Task task, int index) {
        String resultString = DIVIDER + SPACE + "Done! Name of task number "
                + index + " has been changed to " + task.getName() + ".\n" + DIVIDER;
        return resultString;
    }

    public String printTimeEdit(Task task, int index) {
        if (task instanceof Todo) {
            String resultString = DIVIDER + SPACE + "Sorry! " + task.getTime() + " \n"
                    + SPACE + "Try again pwease?\n"
                    + SPACE + "Type 'help' for help! ;)"
                    + DIVIDER;
            return resultString;
        } else if (task instanceof Deadline || task instanceof Event) {
            String resultString = DIVIDER + SPACE + "Done! Time of task number "
                    + index + " has been changed to " + task.getTime() + ".\n" + DIVIDER;
            return resultString;
        } else {
            return printUnknownInputError();
        }
    }

    /**
     * Prints error when unable to parse user input into known command
     */
    public String printUnknownInputError() {
        return DIVIDER + SPACE + "Sorry! I do not understand what you just said.\n"
                + SPACE + "Try again pwease?\n"
                + SPACE + "Type 'help' for help! ;)"
                + DIVIDER;
    }

    public String printIndexOutOfBoundError() {
        return DIVIDER + SPACE + "Sorry! The task number seems to be incorrect!\n"
                + SPACE + "Try again pwease?\n"
                + SPACE + "Type 'help' for help! ;)"
                + DIVIDER;
    }



    /**
     * Gets next line of user input
     *
     * @return User input
     */
    public String getNextCommand() {
        return sc.nextLine().strip();
    }

    /**
     * Checks if there is user input
     *
     * @return True if user typed a command
     */
    public boolean hasNextCommand() {
        return sc.hasNext();
    }


}
