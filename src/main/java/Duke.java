import java.util.ArrayList;
import java.util.Scanner;

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
                echo(String.format("Nice! This task is done :) \n%s", tasks.get(taskIdx).toString()));
                break;
            }
            case "bye": {
                echo("Bye. Hope to see you again soon!");
                shouldRun = false;
                break;
            }
            default: {
                tasks.add(new Task(input));
                echo(String.format("Added %s", input));
                break;
            }
            }
        }
    }

    public static void greet() {
        String logo = " _____      _               _   _             \n" +
                "/  ___|    | |             | | (_)            \n" +
                "\\ `--.  ___| |__   __ _ ___| |_ _  __ _ _ __  \n" +
                " `--. \\/ _ \\ '_ \\ / _` / __| __| |/ _` | '_ \\ \n" +
                "/\\__/ /  __/ |_) | (_| \\__ \\ |_| | (_| | | | |\n" +
                "\\____/ \\___|_.__/ \\__,_|___/\\__|_|\\__,_|_| |_|\n" +
                "                                              \n" +
                "                                              ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("Enter a command below for me to assist you");
    }

    public static void echo(String input) {
        System.out.println("------------------------------");
        System.out.println(input);
        System.out.println("------------------------------");
    }
}
