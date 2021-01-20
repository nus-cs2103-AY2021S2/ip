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

        while (scanner.hasNext()) {
            try {
                String command = scanner.next();
                listen(command);
            } catch (DukeInvalidCommandException e) {
                System.out.println("Sorry I don't understand, please key in a valid command!\n");
            } catch (DukeToDoException e) {
                System.out.println("ToDo description cannot be blank!\n");
            } catch (DukeEventException e) {
                System.out.println("Event command must follow the format: description /at time\n");
            } catch (DukeDeadlineException e) {
                System.out.println("Deadline command must follow the format: description /by time\n");
            } finally {
                System.out.println("Hit me up boss.");
            }
        }
    }

    private void greet() {
        System.out.println("Hello, I'm Duke!\n" + "What can I do for you?\n");
    }

    private void listen(String command) throws
            DukeInvalidCommandException, DukeToDoException, DukeEventException, DukeDeadlineException {
        switch (command) {
        case "bye":
            shutDown();
            break;
        case "deadline":
            // substring from index=1 to ignore the whitespace following "deadline", at index = 0.
            addToList(new Deadline(scanner.nextLine().substring(1)));
            break;
        case "delete":
            deleteTask(scanner.nextInt());
            break;
        case "done":
            taskDone(scanner.nextInt());
            break;
        case "event":
            // substring from index=1 to ignore the whitespace following "event", at index = 0.
            addToList(new Event(scanner.nextLine().substring(1)));
            break;
        case "list":
            printList();
            break;
        case "todo":
            // substring from index=1 to ignore the whitespace at index = 0.
            addToList(new ToDo(scanner.nextLine().substring(1)));
            break;
        default:
            throw new DukeInvalidCommandException();
        }
    }

    private void addToList(Task task) {
        tasks.add(task);
        System.out.println("A new challenger has appeared: " + task + "\n");
    }

    private void printList() {
        System.out.println("You have " + tasks.size() + " task(s) in the list:");

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
        System.out.println("Impressive, yet another task has been done: \n"
                + task
                + "\nOne step closer to freedom now boss.\n");
    }

    private void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Alrighty bossman. I shall wipe this task off the face of the earth: \n"
                + task
                + "\nGood riddance.\n");
    }

    private void shutDown() {
        System.out.println("See you soon boss!\n");
        System.exit(0);
    }
}
