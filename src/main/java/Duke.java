import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> list;
    protected static final String line = (char) 9 + "--------------------------------------------------------------------";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! How can I help you?");

        Scanner scanner = new Scanner(System.in);
        list  = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            TaskManager taskManager = new TaskManager();

            if (input.equals("bye")) {
                System.out.println(line + "\n" + (char) 9 + (char) 9 + "Bye! See you soon :)\n" + line);
                scanner.close();
                break;
            } else {
                taskManager.takeEvent(input, list);
            }

        }
    }
}
