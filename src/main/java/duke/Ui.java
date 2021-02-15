package duke;

/**
 * The Ui class is a collection of print statements
 * that guides the user's interaction.
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Ui {

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
        for (int i = 0; i < taskList.getSize(); i++) {
            l1 += String.valueOf(i + 1) + "." + taskList.getSingleTask(i).toString()
                    + "\n";
        }
        return l1;
    }

    /**
     * This method is called when there are not tasks to be loaded
     * from storage.
     */
    public void showLoadingError() {
        System.out.println("Existing tasks not found.");
    }

}
