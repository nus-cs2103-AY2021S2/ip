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

    public void printIntro() {
        String logo = "  __  __  ___  ___   ___   ___   _____ __  __ \n" +
                " |  \\/  |/ _ \\|   \\ / _ \\ / __| |_   _|  \\/  |\n" +
                " | |\\/| | (_) | |) | (_) | (__    | | | |\\/| |\n" +
                " |_|  |_|\\___/|___/ \\___/ \\___|   |_| |_|  |_|\n" +
                "                                              \n";
        String line = "---------------------------------------\n";
        String line2 = "It is I, MODOC_TM... \n(Mechanized Organism Designed Only for Computing and Tools.Task Management) \n";
        String line3 = "Feed me the commands I so desire...\n";
        String line4 = "---------------------------------------";

        System.out.println(line+logo+line2+line3+line4);
    }

    public void printBye() {
        System.out.println("\n---------------------------------------" );
        System.out.println("Bye. MODOC_TM Shutting Down...");
        System.out.println("---------------------------------------" );
    }

    public void printList(TaskList taskList) {
        System.out.println("\n---------------------------------------" );
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + taskList.getSingleTask(i).toString());
        }
        System.out.println("---------------------------------------" );
    }

    public void printDone(Task task) {
        System.out.println("\n---------------------------------------" );
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.name.trim());
        System.out.println("---------------------------------------" );
    }

    public void printTask(Task task, int remTask) {
        System.out.println("\n---------------------------------------" );
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + remTask + " tasks in the list");
        System.out.println("---------------------------------------" );
    }

    public void printDelete(Task task, int remTask) {
        System.out.println("\n---------------------------------------" );
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + remTask + " tasks in the list");
        System.out.println("---------------------------------------" );
    }

    public void printUnknownCommand() {
        System.out.println("\n---------------------------------------" );
        System.out.println("Hol'up, I don't know what that means :-(");
        System.out.println("---------------------------------------" );
    }

    /**
     * This method is called when there are not tasks to be loaded
     * from storage.
     */
    public void showLoadingError() {
        System.out.println("Existing tasks not found.");
    }

}
