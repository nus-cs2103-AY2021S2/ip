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
     * Prints goodbye message
     */
    public void printBye() {
        System.out.println("\n---------------------------------------");
        System.out.println("Bye. MODOC_TM Shutting Down...");
        System.out.println("---------------------------------------");
    }

    /**
     * Prints lists in the provided TaskList.
     *
     * @param taskList This is the provided TaskList
     */
    public void printList(TaskList taskList) {
        System.out.println("\n---------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + taskList.getSingleTask(i).toString());
        }
        System.out.println("---------------------------------------");
    }

    /**
     * Prints the done message after a task has been completed.
     *
     * @param task This is the task to be marked completed
     */
    public void printDone(Task task) {
        System.out.println("\n---------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getName().trim());
        System.out.println("---------------------------------------");
    }

    /**
     * Prints a task that is added and shows how many remaining
     * tasks are in the list.
     *
     * @param task This is the task to be added
     * @param remTask This is the number of remaining tasks
     */
    public void printTask(Task task, int remTask) {
        System.out.println("\n---------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + remTask + " tasks in the list");
        System.out.println("---------------------------------------");
    }

    /**
     * Prints a task that has been deleted and show how many
     * remaining tasks are in the list.
     *
     * @param task This is the task to be deleted
     * @param remTask This is number of remaining tasks
     */
    public void printDelete(Task task, int remTask) {
        System.out.println("\n---------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + remTask + " tasks in the list");
        System.out.println("---------------------------------------");
    }

    /**
     * Prints an unknown command message.
     */
    public void printUnknownCommand() {
        System.out.println("\n---------------------------------------");
        System.out.println("Hol'up, I don't know what that means :-(");
        System.out.println("---------------------------------------");
    }

    /**
     * Prints the tasks given in the TaskList.
     *
     * @param taskList This is the TaskList to be printed
     */
    public void printMatchingTask(TaskList taskList) {
        System.out.println("\n---------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + taskList.getSingleTask(i).toString());
        }
        System.out.println("---------------------------------------");
    }

    /**
     * This method is called when there are not tasks to be loaded
     * from storage.
     */
    public void showLoadingError() {
        System.out.println("Existing tasks not found.");
    }

}
