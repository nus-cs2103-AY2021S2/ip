import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String H_RULE = "────────────────────────────────────────────────────────────────────";
    private Scanner sc;
    private String raw_in;
    private String[] input;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInputFromUser() {
        this.raw_in = this.sc.nextLine().trim();
        return raw_in;
    }

    public static String printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String out = "Hello from\n" + logo + "\nGreetings! I am Duke! How may I assist you?";

        System.out.println(out + "\n" + H_RULE);

        return out;
    }

    public static String printGoodbye() {
        System.out.println("\nGoodbye! Have a nice day!\n");
        return "Goodbye! Have a nice day!";
    }

    public static String printList(TaskList tasks) {
        String out = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(i + ". " + tasks.get(i).toString());
            out = out + i + ". " + tasks.get(i).toString() + "\n";
        }
        return out;
    }

    public static void printHRule() {
        System.out.println(H_RULE);
    }

    public String printDone(List<Task> tasks) {
        String doneString = "Alright, I will mark " + (tasks.size() > 1 ? "these" : "this") + " as done.\n";
        for (Task t : tasks) {
            doneString += t.toString();
            doneString += "\n";
        }
        System.out.println(doneString);
        return doneString;
    }

    public String printAdded(Task task, int count) {
        System.out.println("Added " + task.getName() + ". \nYou now have " + count + " items in your list.");
        return "Added " + task.getName() + ". \nYou now have " + count + " items in your list.";
    }

    public String printRemoved(List<Task> removedTasks) {
        if (removedTasks.size() == 1) {
            System.out.println("I have removed this item: \n" + removedTasks.get(0).toString());
            return "I have removed this item: \n" + removedTasks.get(0).toString();
        } else {
            String removedString = "I have removed the following " + removedTasks.size() + " items: \n";
            for (Task t : removedTasks) {
                assert t != null: "Item removed should not be null. ";
                removedString = removedString + t.toString() + "\n";
            }
            removedString = removedString.strip();
            System.out.println(removedString);
            return removedString;
        }

    }

    public String printSearch(List<Task> tasks, String searchString) {
        String out = "";
        System.out.println(String.format("Searching for %s...", searchString));
        System.out.println("Here you go!" );
        out = out + "Here's what i found for " + searchString + ": \n";
        tasks.forEach(x -> System.out.println(x));
        for (Task t : tasks) {
            out = out + t.toString() + "\n";
        }
        return out;
    }

    public String printSorry() {
        System.out.println("Sorry, could you repeat please? ");
        return "Sorry, could you repeat please? ";
    }

}
