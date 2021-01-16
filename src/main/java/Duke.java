import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";
    private static boolean isActive = true;

    private static List<Task> tasks = new ArrayList<>();

    /**
     * Print an adding message corresponding to the added Task's type
     * @param task Task that was added
     */
    private static void printTaskAdding(Task task) {
        System.out.println("___________________________________________________________");
        System.out.println("Got it meow. I've added this task:");
        System.out.printf("  [%s][%s] %s\n", task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        System.out.println("___________________________________________________________\n");
    }

    /**
     * Add a ToDo into the internal list
     * @param desc Description of the ToDo
     */
    private static void addToDo(String desc) {
        ToDo newToDo = new ToDo(desc);
        tasks.add(newToDo);
        printTaskAdding(newToDo);
    }

    /**
     * Add a Deadline into the internal list
     * @param desc Description of the Deadline
     * @param dateTime Date/Time that the Deadline must be done
     */
    private static void addDeadline(String desc, String dateTime) {
        Deadline newDeadline = new Deadline(desc, dateTime);
        tasks.add(newDeadline);
        printTaskAdding(newDeadline);
    }

    /**
     * Add an Event into the internal list
     * @param desc Description of the Event
     * @param dateTimeRange Date/Time range when the Event take place
     */
    private static void addEvent(String desc, String dateTimeRange) {
        Event newEvent = new Event(desc, dateTimeRange);
        tasks.add(newEvent);
        printTaskAdding(newEvent);
    }

    /**
     * Mark a task as completed
     * @param index Index of the completed task
     */
    private static void completeTask(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Task task = tasks.get(index);
            task.markAsDone();

            System.out.println("___________________________________________________________");
            System.out.println("Good job meow, I've marked this task as done:");
            System.out.printf("%d.[%s][%s] %s\n", index + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                    task.getDesc());
            System.out.println("___________________________________________________________\n");
        }
    }

    /**
     * List down all tasks in the internal list
     */
    private static void listTasks() {
        System.out.println("___________________________________________________________");
        System.out.println("Meow, here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        }
        System.out.println("___________________________________________________________\n");
    }

    /**
     * Lifecycle of the chatbot
     * @param args
     */
    public static void main(String[] args) {
        // Opening message
        System.out.println("___________________________________________________________");
        System.out.printf("Meow, I'm %s\nWhat can I do for you today?\n", CHATBOT_NAME);
        System.out.println("___________________________________________________________\n");

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String command = "";

        // Respond to user's commands and exit when user enters bye
        while(isActive) {
            command = scanner.nextLine();

            if(command.matches("^todo .+$")) {
                String desc = command.substring(5);
                addToDo(desc);
            } else if(command.matches("^deadline .+$")) {
                String[] splits = command.substring(9).split(" /by ");
                addDeadline(splits[0], splits[1]);
            } else if(command.matches("^event .+$")) {
                String[] splits = command.substring(6).split(" /at ");
                addEvent(splits[0], splits[1]);
            } else if(command.matches("^done ((100)|[1-9][0-9]|[0-9])$")) {
                String[] splits = command.split(" ");
                try {
                    completeTask(Integer.valueOf(splits[1]) - 1);
                } catch(IndexOutOfBoundsException e) {
                    if(tasks.size() == 0) {
                        System.out.println("___________________________________________________________");
                        System.out.println("There are no task to be completed");
                        System.out.println("___________________________________________________________\n");
                    } else {
                        System.out.println("___________________________________________________________");
                        System.out.printf("Please enter a valid task index ranging from 1 to %d (inclusive)\n",
                                tasks.size());
                        System.out.println("___________________________________________________________\n");
                    }
                }
            } else if(command.equals("list")) {
                listTasks();
            } else if(command.equals("bye")) {
                isActive = false;
            } else {
                System.out.println("___________________________________________________________");
                System.out.println("No such command, Please try again with another command meow.");
                System.out.println("___________________________________________________________\n");
            }
        }

        // Closing message
        System.out.println("___________________________________________________________");
        System.out.println("Meow. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }
}
