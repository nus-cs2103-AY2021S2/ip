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

        switch (tokenizedInput[0]) {
            case "list": listTask();
                break;
            case "done":
                int taskIndex = Integer.parseInt(tokenizedInput[1]) - 1;
                doneTask(taskIndex);
                break;
            case "bye": exit();
                return false;
            default:
                addTask(tokenizedInput);
                break;
        }
        return true;
    }

    public static void addTask(String[] tokenizedInput) {
        Task newTask;

        switch (tokenizedInput[0]) {
            case "deadline":
                String[] deadlineDetails = tokenizedInput[1].split("/by");
                newTask = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
                break;
            case "event":
                String[] eventDetails = tokenizedInput[1].split("/at");
                newTask = new Event(eventDetails[0].trim(), eventDetails[1].trim());
                break;
            default:
                newTask = new ToDo(tokenizedInput[1].trim());
                break;
        }
        tasks.add(newTask);
        echo(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", newTask, tasks.size()));
    }

    public static void doneTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
        echo("Nice! I've marked this task as done:\n\t" + tasks.get(taskIndex));
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
