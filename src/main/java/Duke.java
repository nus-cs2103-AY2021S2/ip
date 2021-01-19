import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final ArrayList<Task> listToDo;

    private Duke() {
        this.listToDo = new ArrayList<>();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________");
        System.out.println(logo);
        System.out.println("Hello! I'm Danh's Duke\nWhat can I do for you, Mr Danh?");
        System.out.println("    ____________________________________________________________\n");
        Duke myDuke = new Duke();
        Scanner input = new Scanner(System.in);
        boolean signalToExit = false;
        while (!signalToExit && input.hasNextLine()) {
            String command = input.nextLine();
            if (command.length() < 5) {
                switch (command) {
                    case "list":
                        printList(myDuke);
                        break;
                    case "bye":
                        signalToExit = true;
                        break;
                    default:
                        addToList(myDuke, command);
                }
            } else {
                if ("done ".equals(command.substring(0, 5))) {
                    markTaskDone(myDuke, Integer.parseInt(command.substring(5)));
                } else {
                    addToList(myDuke, command);
                }
            }
            if (!command.equals("list") && !((command.length() > 4) && command.startsWith("done "))) {
                echo(command);
            }
        }
    }

    public static void echo(String command) {
        System.out.println("    ____________________________________________________________");
        switch (command) {
            case "list":
                break;
            case "bye":
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            default:
                System.out.println("     added: " + command);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void printList(Duke duke) {
        int index = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (Task task : duke.listToDo) {
            System.out.format("     %d. " + task.printTask() + "\n", index);
            index++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void addToList(Duke duke, String taskName) {
        duke.listToDo.add(new Task(taskName));
    }

    public static void markTaskDone(Duke duke, int index) {
        System.out.println("    ____________________________________________________________");
        Task task = duke.listToDo.get(index);
        task.markAsDone();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + task.printTask());
        System.out.println("    ____________________________________________________________\n");
    }
}


