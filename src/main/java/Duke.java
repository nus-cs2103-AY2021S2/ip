import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static List<Task> tasks = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

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

    public static void addTask(String[] userInputArr) throws DukeException {
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

    public static void addTodo(String details) {
        Todo todo = new Todo(details);
        tasks.add(todo);
        addTaskReport(todo);
    }

    public static void addDeadline(String[] detailsArr) {
        Deadline deadline = new Deadline(detailsArr[0], detailsArr[1]);
        tasks.add(deadline);
        addTaskReport(deadline);
    }

    public static void addEvent(String[] detailsArr) {
        Event event = new Event(detailsArr[0], detailsArr[1]);
        tasks.add(event);
        addTaskReport(event);
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

    public static void markTaskAsDone(String index) {
        try {
            int taskIndex = Integer.parseInt(index.trim());
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
        } catch (NumberFormatException e) {
            printErrorMessage("I can only find a task with its index number.");
        }
    }

    public static void deleteTask(String index) {
        try {
            int taskIndex = Integer.parseInt(index.trim());
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            partition();
            System.out.println("\tNoted. This task has been removed:");
            System.out.println("\t    " + task.toString());
            displayTaskCount();
            partition();
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage("I cannot find the task you are referring to.");
        } catch (NumberFormatException e) {
            printErrorMessage("I can only find a task with its index number.");
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

    public static void handleUserInput() {

        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = sc.nextLine();
                String[] userInputArr = userInput.split(" ", 2);
                switch (userInputArr[0]) {
                    case "todo":
                    case "deadline":
                    case "event":
                        if (userInputArr.length != 2) {
                            throw new DukeException("The description of your " + userInputArr[0] + " cannot be empty!");
                        }
                        addTask(userInputArr);
                        break;
                    case "done":
                        if (userInputArr.length != 2) {
                            throw new DukeException("I'm not sure which task you want to mark as done!");
                        }
                        markTaskAsDone(userInputArr[1]);
                        break;
                    case "delete":
                        if (userInputArr.length != 2) {
                            throw new DukeException("I'm not sure which task you want to delete!");
                        }
                        deleteTask(userInputArr[1]);
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "bye":
                        farewell();
                        isRunning = false;
                        break;
                    default:
                        throw new DukeException("Sorry, I dont understand what that means :-(");
                }
            } catch (DukeException e) {
                printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        greeting();
        handleUserInput();
        sc.close();
    }
}