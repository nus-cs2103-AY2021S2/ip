import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static String PARTING_LINE = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String FILE_PATH = System.getProperty("user.dir") + "/duke.txt";

    public static void main(String[] args) {
        printGreeting();

        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver) {
            String input = sc.nextLine();
            System.out.println(PARTING_LINE);
            try {
                Command command = Parser.parseCommand(input);
                switch (command) {
                case LIST:
                    listTasks();
                    break;
                case DONE:
                    markAsComplete(Parser.getDoneIndex(input));
                    break;
                case TODO:
                    addThisTask(Parser.getTodo(input));
                    break;
                case DEADLINE:
                    addThisTask(Parser.getDeadline(input));
                    break;
                case EVENT:
                    addThisTask(Parser.getEvent(input));
                    break;
                case DELETE:
                    removeTask(Parser.getDeleteIndex(input));
                    break;
                case BYE:
                    isOver = true;
                    farewell();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(PARTING_LINE);
        }
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
        System.out.println("How can I help you?");
        System.out.println(PARTING_LINE);
    }

    public static void farewell() {
        System.out.println(" See you.");
    }

    public static void listTasks() {
        System.out.println(" Here are the tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + tempTask);
        }
    }

    public static void markAsComplete(int index) throws TaskIndexOutOfBoundException {
        if (index < tasks.size()) {
            Task completedTask = tasks.get(index);
            completedTask.complete();
            updateTasks();
            System.out.println(" Marked. How cool is that?");
            System.out.println("  " + completedTask);
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }

    public static void removeTask(int index) throws TaskIndexOutOfBoundException {
        if (index < tasks.size()) {
            Task removingTask = tasks.get(index);
            tasks.remove(index);
            updateTasks();
            System.out.println(" Following task is removed:");
            System.out.println("  " + removingTask);
            System.out.println(" Now you have " + tasks.size() + " tasks.");
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }

    public static void addThisTask(Task task) {
        System.out.println(" Added: ");
        tasks.add(task);
        updateTasks();
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    public static void updateTasks() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("error occurred when opening:" + e.getMessage());
        }
    }
}
