import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    private TaskList tasks;
    private Scanner sc;
    private Storage storage;

    public Duke() {
        this.tasks = new TaskList();
        this.sc = new Scanner(System.in);
        this.storage = new Storage();
    }

    // Formatting display content

    public static void partition() {
        System.out.println("    ---------------------------");
    }

    public static void greeting() {
        partition();
        String logo = "    __  _____ _  ___   ___   _ ___\n" +
                "    \\ \\/ /_ _| \\| \\ \\ / / | | | __|\n" +
                "     >  < | || .` |\\ V /| |_| | _|\n" +
                "    /_/\\_\\___|_|\\_| |_|  \\___/|___|\n";
        System.out.println("    Hi there! Welcome to\n" + logo);
        System.out.println("    What can I do for you today?");
        partition();
    }

    public static void farewell() {
        partition();
        System.out.println("    Goodbye. Have a nice day!!");
        partition();
    }

    public static void printErrorMessage(String message) {
        partition();
        System.out.println("    OOPS!!! " + message);
        partition();
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

    // Adding, Editing & Displaying Tasks

    public void addTask(String[] userInputArr) throws DukeException {
        switch (userInputArr[0]) {
            case "todo":
                addTodo(userInputArr[1]);
                break;
            case "deadline":
                String[] detailsArr = userInputArr[1].split(" /by ", 2);
                if (detailsArr.length != 2) {
                    throw new DukeException("You can't add a deadline without a deadline!");
                }
                addDeadline(detailsArr);
                break;
            case "event":
                detailsArr = userInputArr[1].split(" /at ", 2);
                if (detailsArr.length != 2) {
                    throw new DukeException("You can't add an event without an event time.");
                }
                addEvent(detailsArr);
                break;
            default:
                break;
        }
    }

    public void addTodo(String details) {
        Todo todo = new Todo(details);
        tasks.addTask(todo);
        addTaskReport(todo);
    }

    public void addDeadline(String[] detailsArr) throws DukeException {
        try {
            LocalDateTime date = parseDateTime(detailsArr[1]);
            Deadline deadline = new Deadline(detailsArr[0], date);
            tasks.addTask(deadline);
            addTaskReport(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the datetime format of dd/mm/yyyy hhmm.");
        }
    }

    public void addEvent(String[] detailsArr) throws DukeException {
        try {
            LocalDateTime date = parseDateTime(detailsArr[1]);
            Event event = new Event(detailsArr[0], date);
            tasks.addTask(event);
            addTaskReport(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please follow the datetime format of dd/mm/yyyy hhmm.");
        }
    }

    public void addTaskReport(Task task) {
        partition();
        System.out.println("    Got it. I've added this task");
        System.out.println("        " + task.toString());
        displayTaskCount();
        partition();
    }

    public void displayTaskCount() {
        System.out.println("    Now you have " + tasks.getTaskCount() + " in the list.");
    }

    public void markTaskAsDone(String index) {
        try {
            int taskIndex = Integer.parseInt(index.trim());
            Task task = tasks.getTask(taskIndex - 1);
            partition();
            if (task.isDone()) {
                System.out.println("    You have already completed this task:");
            } else {
                task.markAsDone();
                System.out.println("    Congratulations! You have completed this task:");
            }
            System.out.println("        " + task.toString());
            partition();
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("I cannot find the task you are referring to.");
        } catch (NumberFormatException e) {
            printErrorMessage("I can only find a task with its index number.");
        }
    }

    public void deleteTask(String index) {
        try {
            int taskIndex = Integer.parseInt(index.trim());
            Task task = tasks.getTask(taskIndex - 1);
            tasks.deleteTask(taskIndex - 1);
            partition();
            System.out.println("    Noted. This task has been removed:");
            System.out.println("        " + task.toString());
            displayTaskCount();
            partition();
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("I cannot find the task you are referring to.");
        } catch (NumberFormatException e) {
            printErrorMessage("I can only find a task with its index number.");
        }
    }

    public void listTasks() {
        partition();
        if (tasks.isEmpty()) {
            System.out.println("    It seems like there is nothing in your list.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 1; i <= tasks.getTaskCount(); ++i) {
                System.out.println("    " + i + "." + tasks.getTask(i - 1).toString());
            }
        }
        partition();
    }

    // Handling user inputs

    private static UserInputType getUserInputType(String userInput) throws DukeException {
        try {
            return UserInputType.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }

    public void handleUserInput() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = sc.nextLine();
                String[] userInputArr = userInput.split(" ", 2);
                switch (getUserInputType(userInputArr[0])) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        if (userInputArr.length != 2) {
                            throw new DukeException("The description of your " + userInputArr[0] + " cannot be empty!");
                        }
                        addTask(userInputArr);
                        break;
                    case DONE:
                        if (userInputArr.length != 2) {
                            throw new DukeException("I'm not sure which task you want to mark as done!");
                        }
                        markTaskAsDone(userInputArr[1]);
                        break;
                    case DELETE:
                        if (userInputArr.length != 2) {
                            throw new DukeException("I'm not sure which task you want to delete!");
                        }
                        deleteTask(userInputArr[1]);
                        break;
                    case LIST:
                        listTasks();
                        break;
                    case BYE:
                        farewell();
                        isRunning = false;
                        break;
                    default:
                        throw new DukeException("Sorry, I dont understand what that means :-(");
                }
                storage.saveTasksToFile(tasks);
            } catch (DukeException e) {
                printErrorMessage(e.getMessage());
            }
        }
    }

    public void run() {
        greeting();
        try {
            storage.loadTasksFromFile(tasks);
        } catch (DukeException e) {
            printErrorMessage(e.getMessage());
        }
        handleUserInput();
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
