import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String PARTING_LINE = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printGreeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(PARTING_LINE);
            if (input.equals("list")) {
                listTasks();
            } else if (isDoneCommand(input)){
                int index = Character.getNumericValue(input.charAt(5)) - 1;
                markAsComplete(index);
            } else if (isTask(input)) {
                addThisTask(input);
            }
            System.out.println(PARTING_LINE);
            input = sc.nextLine();
        }
        farewell();
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(PARTING_LINE);
        System.out.println("Sup. I am Duke.");
        System.out.println("What do you want?");
        System.out.println(PARTING_LINE);
    }

    public static void farewell() {
        System.out.println(PARTING_LINE);
        System.out.println(" See you.");
        System.out.println(PARTING_LINE);
    }

    public static void listTasks() {
        System.out.println(" Here are the tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
    }

    public static void markAsComplete(int index) {
        if (index < tasks.size()) {
            Task completedTask = tasks.get(index);
            completedTask.complete();
            System.out.println(" Marked. How cool is that?");
            System.out.println("  " + completedTask);
        }
    }

    public static void addThisTask(String input) {
        System.out.println(" Added: ");
        Task thisTask;
        String name;
        if (isTodo(input)) {
            name = input.substring(5);
            thisTask = new Todo(name);
        } else if (isDeadline(input)) {
            int byIndex = input.indexOf(" /by ");
            name = input.substring(9, byIndex);
            String ddl = input.substring(byIndex + 5);
            thisTask = new Deadline(name, ddl);
        } else {
            int atIndex = input.indexOf(" /at ");
            name = input.substring(6, atIndex);
            String date = input.substring(atIndex + 5);
            thisTask = new Event(name, date);
        }
        tasks.add(thisTask);
        System.out.println("  " + thisTask);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    /**
     * This method judges whether input string is a valid
     * command that contains keyword "done".
     * @param input is the string to be tested
     * @return true if the input is valid, false otherwise
     */
    public static boolean isDoneCommand(String input) {
        return (input.length() > 5 && input.substring(0, 5).equals("done ")
                && Character.isDigit(input.charAt(5)));
    }

    public static boolean isTask(String input) {
        return (isTodo(input) || isDeadline(input) || isEvent(input));
    }

    public static boolean isTodo(String input) {
        return (input.length() > 5 && input.substring(0, 5).equals("todo "));
    }

    public static boolean isDeadline(String input) {
        return (input.length() > 9 && input.substring(0, 9).equals("deadline "));
    }

    public static boolean isEvent(String input) {
        return (input.length() > 6 && input.substring(0, 6).equals("event "));
    }
}
