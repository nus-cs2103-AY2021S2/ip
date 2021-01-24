import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Task> taskList = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    private static void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task t : taskList) {
            System.out.println(counter + ". " + t);
            counter++;
        }
    }

    private static boolean isByeCommand(String command) {
        return command.equals(Commands.BYE.getCommand());
    }

    private static boolean isListCommand(String command) {
        return command.equals(Commands.LIST.getCommand());
    }

    private static boolean isDoneCommand(String command) {
        return command.equals(Commands.DONE.getCommand());
    }

    private static boolean isDeleteCommand(String command) {
        return command.equals(Commands.DELETE.getCommand());
    }

    private static boolean checkValidCommand(String command) {
        return command.equals(TaskTypes.TODO.getType()) || command.equals(TaskTypes.DEADLINE.getType())
                || command.equals(TaskTypes.EVENT.getType());
    }

    private static Task defineTask(String command, String[] taskInputAndDate) throws InvalidCommandException,
            EmptyDescriptionException {
        if (checkValidCommand(command)) {
            if (taskInputAndDate[0].length() == 0) {
                throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            } else if (command.equals(TaskTypes.TODO.getType())) {
                return new ToDo(taskInputAndDate[0]);
            } else if (command.equals(TaskTypes.DEADLINE.getType())) {
                return new Deadline(taskInputAndDate[0], LocalDate.parse(taskInputAndDate[1], formatter));
            } else {
                return new Event(taskInputAndDate[0], LocalDate.parse(taskInputAndDate[1], formatter));
            }
        } else {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "Please enter a valid command that starts with 'todo', 'deadline', 'event', 'list' or 'done'!");
        }
    }

    private static void endProgram() {
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(endMessage);
        scanner.close();
    }

    private static void setTaskDone(int pos) {
        taskList.get(pos).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(pos));
    }

    private static int calcListPos(String taskIndex) throws NumberFormatException {
        return Integer.parseInt(taskIndex) - 1;
    }

    private static void doneTask(String taskIndex) throws IndexOutOfBoundsException {
        setTaskDone(calcListPos(taskIndex));
    }

    private static void deleteTask(String taskIndex) throws IndexOutOfBoundsException {
        int pos = calcListPos(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(pos));
        taskList.remove(pos);
        printNumTasksInList();
    }

    private static void addTask(String command, String[] taskInputAndDate) throws InvalidCommandException,
            EmptyDescriptionException {
        Task task = defineTask(command, taskInputAndDate);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        printNumTasksInList();
    }

    private static void printNumTasksInList() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void run() {
        introduction();
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            if (isByeCommand(command)) {
                endProgram();
                break;
            } else if (isListCommand(command)) {
                printList();
            } else {
                String taskInput = scanner.nextLine();
                String[] taskInputAndDate = taskInput.split("/", 2);
                taskInputAndDate[0] = taskInputAndDate[0].trim();
                taskInputAndDate[1] = taskInputAndDate[1].trim().substring(3);
                String taskDescription = taskInputAndDate[0];
                if (isDoneCommand(command) || isDeleteCommand(command)) {
                    try {
                        if (isDoneCommand(command)) {
                            doneTask(taskDescription);
                        } else {
                            deleteTask(taskDescription);
                        }
                    } catch (NumberFormatException numEx) {
                        System.err.println("'" + command + "' is command word; please pass a numerical index or start your task"
                                + " with another word!");
                    } catch (IndexOutOfBoundsException arrEx) {
                        System.err.println("Please pass a valid index!");
                    }
                } else {
                    try {
                        addTask(command, taskInputAndDate);
                    } catch (InvalidCommandException | EmptyDescriptionException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}