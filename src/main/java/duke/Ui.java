package duke;

import java.time.LocalDate;

/**
 * The Ui class is a collection of print statements
 * that guides the user's interaction.
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Ui {
    private static final String EMPTY_STRING = "";

    /**
     * Prints the introductory statement for the program.
     */
    public void printIntro() {
        String logo = "  __  __  ___  ___   ___   ___   _____ __  __ \n"
                + " |  \\/  |/ _ \\|   \\ / _ \\ / __| |_   _|  \\/  |\n"
                + " | |\\/| | (_) | |) | (_) | (__    | | | |\\/| |\n"
                + " |_|  |_|\\___/|___/ \\___/ \\___|   |_| |_|  |_|\n"
                + "                                              \n";
        String line = "---------------------------------------\n";
        String line2 = "It is I, MODOC_TM... \n(Mechanized Organism Designed Only for Computing and Tools.Task "
                + "Management) \n";
        String line3 = "Feed me the commands I so desire...\n";
        String line4 = "---------------------------------------";

        System.out.println(line + logo + line2 + line3 + line4);
    }

    /**
     * Returns goodbye message
     *
     * @return This is a goodbye message
     */
    public String printBye() {
        String bye = "Bye. MODOC_TM Shutting Down...";
        return bye;
    }

    /**
     * Returns list in the provided TaskList.
     *
     * @param taskList This is the provided TaskList
     * @return This is the string version of TaskList
     */
    public String printList(TaskList taskList) {
        String l1 = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            l1 += String.valueOf(i + 1) + "." + taskList.getSingleTask(i).toString()
                    + "\n";
        }
        return l1;
    }

    /**
     * Returns the done message after a task has been completed.
     *
     * @param task This is the task to be marked completed
     * @return This is the done message
     */
    public String printDone(Task task) {
        String l1 = "Nice! I've marked this task as done:\n";
        String l2 = "[" + task.getStatusIcon() + "] " + task.getName().trim();
        return l1 + l2;
    }

    /**
     * Prints a task that is added and shows how many remaining
     * tasks are in the list.
     *
     * @param task This is the task to be added
     * @param remTask This is the number of remaining tasks
     */
    public String printTask(Task task, int remTask) {
        String l1 = "Got it. I've added this task:\n";
        String l2 = task.toString() + "\n";
        String l3 = "Now you have " + remTask + " tasks in the list";

        return l1 + l2 + l3;
    }

    /**
     * Prints a task that has been deleted and show how many
     * remaining tasks are in the list.
     *
     * @param task This is the task to be deleted
     * @param remTask This is number of remaining tasks
     * @return Returns a task deletion message
     */
    public String printDelete(Task task, int remTask) {
        String l1 = "Noted. I've removed this task:\n";
        String l2 = task.toString() + "\n";
        String l3 = "Now you have " + remTask + " tasks in the list\n";

        return l1 + l2 + l3;
    }

    /**
     * returns an unknown command message.
     *
     * @return This is the unknown command message
     */
    public String printUnknownCommand() {
        return "Hol'up, I don't know what that means :-(";
    }

    /**
     * Prints the tasks given in the TaskList.
     *
     * @param taskList This is the TaskList to be printed
     * @return This is the message
     */
    public String printMatchingTask(TaskList taskList) {
        String l1 = "Here are the matching tasks in your list:\n";
        String l2 = EMPTY_STRING;

        for (int i = 0; i < taskList.getSize(); i++) {
            l2 += String.valueOf(i + 1) + "." + taskList.getSingleTask(i).toString()
                    + "\n";
        }

        if (l2 == EMPTY_STRING) {
            return "Sorry! Nothing found!";
        } else {
            return l1 + l2;
        }
    }

    /**
     * Prints the snooze complete message with updated date.
     *
     * @param newDate This is the snooze new date
     * @return This is the snooze complete message
     */
    public String printDoneSnooze(String newDate) {
        String l1 = "Deadline has been snoozed to "
                + newDate;
        return l1;
    }

    /**
     * Prints a message of all possible commands
     *
     * @return This is a text based help page
     */
    public String getHelpMessage() {
        String commands = "To get you started, here are some commands:\n\n"
                + "\"list\": displays exisiting commands.\n"
                + "\"done\" <taskNumber>: marks a task complete.\n "
                + "\"delete\" <taskNumber>: deletes a task from your list.\n"
                + "\"find\" <anyString>: finds a task that matches the string.\n"
                + "\"todo\" <task> : adds a todo task.\n"
                + "\"deadline\" <task> /by <date YYYY-MM-DD>: adds a deadline.\n"
                + "\"event\" <task> /at <location>: adds an event.\n"
                + "\"bye\": say bye to save existing tasks and exit\n"
                + "\nType \"help\" to bring up this page again. :)";
        return commands;
    }

    /**
     * Returns the introduction message
     *
     * @return This is the introduction message
     */
    public String getIntroMessage() {
        String welcome = "Greetings mortal...\n"
                + "Welcome to MODOC_TM\n\n"
                + "How can I be of service?";

        return welcome;
    }

    /**
     * This method is called when there are not tasks to be loaded
     * from storage.
     */
    public void showLoadingError() {
        System.out.println("Existing tasks not found.");
    }

}
