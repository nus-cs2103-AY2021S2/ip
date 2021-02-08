package duke;


/**
 *  The Ui class contains methods to handle text represention to users via the CLI.
 */
public class Ui {

    public static final String HORIZONTAL_LINE = "________________________________________________";
    public static final String FILE_PATH = "data.txt";
    private static final String greeting =" Hello! I'm duke.\n What can I do for you?\n";
    private static String farewell = " Bye. Hope to see you again soon!";


    /**
     * Initialize and prints greeting message when a user runs programme.
     */

    public static void initGreating() {
        System.out.println(HORIZONTAL_LINE + "\n" + greeting + HORIZONTAL_LINE + "\n");
    }


    /**
     * Prints error message when a user encounters exceptions.
     */

    public static void showErrorMessage(String error){
        System.out.println(HORIZONTAL_LINE + "\n"  + error + "\n" +HORIZONTAL_LINE);
    }


    /**
     * Prints success message when a user successfully adds a task.
     */

    public static void showSuccessfulAddedMessage(int numTask, Task task){
        System.out.println(HORIZONTAL_LINE + "\n" + " Got it! I've added this new Task!");
        System.out.println("  " + task.toString());
        System.out.println(" Now you have " + numTask + " tasks in your TaskList.");
        System.out.println(HORIZONTAL_LINE);
    }


    /**
     * Prints TaskList as a string
     */

    public static void showListContent(TaskList taskList){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Here are your remaining tasks!\n");
        if(taskList.getSize() ==0){
            System.out.println(" The list is empty!\n Please add tasks into the list :))");
        }
        System.out.println(taskList.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints message when a user successful marks a task as done.
     */

    public static void markTaskAsDone(Task task){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Nice! I've marked this task as done");
        System.out.println(" " + task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints message when a user successful deletes a task.
     */
    public static void showTaskAsDeleted(Task task){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Noted! I have removed this task from the list.");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints message when a user exits programme.
     */

    public static void showByeMessage(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(farewell);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints default message when a user inputs invalid arguments.
     * Prints list of valid arguments
     */

    public static void showDefaultStatement(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Please enter a valid command!");
        System.out.println(" Valid commands include:");
        System.out.println(" todo\n event\n deadline\n list\n done\n delete\n bye");
        System.out.println(HORIZONTAL_LINE);
    }

}
