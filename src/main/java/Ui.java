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

    public String printDone(Task task) {
        System.out.println("Alright, I will mark this as done.\n" + task.toString());
        return "Alright, I will mark this as done.\n" + task.toString();
    }

    public String printAdded(Task task, int count) {
        System.out.println("Added " + task.getName() + ". \nYou now have " + count + " items in your list.");
        return "Added " + task.getName() + ". \nYou now have " + count + " items in your list.";
    }

    public String printRemoved(int index) {
        System.out.println("I have removed item " + index + ".");
        return "I have removed item " + index + ".";
    }

    public String printSearch(List<Task> tasks, String searchString) {
        String out = "";
        System.out.println(String.format("Searching for %s...", searchString));
        System.out.println("Here you go!" );
        out = out + "Here's what i found for " + searchString + ": \n";
        for (Task t : tasks) {
            System.out.println(t);
            out = out + t.toString() + "\n";
        }
        return out;
    }

}
