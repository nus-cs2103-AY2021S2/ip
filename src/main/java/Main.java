import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner sc;
    private List<Task> taskList;
    private State state;
    
    public static void main(String[] args) {
        new Main().run(args);
    }
    
    public void run(String[] args) {
        // Initialize the required components
        initialize(args);
        
        // Run infinite loop asking for user command until user enter exit command
        runLoop();
        
        // Exit the program
        exit();
    }
    
    private void initialize(String[] args) {
        this.sc = new Scanner(System.in);
        this.taskList = new ArrayList<>();
        this.state = State.ONLINE;
        // Print greeting
        printGreeting();
    }
    
    private void exit() {
        // Print exit message
        printExitMessage();
    }

    private void runLoop() {
        while (isOnline()) {
            try {
                printHorizontalLine();
                Command command = Parser.parseCommand(sc.next());
                String arguments = sc.nextLine();
                executeCommand(command, arguments);
            } catch (InvalidCommandException ex) {
                printHorizontalLine();
                System.out.println(ex.getMessage());
            }
        }
    }

    private boolean isOnline() {
        return this.state == State.ONLINE;
    }

    private void off() {
        this.state = State.OFF;
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
    }

    private void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    private void executeCommand(Command command, String arguments) {
        try {
            printHorizontalLine();
            switch (command) {
            case BYE:
                off();
                break;
            case LIST:
                printAllTasks();
                break;
            case DONE:
                markTaskAsDone(arguments);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                createTask(command, arguments);
                break;
            case DELETE:
                deleteTask(arguments);
                break;
            case HELP:
                printHelp();
                break;
            }
        } catch (NoDescriptionException | InvalidDescriptionException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void printAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You do not have anything to do at the moment!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= taskList.size(); i++) {
                System.out.printf("%d.%s\n", i, taskList.get(i - 1).toString());
            }
        }
    }

    private void createTask(Command taskType, String taskDescription) throws NoDescriptionException {
        if (taskDescription.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        Task task = null;
        switch (taskType) {
        case TODO:
            task = createToDoTask(taskDescription.strip());
            break;
        case DEADLINE:
            task = createDeadlineTask(taskDescription.strip());
            break;
        case EVENT:
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
            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
            Task task = taskList.get(index);
            task.completeTask();
            printMarkedAsDoneMessage(task);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid task number");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("Please enter a valid index!");
        }
    }

    private void printMarkedAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [X] %s\n", task.getName());
    }

    private void addTask(Task task) {
        taskList.add(task);
        printAddedMessage(task);
    }

    private void printAddedMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in your list.\n", taskList.size());
    }

    private void deleteTask(String arguments) throws NoDescriptionException,
            InvalidDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please indicate a task number to be deleted.");
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
            Task task = taskList.get(index);
            taskList.remove(index);
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
        System.out.printf("Now you have %d tasks in your list.\n", taskList.size());
    }

    private void printHelp() {
        System.out.println("Here are the list of available commands:");
        System.out.println("BYE:\nExit the program\nUsage: 'bye'");
        System.out.println();
        System.out.println("LIST:\nPrint the list of current tasks\nUsage: 'list'");
        System.out.println();
        System.out.println("DONE:\nMark a task as completed\nUsage: 'done <task_number>'");
        System.out.println();
        System.out.println("DELETE:\nDelete a task\nUsage: 'delete <task_number>'");
        System.out.println();
        System.out.println("TODO:\nAdd a todo task\nUsage: 'todo <task_description>'");
        System.out.println();
        System.out.println("DEADLINE:\nAdd a deadline task\nUsage: 'deadline <task_description> /by <deadline>'");
        System.out.println();
        System.out.println("EVENT:\nAdd an event task\nUsage: 'event <task_description> /at <event_time>'");
        System.out.println();
        System.out.println("HELP:\nPrint available commands\nUsage: 'help'");
    }
}

