import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
    private static List<Task> listOfTasks = new ArrayList<>();
    private static final Pattern patternToMarkDone = Pattern.compile("^done\\s+([0-9]+)$");

    private static void printAllTasks() {
        int counter = 1;
        for (Task currentTask : listOfTasks) {
            System.out.println(counter + ". " + currentTask);
            counter++;
        }
    }

    private static boolean isDoneCommand(String input) {
        boolean flagToMarkDone;
        String regex = "done\\s+[0-9]+";
        return input.matches(regex);
    }

    private static int parseMarkDoneCommand(String input) {
        Matcher m = patternToMarkDone.matcher(input);
        m.matches();
        int indexToMarkDone = Integer.parseInt(m.group(1));
        return indexToMarkDone;
    }

    private static void markTaskDone(int indexToMarkDone) {
        Task task = listOfTasks.get(indexToMarkDone-1);
        task.markAsDone();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("\n\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input;
        Scanner sc = new Scanner(System.in);

        do {
            input = sc.nextLine();
            if (input.equals("bye")) break;
            if (input.equals("list")) {
                printAllTasks();
            } else if (isDoneCommand(input)) {
                 int indexToMarkDone = parseMarkDoneCommand(input);
                 markTaskDone(indexToMarkDone);
            } else {
                System.out.println("added: " + input + "\n");
                Task incomingTask = new Task(input);
                listOfTasks.add(incomingTask);
            }
        } while(true);
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
