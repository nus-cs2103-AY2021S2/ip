package chat;

import java.util.Scanner;

import chat.task.Task;

/**
 * Ui is a class that deals with interactions with the user.
 */
public class Ui {
    
    private static String greetingCat = "(=^. .^=)";
    private static String goodByeCat = "(=^. .^=*)";
    private static String goCat = "(=^. .^=)~~";
    private static String gdJobCat = "\\(=^> <^=)/";
    private static String errorCat = "(=^> <^=)'''";
    
    private static Scanner sc = new Scanner(System.in);

    /**
     * Prints lines that divide each response.
     */
    public void lines() {
        System.out.println("__________________________" +
                "__________________________________");
    }

    /**
     * Prints greeting message from Chat the Cat.
     */
    public void greet() {
        System.out.println(greetingCat);
        System.out.println("Mew! I'm Chat the Cat");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints goodbye message from Chat the Cat.
     */
    public void goodbye() {
        System.out.println("*** Goodbye *** " + goodByeCat);
    }

    /**
     * Prints message from Chat the Cat to tell user that a task has been added successfully to list of tasks.
     * 
     * @param task Task that has been added to list of tasks.
     * @param size Total number of tasks in the list of tasks.
     */
    public void printAddedSuccess(Task task, int size) { 
        System.out.println(goCat);
        System.out.println("Mew! I've added this task:");
        System.out.println(task);
        System.out.println(String.format("\n** Now you have %d tasks in the list **", size));
    }

    /**
     * Prints message from Chat the Cat to tell user that a task has been deleted successfully from the list of tasks.
     * @param task Task that has been deleted from list of tasks.
     * @param size Total number of tasks in the list of tasks.
     */
    public void printDeleteSuccess(Task task, int size) {
        System.out.println(goCat);
        System.out.println("Mew! I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("\n** Now you have %d tasks in the list **", size));
    }

    /**
     * Prints message from Chat the Cat to tell user that a task has been marked successfully as done when completed.
     * @param task Task Task that has been completed.
     */
    public void printWellDone(Task task) {
        System.out.println(gdJobCat);
        System.out.println("Mew! I've marked this task as done:");
        System.out.println(task);
        System.out.println("\n* Good job, you deserve a kit-kat *");
    }

    /**
     * Retrieve next command from user to Chat the Cat.
     * 
     * @return Inputted command string from user to Chat the Cat.
     */
    public String readCommand() { 
        return sc.nextLine();
    }

    /**
     * Prints error message when Chat has encountered an error when loading tasks from data on hard disk.
     */
    public void showLoadingError() { 
        System.out.println(new ChatException("Error loading file"));
    }

    /**
     * Prints error message when Chat has encountered an error.
     * 
     * @param e Error message from Chat to user.
     */
    public void showError(ChatException e) { 
        System.out.println(errorCat + "\n" + e);
    }

    /**
     * Displays tasks from list of tasks from TaskList object.
     * 
     * @param tasks TaskList object that contains a list of tasks.
     */
    public void list(TaskList tasks) {
        int i = 0;
        System.out.println(" * list *");
        while (i < tasks.getTaskList().size()) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.getTaskList().get(i));
            i++;
        }
    }
    
}
