import java.util.Scanner;

/**
 * Handles receiving user input and outputting relevant textual information for the user to see.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a welcome sequence.
     */
    public void welcome() {
        System.out.println(DIVIDER);
        System.out.println("Welcome to Duke!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Shows a goodbye sequence.
     */
    public void quit() {
        System.out.println(DIVIDER);
        System.out.println("Bye! Hope to see you again :)");
        System.out.println(DIVIDER);
    }

    /**
     * Prints a given String in between two dividers, for formatting purposes.
     * @param msg String to be printed between dividers.
     */
    public void borderPrint(String msg) {
        System.out.println(DIVIDER);
        System.out.println(msg);
        System.out.println(DIVIDER);
    }

    /**
     * Shows details of a Task that's been added to the TaskList.
     * @param task Task that's added to the TaskList.
     * @param listSize Size of the TaskList, after the Task is added.
     */
    public void showAddedTask(Task task, int listSize) {
        String msg = String.format("I've added this task: %s\nYou now have %d items on your todo list.",
                task.toString(),
                listSize);
        borderPrint(msg);
    }

    /**
     * Shows details of a Task that's been removed from the TaskList.
     * @param task Task that's been removed from the TaskList.
     * @param listSize Size of the TaskList, after the Task is added.
     */
    public void showRemovedTask(Task task, int listSize) {
        String msg = String.format("I've removed this task: %s\nYou now have %d items on your todo list.",
                task.toString(),
                listSize);
        borderPrint(msg);
    }

    /**
     * Shows details of a Task that's been marked as done/completed.
     * @param task Task that's been marked as done/completed.
     */
    public void showDoneTask(Task task) {
        String msg = String.format("Congrats! The following task has been marked as done:\n  %s",
                task.toString());
        borderPrint(msg);
    }

    /**
     * Formats the content of an Exception object between two dividers, for formatting purposes.
     * @param e Exception object to be shown.
     */
    public void showError(Exception e) {
        borderPrint(e.getMessage());
    }

    public void displayList(TaskList tasks) {
        StringBuilder items = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            items.append(String.valueOf(i) + ". " + tasks.get(i).toString() + "\n");
        }
        String output = items.toString().trim();
        if (output.length() > 0) {
            output = "Here's all the matches I found:\n" + output;
        } else {
            output = "I couldn't find anything!";
        }
        borderPrint(output);
    }
}