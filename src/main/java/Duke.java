import java.util.*;

public class Duke {
    private final Scanner sc = new Scanner(System.in);
    private final List<Task> database = new ArrayList<>();
    private final Set<String> commands = new HashSet<>();
    private static final String EXIT_COMMAND = "bye";
    
    public Duke() {
        // Populate valid commands
        setUpCommandList();
    }
    
    public void run() {
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

    private void setUpCommandList() {
        commands.add("list");
        commands.add("done");
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
        commands.add("delete");
    }

    private void printGreeting() {
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

    private void printExitMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    private void parseCommand(String command, String arguments) {
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
                case "delete":
                    deleteTask(arguments);
                    break;
            }
        } catch (InvalidCommandException | NoDescriptionException | InvalidDescriptionException ex) {
            System.out.println(ex.getMessage());
        } finally {
            printHorizontalLine();
        }

    }

    private void checkValidCommand(String command) throws InvalidCommandException{
        if (!commands.contains(command)) {
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void printAllTasks() {
        if (database.isEmpty()) {
            System.out.println("You do not have anything to do at the moment!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task task : database) {
                System.out.printf("%d.%s\n", task.getIndex(), task.toString());
            }
        }
    }

    private void createTask(String taskType, String taskDescription) throws NoDescriptionException {
        if (taskDescription.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        Task task = null;
        switch (taskType) {
            case "todo":
                task = createToDoTask(taskDescription.strip());
                break;
            case "deadline":
                task = createDeadlineTask(taskDescription.strip());
                break;
            case "event":
                task = createEventTask(taskDescription.strip());
                break;
        }
        if (task != null) {
            addTask(task);
        }
    }

    private Task createToDoTask(String taskDescription) {
        return new ToDoTask(taskDescription);
    }

    private Task createDeadlineTask(String taskDescription) {
        String[] deadlineInputArr = taskDescription.split("/by");
        String deadlineTaskName = deadlineInputArr[0].strip();
        String deadline = deadlineInputArr[1].strip();
        return new DeadlineTask(deadlineTaskName, deadline);
    }

    private Task createEventTask(String taskDescription) {
        String[] eventInputArr = taskDescription.split("/at");
        String eventTaskName = eventInputArr[0].strip();
        String eventTime = eventInputArr[1].strip();
        return new EventTask(eventTaskName, eventTime);
    }

    private void markTaskAsDone(String arguments) throws NoDescriptionException, 
            InvalidDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please indicate a task number to be marked as done.");
        }
        try {
            int index = Integer.parseInt(arguments.strip());
            Task task = database.get(index - 1);
            task.completeTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("  [X] %s\n", task.getName());
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid task number");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("Please enter a valid index!");
        }
    }

    private void addTask(Task task) {
        database.add(task);
        printAddedMessage(task);
    }

    private void printAddedMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in your list.\n", database.size());
    }

    private void deleteTask(String arguments) throws NoDescriptionException, 
            InvalidDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please indicate a task number to be deleted.");
        }
        try {
            int index = Integer.parseInt(arguments.strip());
            Task task = database.get(index - 1);
            database.remove(index);
            printDeletedMessage(task);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid task number");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("Please enter a valid index!");
        }
    }

    private void printDeletedMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in your list.\n", database.size());
    }
}