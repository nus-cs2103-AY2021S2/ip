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
            System.out.println(counter + "." + currentTask);
            counter++;
        }
    }

    private static void addTask(Task incomingTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + incomingTask);
        listOfTasks.add(incomingTask);
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }



    private static int parseMarkDoneCommand(String input) {
        Matcher m = patternToMarkDone.matcher(input);
        m.matches(); // to be implemented : throw error here is m does not match...
        int indexToMarkDone = Integer.parseInt(m.group(1));
        return indexToMarkDone;
    }

    private static void markTaskDone(int indexToMarkDone) {
        Task task = listOfTasks.get(indexToMarkDone-1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
    }

    private static Task parseAddDeadline(String input) {
        String regex = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex);
        Matcher m = patternToMatch.matcher(input);
        m.matches(); //to be implemented : throw error if m does not match
        String description = m.group(1);
        String by = m.group(2);
        return new Deadline(description,by);
    }

    private static Task parseAddEvent(String input) {
        String regex = "^event\\s+(.+)\\s+/at\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex);
        Matcher m = patternToMatch.matcher(input);
        m.matches(); //to be implemented : throw error if m does not match
        String description = m.group(1);
        String at = m.group(2);
        return new Event(description,at);
    }

    private static Task parseToDo(String input) {
        String regex = "^todo\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex);
        Matcher m = patternToMatch.matcher(input);
        m.matches(); //to be implemented : throw error if m does not match
        String description = m.group(1);
        return new ToDo(description);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("bye")) break;
            if (input.equals("list")) {
                printAllTasks();
            } else if (CommandType.MARK_AS_DONE.isMatchingInput(input)) {
                 int indexToMarkDone = parseMarkDoneCommand(input);
                 markTaskDone(indexToMarkDone);
            } else if (CommandType.ADD_DEADLINE.isMatchingInput(input)){
                Task incomingTask = parseAddDeadline(input);
                addTask(incomingTask);
            } else if (CommandType.ADD_EVENT.isMatchingInput(input)) {
                Task incomingTask = parseAddEvent(input);
                addTask(incomingTask);
            } else if (CommandType.ADD_TODO.isMatchingInput(input)) {
                Task incomingTask = parseToDo(input);
                addTask(incomingTask);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
