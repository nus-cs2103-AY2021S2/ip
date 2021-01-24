import Exceptions.*;

import java.io.File;
import java.util.*;

public class Duke {
    public static ArrayList<Task> myTasks = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void printExceptions(String message) {
        System.err.println(message);
        System.err.println();
    }

    public static void taskManager(String task) {
        String[] line = task.split(" ", 2); // split type of task from description
        String type = line[0]; // type of task

        try {
            if (task.isBlank()) {
                throw new EmptyLineException(" ");
            } else if (type.equals("done")) {
                markAsDone(line[1]);
            } else if (type.equals("delete")) {
                deleteTask(line[1]);
            } else {
                if (type.equals("todo")) {
                    if (line.length != 2 || line[1].isBlank()) {
                        throw new UnknownInputException(type);
                    }
                    addToDo(line[1]);
                } else if (type.equals("deadline")) {
                    if (line.length != 2 || line[1].isBlank()) {
                        throw new UnknownInputException(type);
                    }
                    addDeadline(line[1]);
                } else if (type.equals("event")) {
                    if (line.length != 2 || line[1].isBlank()) {
                        throw new UnknownInputException(type);
                    }
                    addEvent(line[1]);
                } else {
                    throw new IncorrectTypeException("");
                }
                int numOfTasks = myTasks.size();
                System.out.println("Now you have " + numOfTasks + " tasks in the list.");
            }
            FileManager.saveTasks(myTasks);
        } catch (UnknownInputException e) {
            printExceptions(e.getMessage());
        } catch (IncorrectTypeException e) {
            printExceptions(e.getMessage());
        } catch (EmptyLineException e) {
            printExceptions(e.getMessage());
        } catch (MissingDateException e) {
            printExceptions(e.getMessage());
        } catch (IncorrectNumberException e) {
            printExceptions(e.getMessage());
        } catch (DukeException e) {
            printExceptions(e.getMessage());
        }
    }

    public static void deleteTask(String taskNum) throws IncorrectNumberException {
        int num = Integer.parseInt(taskNum);

        if (num < 1 || num > myTasks.size()) {
            throw new IncorrectNumberException(num);
        }

        Task t = myTasks.get(num - 1);
        myTasks.remove(num - 1); // removing task from list
        System.out.println("☺ Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + myTasks.size() + " tasks in the list.");

    }

    public static void markAsDone(String taskNum) throws IncorrectNumberException {
        int num = Integer.parseInt(taskNum);

        if (num < 1 || num > myTasks.size()) {
            throw new IncorrectNumberException(num);
        }

        Task t = myTasks.get(num - 1);
        t.markAsDone();
        System.out.println("☺ Nice! I've marked this task as done:");
        System.out.println(t);
    }

    public static void addToDo(String name) {
        Task task = new ToDos(name);
        myTasks.add(task);
        System.out.println("☺ Got it. I've added this task:");
        System.out.println(task);
    }

    public static void addDeadline(String name) throws MissingDateException {
        String[] description = name.split(" /by ", 2);

        if (description.length != 2) {
            throw new MissingDateException("");
        }

        Task task = new Deadline(description[0], description[1]);
        myTasks.add(task);
        System.out.println("☺ Got it. I've added this task:");
        System.out.println(task);
    }

    public static void addEvent(String name) throws MissingDateException {
        String[] description = name.split(" /at ", 2);

        if (description.length != 2) {
            throw new MissingDateException("");
        }

        Task task = new Event(description[0], description[1]);
        myTasks.add(task);
        System.out.println("☺ Got it. I've added this task:");
        System.out.println(task);
    }

    public static void showList() {
        if (myTasks.isEmpty()) {
            System.out.print("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= myTasks.size(); i++) {
                Task t = myTasks.get(i - 1);
                System.out.println(i + "." + t);
            }
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

    public static void scanner() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println();
            if (input.equals("list")) {
                showList();
            } else {
                taskManager(input);
            }
            System.out.println();
            input = sc.nextLine();

        }
    }


    public static void main(String[] args) throws DukeException {
        welcomeMessage();
        try {
            myTasks = FileManager.displayTasks();
        } catch (Exception e) {
            printExceptions(e.getMessage());
        }
        scanner();
        System.out.println();
        System.out.println("Bye. Hope to see you again soon! ☺");
        sc.close();
    }
}
