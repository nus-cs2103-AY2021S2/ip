import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Scanner scanner;
    private final ArrayList<Task> tasks;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

    }

    public Duke() {
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();

        greet();
        listen();
    }

    private void greet() {
        System.out.println("Hello, I'm Duke!\n" + "What can I do for you?\n");
    }

    private void listen() {
        while (scanner.hasNext()) {
            String command = scanner.next();

            switch (command) {
            case "bye":
                shutDown();
                break;
            case "deadline":
                String[] splitD = scanner.nextLine().split(" /by ");
                // substring from index=1 to ignore the whitespace following "deadline", at index = 0.
                String deadline = splitD[0].substring(1);
                String deadlineTime = splitD[1];
                addToList(new Deadline(deadline, deadlineTime));
                break;
            case "done":
                int index = scanner.nextInt();
                taskDone(index);
                break;
            case "event":
                String[] splitE = scanner.nextLine().split(" /at ");
                // substring from index=1 to ignore the whitespace following "event", at index = 0.
                String event = splitE[0].substring(1);
                String eventTime = splitE[1];
                addToList(new Event(event, eventTime));
                break;
            case "list":
                printList();
                break;
            case "todo":
                // substring from index=1 to ignore the whitespace at index = 0.
                addToList(new ToDo(scanner.nextLine().substring(1)));
                break;
            default:
                System.out.println("Invalid input\n");
                break;
            }

            System.out.println("Let me know what to do!");
        }
    }

    private void addToList(Task task) {
        tasks.add(task);
        System.out.println("added: " + task + "\n");
    }

    private void printList() {
        System.out.println("You have " + tasks.size() + " task(s) in the list.");

        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + ". " + t);
            i++;
        }
        System.out.println();
    }

    private void taskDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("Nice! I shall mark this task as done: \n"
                + task
                + "\nOne step closer to freedom :D\n");
    }

    private void shutDown() {
        System.out.println("Bye bye, see you soon!\n");
        System.exit(0);
    }
}
