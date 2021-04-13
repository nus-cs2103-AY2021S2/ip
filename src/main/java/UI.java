import java.util.Scanner;
import java.util.TreeSet;
import java.util.List;

public class UI {
    private static final String line = "────────────────────────────────────────────────────────────────────";
    private Scanner sc;
    private String raw_in;
    private String[] input;

    public UI() {
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

        String str = "Hello from\n" + logo +"Greetings! I am Duke! How may I assist you?\n" + line;
        System.out.println(str);
        return str;
    }

    public static String printGoodbye() {
        System.out.println("\nGoodbye\n");
        return "\n Goodbye \n";
    }

    public static String printList(TaskList tasks) {
        String str ="";

        for (int i = 1; i <= tasks.getCount(); i++) {
            System.out.println(i + ". " + tasks.get(i).toString());
            str = str + i + "." + tasks.get(i).toString() + "\n";
        }
        return str;
    }

    public static void printline() {
        System.out.println(line);
    }

    public String printDone(Task task) {
        String str = "Alright, I will mark this as done.\n" + task.toString();
        System.out.println(str);
        return str;

    }

    public String printAdded(Task task, int count) {
        String str ="Added" + task.getName() + ". \nYou now have " + count + " items in your list.";
        System.out.println(str);
        return str;
    }

    public String printRemoved(int index) {
        String str ="I have removed item " + index + ".";
        System.out.println(str);
        return str;
    }

    public String printFind(List<Task> tasks){
        String str = "";
        for (Task t : tasks){
            System.out.println(t);
            str = str + t.toString() + "\n";
        }
        return str;

    }
}