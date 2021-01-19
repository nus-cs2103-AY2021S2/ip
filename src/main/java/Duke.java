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
            Parser parser = new Parser(input);
            String parsedCommand = parser.getParsedCommand();
            String taskName = parser.getTaskName();
            String additionals = parser.getAdditionals();
            Task thisTask;

            System.out.println(PARTING_LINE);
            try {
                Command command = Command.valueOf(parsedCommand);
                switch (command) {
                case LIST:
                    listTasks();
                    break;
                case DONE:
                    int index = Integer.parseInt(additionals) - 1;
                    markAsComplete(index);
                    break;
                case TODO:
                    thisTask = new Todo(taskName);
                    addThisTask(thisTask);
                    break;
                case DEADLINE:
                    thisTask = new Deadline(taskName, additionals);
                    addThisTask(thisTask);
                    break;
                case EVENT:
                    thisTask = new Event(taskName, additionals);
                    addThisTask(thisTask);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command!");
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

    public static void addThisTask(Task task) {
        System.out.println(" Added: ");
        tasks.add(task);
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }
}
