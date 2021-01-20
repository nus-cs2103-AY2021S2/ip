import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean isActive;
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
        isActive = true;
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();

        greet();
        listen();
    }

    private void greet() {
        System.out.println("Hello, I'm Duke!\n" + "What can I do for you?\n");
    }

    private void listen() {
        while (isActive) {
            System.out.println("Let me know what to do!");
            String input = scanner.nextLine();
            String command = input.split(" ")[0];

            switch (command) {
            case "bye":
                shutDown();
                break;
            case "done":
                int index = Integer.parseInt(input.split(" ")[1]);
                taskDone(index);
                break;
            case "list":
                printList();
                break;
            default:
                addToList(input);
                break;
            }
        }
    }

    private void addToList(String name) {
        tasks.add(new Task(name));
        System.out.println("added: " + name + "\n");
    }

    private void printList() {
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + ". " + t);
            i++;
        }
        System.out.println();
    }

    private void taskDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println("Nice! I shall mark this task as done: \n"
                + task
                + "\nOne step closer to freedom :D\n");
    }

    private void shutDown() {
        isActive = false;
        System.out.println("Bye bye, see you soon!\n");
    }
}
