import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Scanner scanner;
    private final ArrayList<Task> tasks;

    public static void main(String[] args) {
        String logo =
                "$$    $$                                $$\n" +
                "$$    $$                                $$\n" +
                "$$    $$   $$$$$$   $$$$$$$    $$$$$$$  $$$$$$$   $$$$$$ $$$$    $$$$$$   $$$$$$$\n" +
                "$$$$$$$$  $$    $$  $$    $$  $$        $$    $$  $$   $$   $$        $$  $$    $$\n" +
                "$$    $$  $$$$$$$$  $$    $$  $$        $$    $$  $$   $$   $$   $$$$$$$  $$    $$\n" +
                "$$    $$  $$        $$    $$  $$        $$    $$  $$   $$   $$  $$    $$  $$    $$\n" +
                "$$    $$   $$$$$$$  $$    $$   $$$$$$$  $$    $$  $$   $$   $$   $$$$$$$  $$    $$\n";

        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

    }

    public Duke() {
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();

        greet();

        while (scanner.hasNext()) {
            try {
                String command = scanner.next();
                listen(command);
            } catch (DukeException e) {
                System.out.println("Sorry boss something went wrong: ");
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Sorry boss something went wrong: ");
                System.out.println("Please follow the Date-Time format: YYYY-MM-DD TIME");
            } finally {
                System.out.println("\nHit me up boss.");
            }
        }
    }

    private void greet() {
        System.out.println("Greetings Boss, I'm your top henchman.\n" + "What can I do for you?\n");
    }

    private void listen(String command) throws DukeException {
        switch (command) {
        case "bye":
            shutDown();
            break;
        case "deadline":
            // substring from index=1 to ignore the whitespace following "deadline", at index = 0.
            addTask(new Deadline(scanner.nextLine().substring(1)));
            break;
        case "delete":
            deleteTask(scanner.nextInt());
            break;
        case "done":
            doneTask(scanner.nextInt());
            break;
        case "event":
            // substring from index=1 to ignore the whitespace following "event", at index = 0.
            addTask(new Event(scanner.nextLine().substring(1)));
            break;
        case "list":
            printTasks();
            break;
        case "todo":
            // substring from index=1 to ignore the whitespace at index = 0.
            addTask(new ToDo(scanner.nextLine().substring(1)));
            break;
        default:
            throw new DukeException("Invalid command, please provide a supported command.");
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("Here's a new task: " + task);
        saveList();
    }

    private void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Alrighty bossman. I shall wipe this task off the face of the earth: \n"
                + task
                + "\nGood riddance.");
        saveList();
    }

    private void doneTask(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("Impressive, yet another task has been done: \n"
                + task
                + "\nOne step closer to freedom now boss.");
        saveList();
    }

    private void printTasks() {
        System.out.println("You have " + tasks.size() + " task(s) in the list:");

        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + ". " + t);
            i++;
        }
    }

    private void saveList() {
        try {
            FileWriter fw = new FileWriter("duke_saved_tasks");
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task t : tasks) {
                bw.write(t.toLog() + "\n");
//                System.out.println(t.toLog());
            }

            bw.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void shutDown() {
        System.out.println("See you soon boss!");
        System.exit(0);
    }
}
