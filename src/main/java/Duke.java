import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean shouldRun = true;

        greet();

        while (shouldRun) {
            String input = scanner.nextLine();
            switch (input.split(" ")[0]) {
            case "list": {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    builder.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
                }
                echo(builder.toString().trim());
                break;
            }
            case "done": {
                int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(taskIdx).setDone();
                echo(String.format("Nice! This task is done :)\n%s", tasks.get(taskIdx).toString()));
                break;
            }
            case "todo": {
                Task todoTask = createTodoTask(input);
                // echo(String.format("Added %s", input));
                tasks.add(todoTask);
                echo(String.format("Added a deadline for you:\n%s\n%s", todoTask.toString(), getNumberOfTasksString(tasks)));
                break;
            }
            case "deadline": {
                Task deadline = createDeadlineTask(input);
                tasks.add(deadline);
                echo(String.format("Added a deadline for you:\n%s\n%s", deadline.toString(), getNumberOfTasksString(tasks)));
                break;
            }
            case "event": {
                Task event = createEventTask(input);
                tasks.add(event);
                echo(String.format("Added a deadline for you:\n%s\n%s", event.toString(), getNumberOfTasksString(tasks)));
                break;
            }
            case "bye": {
                echo("Bye. Hope to see you again soon!");
                shouldRun = false;
                break;
            }
            default: {
                break;
            }
            }
        }
    }

    public static void greet() {
        String logo = "______                    _       _           _\n" +
                "|  ___|                  (_)     | |         | |\n" +
                "| |_ _ __ __ _ _ __   ___ _ ___  | |     ___ | |__\n" +
                "|  _| '__/ _` | '_ \\ / __| / __| | |    / _ \\| '_ \\\n" +
                "| | | | | (_| | | | | (__| \\__ \\ | |___| (_) | | | |\n" +
                "\\_| |_|  \\__,_|_| |_|\\___|_|___/ \\_____/\\___/|_| |_|\n" +
                "\n" +
                "\n";
        System.out.println("Hello from\n" + logo + "(a.k.a Loh Jing Yen)");
        System.out.println("What can I do for you?");
        System.out.println("Enter a command below for me to assist you");
    }

    public static Task createTodoTask(String input) {
        Pattern p = Pattern.compile("(?<=todo ).*");
        Matcher matcher = p.matcher(input);
        matcher.find();
        String taskName = matcher.group();
        return new Todo(taskName);
    }

    public static Deadline createDeadlineTask(String input) {
        // First get the task name
        Pattern p = Pattern.compile("(?<=deadline )(.*)(?= \\/by)");
        Matcher matcher = p.matcher(input);
        matcher.find();
        String taskName = matcher.group();

        // The get the deadline
        p = Pattern.compile("(?<=\\/by ).*");
        matcher = p.matcher(input);
        matcher.find();
        String deadline = matcher.group();

        return new Deadline(taskName, deadline);
    }

    public static Event createEventTask(String input) {
        // First get the task name
        Pattern p = Pattern.compile("(?<=event )(.*)(?= \\/at)");
        Matcher matcher = p.matcher(input);
        matcher.find();
        String taskName = matcher.group();

        // The get the time
        p = Pattern.compile("(?<=\\/at ).*");
        matcher = p.matcher(input);
        matcher.find();
        String time = matcher.group();

        return new Event(taskName, time);
    }

    public static String getNumberOfTasksString(ArrayList<Task> tasks) {
        return String.format("Now you have %d items in your list", tasks.size());
    }

    public static void echo(String input) {
        System.out.println("------------------------------");
        System.out.println(input);
        System.out.println("------------------------------");
    }
}
