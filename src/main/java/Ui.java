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

    public static void printHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Greetings! I am Duke! How may I assist you?\n" + H_RULE);
    }

    public static void printGoodbye() {
        System.out.println("\nGoodbye! Have a nice day!\n");
    }

    public static void printList(TaskList tasks) {
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(i + ". " + tasks.get(i).toString());
        }
    }

    public static void printHRule() {
        System.out.println(H_RULE);
    }

    public void printDone(Task task) {
        System.out.println("Alright, I will mark this as done.\n" + task.toString());
    }

    public void printAdded(Task task, int count) {
        System.out.println("Added " + task.getName() + ". \nYou now have " + count + " items in your list.");
    }

    public void printRemoved(int index) {
        System.out.println("I have removed item " + index + ".");
    }

    public void printSearch(List<Task> tasks, String searchString) {
        System.out.println(String.format("Searching for %s...", searchString));
        System.out.println("Here you go!" );
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

}
