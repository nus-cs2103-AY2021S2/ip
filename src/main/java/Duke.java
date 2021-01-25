import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * Encompasses the behavior of the Duke chat-bot. Keeps track of Tasks in a List, tasks.
 * Stores tasks in "./data/duke.txt".
 */
public class Duke {
    private static List<Task> tasks;
    private static DukeStorage storage;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        storage = DukeStorage.createStorage("./data/duke.txt");
        tasks = new ArrayList<>();
        storage.loadTaskList(tasks);

        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String command = scan.nextLine();
            printLine();
            dispatchCommand(command);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            printLine();
        }
        scan.close();
    }

    /**
     * Passing the command to the relevant task creation methods.
     *
     * @param taskCommand String of command that describes the task to be created.
     */
    public static Task dispatchTaskCreation(String taskCommand) {
        Task newTask = null;
        taskCommand = taskCommand.trim();
        try {
            switch (taskCommand.split(" ")[0]) {
                case "todo":
                    newTask = createToDo(taskCommand);
                    break;
                case "deadline":
                    newTask = createDeadline(taskCommand);
                    break;
                case "event":
                    newTask = createEvent(taskCommand);
                    break;
                default:
                    throw new DukeException("Task cannot be created: " + taskCommand);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return newTask;
    }

    private static void printLine() {
        System.out.println("------------------------------");
    }

    /**
     * Passing the command to the relevant methods.
     *
     * @param command A string of the command to be carried out.
     */
    private static void dispatchCommand(String command) {
        command = command.trim();
        try {
            switch (command.split(" ")[0]) {
                case "list":
                    printList();
                    break;
                case "done":
                    handleDone(command);
                    break;
                case "delete":
                    handleDelete(command);
                    break;
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    Task newTask = dispatchTaskCreation(command);
                    addTask(newTask);
                    break;
                default:
                    throwIllegalArgumentEx(command);
                    break;
            }
        } catch (DukeException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints each Task in the list with its index, and remarks if the list is empty.
     */
    private static void printList() {
        if (tasks.size() == 0) {
            System.out.println("Your list is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
        }
    }

    /**
     * Finds and marks task specified in the command string after calling done.
     * Handles exceptions that include index out of bounds and number format.
     *
     * @param command Command inputted into the console handled by dispatch method.
     */
    private static void handleDone(String command) {
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]) - 1;
            Task currTask = tasks.get(taskNum);
            currTask.markDone();
            System.out.println("Swee chai. It's done.\n" + currTask);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number from 1 to " + tasks.size() + " after done!");
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                System.out.println("Your list is empty!");
            } else {
                System.out.println("Please enter a number from 1 to " + tasks.size() + " after done!");
            }
        }
    }

    private static void handleDelete(String command) {
        try {
            Task currTask = tasks.remove(Integer.parseInt(command.split(" ")[1]) - 1);
            System.out.println("See la. It's deleted.\n" + currTask +
                    "\nYou currently have " + tasks.size() + " task(s) in the list.");
        } catch (Exception e) {
            if (tasks.size() == 0) {
                System.out.println("Your list is empty!");
            } else {
                System.out.println("Please enter a number from 1 to " + tasks.size() + " after delete!");
            }
        }
    }

    /**
     * Adds specified task into task list and prints user feedback.
     *
     * @param task Task to be added.
     */
    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Your task has been added: " + task +
                "\nYou currently have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Creates Task from the command with todo.
     *
     * @param command User input with the command todo.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     */
    private static Task createToDo(String command) throws DukeException {
        if (command.substring(4).trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"todo\"");
        }
        return new ToDo(command.substring(5));
    }

    /**
     * Creates Task from the command with deadline.
     *
     * @param command User input with the command deadline.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     * @throws DukeException if there is no deadline specified by "/by".
     */
    private static Task createDeadline(String command) throws DukeException {
        String[] splitCommand = command.substring(8).split("/by");
        if (splitCommand[0].trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"deadline\"");
        }
        if (splitCommand.length == 1 || splitCommand[1].trim().length() == 0) {
            throw new DukeException("Expected argument \"/by\" specifying deadline of task for \"deadline\"");
        }
        return new Deadline(splitCommand[0].trim(), splitCommand[1].trim());
    }

    /**
     * Creates Task from the command with event.
     *
     * @param command User input with the command event.
     * @return Task created.
     * @throws DukeException if there is no description of the task.
     * @throws DukeException if there is no duration specified by "/at".
     */
    private static Task createEvent(String command) throws DukeException {
        String[] splitCommand = command.substring(5).split("/at");
        if (splitCommand[0].trim().length() == 0) {
            throw new DukeException("Expected argument describing task after \"event\"");
        }
        if (splitCommand.length == 1 || splitCommand[1].trim().length() == 0) {
            throw new DukeException("Expected argument \"/at\" specifying duration of task for \"event\"");
        }
        return new Event(splitCommand[0].trim(), splitCommand[1].trim());
    }

    /**
     * Throws IllegalArgumentException if the first word of the command is wrong or absent.
     *
     * @param command User input provided.
     * @throws IllegalArgumentException if the first word of the command is wrong or absent.
     */
    private static void throwIllegalArgumentEx(String command) throws IllegalArgumentException {
        if (command.length() > 0) {
            throw new IllegalArgumentException("That is not a valid command!\n" +
                    "These are the possible commands:\n" +
                    "\"list\" \"done\" \"todo\" \"deadline\" \"event\"");
        } else {
            throw new IllegalArgumentException("What are you trying to say?\n" +
                    "Please enter something ...");
        }
    }
}
