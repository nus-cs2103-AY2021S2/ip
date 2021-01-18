import java.util.*;

public class Duke {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<Task> database = new ArrayList<>();
    private static final Set<String> commands = new HashSet<>();
    private static final String EXIT_COMMAND = "bye";

    public static void main(String[] args) {
        // Populating valid commands
        setUpCommandList();
        
        // Print greeting
        printGreeting();

        // Get user input
        while (sc.hasNextLine()) {
            String command = sc.next();
            if (command.equals(EXIT_COMMAND)) {
                // User wants to exit program
                break;
            }
            String arguments = sc.nextLine();
            parseCommand(command, arguments);
        }
        
        // Print exit message
        printExitMessage();
    }
    
    private static void setUpCommandList() {
        commands.add("bye");
        commands.add("list");
        commands.add("done");
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
    }
    
    private static void printGreeting() {
        printHorizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void printExitMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    private static void parseCommand(String command, String arguments) {
        try {
            printHorizontalLine();
            checkValidCommand(command);
            switch (command) {
                case "list":
                    printAllTasks();
                    break;
                case "done":
                    markTaskAsDone(arguments);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    createTask(command, arguments);
                    break;
            }
        } catch (InvalidCommandException ex) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("Please enter a valid command:");
        } catch (NoDescriptionException ex) {
            System.out.println("OOPS!!! The description of a task cannot be empty.");
        } finally {
            printHorizontalLine();
        }

    }

    private static void checkValidCommand(String command) throws InvalidCommandException{
        if (!commands.contains(command)) {
//            throw new InvalidCommandException("Invalid command thrown from checkvalidcommand");
            throw new InvalidCommandException();
        }
    }

    private static void printAllTasks() {
        if (database.isEmpty()) {
            System.out.println("You do not have anything to do at the moment!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task task : database) {
                System.out.printf("%d.%s\n", task.getIndex(), task.toString());
            }
        }
    }

    private static void createTask(String taskType, String taskDescription) throws NoDescriptionException {
        // Note: use isEmpty()?
        if (taskDescription.isBlank()) {
//            throw new NoDescriptionException("Empty description thrown from createTask");
            throw new NoDescriptionException();
        }
        switch (taskType) {
            case "todo":
                createToDoTask(taskDescription.strip());
                break;
            case "deadline":
                createDeadlineTask(taskDescription.strip());
                break;
            case "event":
                createEventTask(taskDescription.strip());
                break;
        }
    }

    private static void createToDoTask(String taskDescription) {
        ToDoTask toDoTask = new ToDoTask(taskDescription);
        addTask(toDoTask);
    }

    private static void createDeadlineTask(String taskDescription) {
        String[] deadlineInputArr = taskDescription.split("/by");
        String deadlineTaskName = deadlineInputArr[0].strip();
        String deadline = deadlineInputArr[1].strip();
        DeadlineTask deadlineTask = new DeadlineTask(deadlineTaskName, deadline);
        addTask(deadlineTask);
    }

    private static void createEventTask(String taskDescription) {
        String[] eventInputArr = taskDescription.split("/at");
        String eventTaskName = eventInputArr[0].strip();
        String eventTime = eventInputArr[1].strip();
        EventTask eventTask = new EventTask(eventTaskName, eventTime);
        addTask(eventTask);
    }

    private static void markTaskAsDone(String arguments) throws NoDescriptionException {
        // Note: use isEmpty()?
        if (arguments.isBlank()) {
//            throw new NoDescriptionException("Empty description thrown from markTaskAsDone");
            throw new NoDescriptionException();
        }
        int index = Integer.parseInt(arguments.strip());
        Task task = database.get(index - 1);
        task.completeTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [X] %s\n", task.getName());
    }

    private static void addTask(Task task) {
        database.add(task);
        printAddedMessage(task);
    }

    private static void printAddedMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in your list.\n", database.size());
    }
}

