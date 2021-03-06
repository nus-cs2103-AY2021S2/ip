import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String H_RULE =
            "─────────────────────────────────"
                    + "───────────────────────"
                        + "────────────";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Gets input from user through the Scanner object provided during object initialization.
     * @return Trimmed String of user input
     */
    public String getInputFromUser() {
        return this.sc.nextLine().trim();
    }

    /**
     * Prints and returns hello message.
     * @return Hello message
     */
    public static String printHello() {
        String logo = "\n   D U K E   \n";

        String out = "Hello from\n" + logo + "\nGreetings! I am Duke! \nHow may I assist you?";

        System.out.println(out + "\n" + H_RULE);

        return out;
    }

    /**
     * Prints and returns goodbye message.
     * @return Goodbye message
     */
    public static String printGoodbye() {
        System.out.println("\nGoodbye! Have a nice day!\n");
        return "Goodbye! Have a nice day!";
    }

    /**
     * Prints and returns String representation of all given Tasks.
     * @param tasks TaskList object that contains all Tasks to be printed
     * @return String representation of all the given Tasks
     */
    public static String printList(TaskList tasks) {
        String out = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(i + ". " + tasks.get(i).toString());
            out = out + i + ". " + tasks.get(i).toString() + "\n";
        }
        return out;
    }

    /**
     * Prints a horizontal line.
     */
    public static void printHRule() {
        System.out.println(H_RULE);
    }

    /**
     * Prints and returns String response of a done command.
     * @param tasks List of Tasks that are marked done
     * @return String response of done command
     */
    public String printDone(List<Task> tasks) {
        String doneString = "Alright, I will mark " + (tasks.size() > 1 ? "these" : "this") + " as done.\n";
        for (Task t : tasks) {
            doneString += t.toString();
            doneString += "\n";
        }
        System.out.println(doneString);
        return doneString;
    }

    /**
     * Prints and returns response to add command.
     * @param task Task added
     * @param count Number of Tasks in storage
     * @return Response to add command
     */
    public String printAdded(Task task, int count) {
        System.out.println("Added " + task.getName() + ". \nYou now have " + count + " items in your list.");
        return "Added " + task.getName() + ". \nYou now have " + count + " items in your list.";
    }

    /**
     * Prints and returns response to remove command.
     * @param removedTasks List of Tasks that were removed
     * @return String response to remove command
     */
    public String printRemoved(List<Task> removedTasks) {
        if (removedTasks.size() == 1) {
            System.out.println("I have removed this item: \n" + removedTasks.get(0).toString());
            return "I have removed this item: \n" + removedTasks.get(0).toString();
        } else {
            String removedString = "I have removed the following " + removedTasks.size() + " items: \n";
            for (Task t : removedTasks) {
                assert t != null : "Item removed should not be null. ";
                removedString = removedString + t.toString() + "\n";
            }
            removedString = removedString.strip();
            System.out.println(removedString);
            return removedString;
        }
    }

    /**
     * Prints and returns response to find command
     * @param tasks List of Tasks that are the results of the find
     * @param searchString String given by user for the search
     * @return String response to find command
     */
    public String printSearch(List<Task> tasks, String searchString) {
        String out = "";
        System.out.println(String.format("Searching for %s...", searchString));
        System.out.println("Here you go!");
        out = out + "Here's what i found for " + searchString + ": \n";
        tasks.forEach(System.out::println);
        for (Task t : tasks) {
            out = out + t.toString() + "\n";
        }
        return out;
    }

    /**
     * Prints and returns the sorry message.
     * @return Sorry message
     */
    public String printSorry() {
        System.out.println("Sorry, could you repeat please? ");
        return "Sorry, could you repeat please? ";
    }

    public void close() {
        this.sc.close();
    }

}
