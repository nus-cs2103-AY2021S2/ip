import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Task> taskList = new ArrayList<>();

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

    private static boolean isDoneCommand(String command) {
        return command.equals(Commands.DONE.getCommand());
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
                return new Deadline(taskInputAndDate[0], taskInputAndDate[1]);
            } else {
                return new Event(taskInputAndDate[0], taskInputAndDate[1]);
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

    private static void doneTask(String taskIndex) throws NumberFormatException, IndexOutOfBoundsException {
        int pos = Integer.parseInt(taskIndex) - 1;
        setTaskDone(pos);
    }

    private static void addTask(String command, String[] taskInputAndDate) throws InvalidCommandException,
            EmptyDescriptionException {
        Task task = defineTask(command, taskInputAndDate);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void run() {
        introduction();
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            if (command.equals(Commands.BYE.getCommand())) {
                endProgram();
                break;
            } else if (command.equals(Commands.LIST.getCommand())) {
                printList();
            } else {
                String taskInput = scanner.nextLine();
                String[] taskInputAndDate = taskInput.split("/");
                String taskDescription = taskInputAndDate[0].trim();
                if (isDoneCommand(command)) {
                    try {
                        doneTask(taskDescription);
                    } catch (NumberFormatException numEx) {
                        System.err.println("'done' is command word; please pass a numerical index or start your task"
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