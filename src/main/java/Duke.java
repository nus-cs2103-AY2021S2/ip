import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean runDuke = true;

        greet();

        while (runDuke) {
            String input = scanner.nextLine().trim();
            String[] tokenizedInput = input.split(" ", 2);

            switch (tokenizedInput[0]) {
                case "list": StringBuilder builder = new StringBuilder();
                    builder.append("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        int numbering = i + 1;
                        builder.append(numbering);
                        builder.append(". ");
                        builder.append(tasks.get(i));

                        if (numbering != tasks.size()) {
                            builder.append("\n");
                        }
                    }
                    echo(builder.toString());
                    break;
                case "done":
                    int taskIndex = Integer.parseInt(tokenizedInput[1]) - 1;
                    tasks.get(taskIndex).markAsDone();
                    echo("Nice! I've marked this task as done:\n" + tasks.get(taskIndex));
                    break;
                case "bye": echo("Bye. Hope to see you again soon!");
                    runDuke = false;
                    break;
                default:
                    addTask(tokenizedInput);
            }
        }
    }

    public static void addTask(String[] tokenizedInput) {
        Task newTask;

        switch (tokenizedInput[0]) {
            case "deadline":
                String[] deadlineDetails = tokenizedInput[1].split("/by");
                newTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                break;
            case "event":
                String[] eventDetails = tokenizedInput[1].split("/at");
                newTask = new Event(eventDetails[0], eventDetails[1]);
                break;
            default:
                newTask = new ToDo(tokenizedInput[0]);
                break;
        }
        tasks.add(newTask);
        echo(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list", newTask, tasks.size()));
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _ ___ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        echo("Hello! I'm Duke.\nWhat can I do for you?");
    }

    public static void echo(String input) {
        System.out.println("========================================");
        System.out.println(input);
        System.out.println("========================================\n");
    }
}
