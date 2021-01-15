import java.util.*;

public class Duke {

    public static List<Task> tasks = new ArrayList<>();

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
        System.out.println("    ☹ OOPS!!! " + message);
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
        System.out.println("    Got it. I've added this task");
        System.out.println("        " + task.toString());
        displayTaskCount();
        partition();
    }

    public static void displayTaskCount() {
        System.out.println("    Now you have " + tasks.size() + " in the list.");
    }

    public static void markTaskAsDone(int taskIndex) throws DukeException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException("I cannot find the task you are referring to.");
        }
        Task task = tasks.get(taskIndex - 1);
        partition();
        if (task.isDone()) {
            System.out.println("    You have already completed this task:");
        } else {
            task.markAsDone();
            System.out.println("    Congratulations! You have completed this task:");
        }
        System.out.println("        " + task.toString());
        partition();
    }

    public static void deleteTask(int taskIndex) throws DukeException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException("I cannot find the task you are referring to.");
        }
        Task task = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        partition();
        System.out.println("    Noted. This task has been removed:");
        System.out.println("        " + task.toString());
        displayTaskCount();
        partition();
    }

    public static void listTasks() {
        partition();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); ++i) {
            System.out.println("    " + i + "." + tasks.get(i - 1).toString());
        }
        partition();
    }

    // Handling user inputs

    public static void handleUserInput(String userInput) throws DukeException {
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
        } catch (DukeException e) {
            throw e;
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
            try {
                handleUserInput(userInput);
            } catch (DukeException e) {
                printErrorMessage(e.getMessage());
            }
        }

        farewell();
        sc.close();
    }
}