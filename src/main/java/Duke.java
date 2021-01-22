import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static List<Task> tasks = new ArrayList<>();

    // Formatting display content

    public static void partition() {
        System.out.println("\t---------------------------");
    }

    public static void greeting() {
        partition();
        String logo = "\t__  _____ _  ___   ___   _ ___\n" +
                "\t\\ \\/ /_ _| \\| \\ \\ / / | | | __|\n" +
                "\t >  < | || .` |\\V / | |_ | |_ |\n " +
                "\t/_/\\_\\___|_|\\_| |_|  \\___/|___|\n";
        System.out.println("\tHi there! Welcome to\n" + logo);
        System.out.println("\tWhat can I do for you today?");
        partition();
    }

    public static void farewell() {
        partition();
        System.out.println("\tGoodbye. Have a nice day!!");
        partition();
    }

    public static void printErrorMessage(String message) {
        partition();
        System.out.println("\t☹ OOPS!!! " + message);
        partition();
    }

    // Adding, Editing & Displaying Tasks

    public static void addTodo(String userInput) {
        Todo todo = new Todo(userInput);
        tasks.add(todo);
        addTaskReport(todo);
    }

    public static void addDeadline(String userInput) throws DukeException {
        String[] userInputArr = userInput.split(" /by ", 2);
        if (userInputArr.length != 2) {
            throw new DukeException("You can't add a deadline without a deadline!");
        } else {
            Deadline deadline = new Deadline(userInputArr[0], userInputArr[1]);
            tasks.add(deadline);
            addTaskReport(deadline);
        }
    }

    public static void addEvent(String userInput) throws DukeException {
        String[] userInputArr = userInput.split(" /at ", 2);
        if (userInputArr.length != 2) {
            throw new DukeException("You can't add an event without an event time.");
        } else {
            Event event = new Event(userInputArr[0], userInputArr[1]);
            tasks.add(event);
            addTaskReport(event);
        }
    }

    public static void addTaskReport(Task task) {
        partition();
        System.out.println("\tGot it. I've added this task");
        System.out.println("\t    " + task.toString());
        displayTaskCount();
        partition();
    }

    public static void displayTaskCount() {
        System.out.println("\tNow you have " + tasks.size() + " in the list.");
    }

    public static void markTaskAsDone(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex - 1);
            partition();
            if (task.isDone()) {
                System.out.println("\tYou have already completed this task:");
            } else {
                task.markAsDone();
                System.out.println("\tCongratulations! You have completed this task:");
            }
            System.out.println("\t    " + task.toString());
            partition();
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("I cannot find the task you are referring to.");
        }
    }

    public static void deleteTask(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            partition();
            System.out.println("\tNoted. This task has been removed:");
            System.out.println("\t    " + task.toString());
            displayTaskCount();
            partition();
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("I cannot find the task you are referring to.");
        }
    }

    public static void listTasks() {
        partition();
        if (tasks.isEmpty()) {
            System.out.println("\tIt seems like there is nothing in your list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); ++i) {
                System.out.println("\t" + i + "." + tasks.get(i - 1).toString());
            }
        }
        partition();
    }

    // Handling user inputs

    public static void handleUserInput(String userInput) {
        String[] userInputArr = userInput.split(" ", 2);

        try {
            switch (userInputArr[0]) {
                case "done":
                    if (userInputArr.length != 2) {
                        throw new DukeException("I'm not sure which task you want to mark as done!");
                    }
                    int taskIndex = Integer.parseInt(userInputArr[1]);
                    markTaskAsDone(taskIndex);
                    break;
                case "delete":
                    if (userInputArr.length != 2) {
                        throw new DukeException("I'm not sure which task you want to delete!");
                    }
                    taskIndex = Integer.parseInt(userInputArr[1]);
                    deleteTask(taskIndex);
                    break;
                case "todo":
                    if (userInputArr.length != 2) {
                        throw new DukeException("The description of a todo cannot be empty!");
                    }
                    addTodo(userInputArr[1]);
                    break;
                case "deadline":
                    if (userInputArr.length != 2) {
                        throw new DukeException("The description of a deadline cannot be empty!");
                    }
                    addDeadline(userInputArr[1]);
                    break;
                case "event":
                    if (userInputArr.length != 2) {
                        throw new DukeException("The description of an event cannot be empty!");
                    }
                    addEvent(userInputArr[1]);
                    break;
                default:
                    throw new DukeException("Sorry, I dont understand what that means :-(");
            }
        } catch (NumberFormatException e) {
            printErrorMessage("I can only find a task with its index number.");
        } catch (DukeException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greeting();

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                listTasks();
                continue;
            }
            handleUserInput(userInput);
        }

        farewell();
        sc.close();
    }
}