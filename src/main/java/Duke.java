import java.util.*;

public class Duke {
    public static ArrayList<Task> myTasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                showList();
            } else {
                taskManager(input);
            }
            System.out.println();
            input = sc.nextLine();

        }
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public static void printExceptions(String message) {
        System.out.println(message);
        System.out.println();
    }

    public static void taskManager(String task) {

        String[] line = task.split(" ", 2); // split type of task from description
        String type = line[0];
        try {
            if (task.isBlank()) {
                throw new EmptyLineException(" ");
            } else if (type.equals("done")) {
                markAsDone(line[1]);
            } else {
                if (type.equals("todo")) {
                    if (line.length != 2) {
                        throw new UnknownException(type);
                    }
                    addToDo(line[1]);
                } else if (type.equals("deadline")) {
                    if (line.length != 2) {
                        throw new UnknownException(type);
                    }
                    addDeadline(line[1]);
                } else if (type.equals("event")) {
                    if (line.length != 2) {
                        throw new UnknownException(type);
                    }
                    addEvent(line[1]);
                } else {
                    throw new IncorrectTypeException("");
                }
                int numOfTasks = myTasks.size();
                System.out.println("Now you have " + numOfTasks + " tasks in the list");
            }
        } catch (UnknownException e) {
            printExceptions(e.getMessage());
        } catch (IncorrectTypeException e) {
            printExceptions(e.getMessage());
        } catch (EmptyLineException e) {
            printExceptions(e.getMessage());
        }
    }

    public static void markAsDone(String taskNum) {
        try {
            int num = Integer.parseInt(taskNum);
            if (num < 1 || num > myTasks.size()) {
                throw new IncorrectNumberException(num);
            }
            Task t = myTasks.get(num - 1);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(t);
        } catch (IncorrectNumberException e) {
            printExceptions(e.getMessage());
        }
    }

    public static void addToDo(String name) {
        Task task = new ToDos(name);
        myTasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    public static void addDeadline(String name) {
        String[] description = name.split(" /by", 2);
        try {
            if (description.length != 2) {
                throw new MissingDateException("");
            }
            Task task = new Deadline(description[0], description[1]);
            myTasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
        } catch (MissingDateException e) {
            printExceptions(e.getMessage());
        }
    }

    public static void addEvent(String name) {
        String[] description = name.split(" /at", 2);
        try {
            if (description.length != 2) {
                throw new MissingDateException("");
            }
            Task task = new Event(description[0], description[1]);
            myTasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
        } catch (MissingDateException e) {
            printExceptions(e.getMessage());
        }
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= myTasks.size(); i++) {
            Task t = myTasks.get(i - 1);
            System.out.println(i + "." + t);
        }
    }

    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }
}
