import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueDuke = true;

        greet();

        while (continueDuke) {
            String input = scanner.nextLine().trim();
            continueDuke = processInput(input);
        }
    }

    public static boolean processInput(String input) {
        String[] tokenizedInput = input.split(" ", 2);

        try {
            switch (tokenizedInput[0]) {
                case "list":
                    listTask();
                    break;
                case "done":
                    doneTask(tokenizedInput);
                    break;
                case "bye":
                    exit();
                    return false;
                case "todo":
                    addTodo(tokenizedInput);
                    break;
                case "deadline":
                    addDeadline(tokenizedInput);
                    break;
                case "event":
                    addEvent(tokenizedInput);
                    break;
                case "delete":
                    deleteTask(tokenizedInput);
                    break;
                default:
                    throw new DukeException("☹ OPPS!!! I'm sorry, but I don't know what that means :-(");
            }

            return true;
        } catch (DukeException e) {
            echo(e.getMessage());
        }

        return true;
    }

    public static void addTodo(String[] tokenizedInput) throws DukeException {
        if (tokenizedInput.length != 2) {
            throw new DukeException("☹ OPPS!!! The description of a todo cannot be empty.");
        }

        Task newTask = new ToDo(tokenizedInput[1].trim());
        tasks.add(newTask);
        echo(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", newTask, tasks.size()));
    }

    public static void addDeadline(String[] tokenizedInput) throws DukeException {
        if (tokenizedInput.length != 2) {
            throw new DukeException("☹ OPPS!!! The description of a deadline cannot be empty.");
        }

        String[] deadlineDetails = tokenizedInput[1].split("/by");
        if (deadlineDetails.length != 2) {
            throw new DukeException("☹ OPPS!!! Deadline should be in the following format: deadline [description] /by [deadline]");
        }

        Task newTask = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
        tasks.add(newTask);
        echo(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", newTask, tasks.size()));
    }

    public static void addEvent(String[] tokenizedInput) throws DukeException {
        if (tokenizedInput.length != 2) {
            throw new DukeException("☹ OPPS!!! The description of a event cannot be empty.");
        }

        String[] eventDetails = tokenizedInput[1].split("/at");
        if (eventDetails.length != 2) {
            throw new DukeException("☹ OPPS!!! Event should be in the following format: event [description] /at [datetime]");
        }

        Task newTask = new Event(eventDetails[0].trim(), eventDetails[1].trim());
        tasks.add(newTask);
        echo(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", newTask, tasks.size()));
    }

    public static void doneTask(String[] input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(input[1]) - 1;
            tasks.get(taskIndex).markAsDone();
            echo("Nice! I've marked this task as done:\n\t" + tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OPPS!!! Invalid task number entered.");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OPPS!!! Invalid task number entered.");
        }
    }

    public static void listTask() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int numbering = i + 1;
            builder.append(String.format("\t%d. %s", numbering, tasks.get(i)));

            if (numbering != tasks.size()) {
                builder.append("\n");
            }
        }
        echo(builder.toString());
    }

    public static void deleteTask(String[] input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(input[1]) - 1;
            Task deletedTask = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            echo("Noted! I've removed this task:\n\t" + deletedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OPPS!!! Invalid task number entered.");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OPPS!!! Invalid task number entered.");
        }
    }

    public static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _ ___\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        echo("Hello! I'm Duke.\nWhat can I do for you?");
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String input) {
        System.out.println("========================================");
        System.out.println(input);
        System.out.println("========================================\n");
    }
}
