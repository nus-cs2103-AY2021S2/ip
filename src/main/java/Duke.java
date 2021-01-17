import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static List<Task> database = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Prints greeting
        printGreeting();

        // Ask for commands
        boolean isExiting = false;
        while (true) {
            String[] inputArr = br.readLine().split(" ");
            String command = inputArr[0];
            printHorizontalLine();
            switch (command) {
                case "list":
                    if (database.isEmpty()) {
                        pw.println("You do not have anything to do at the moment!");
                    } else {
                        pw.println("Here are the tasks in your list:");
                        for (Task task : database) {
                            pw.printf("%d.[%s] %s\n", task.getIndex(),
                                    (task.isDone() ? "X" : " "), task.getName());
                        }
                    }
                    break;
                case "done":
                    int index = Integer.parseInt(inputArr[1]);
                    Task task = database.get(index - 1);
                    task.completeTask();
                    pw.println("Nice! I've marked this task as done:");
                    pw.printf("  [X] %s\n", task.getName());
                    break;
                case "bye":
                    isExiting = true;
                    break;
                default:
                    String taskName = String.join(" ", inputArr);
                    Task newTask = new Task(taskName);
                    database.add(newTask);
                    printAddedTask(taskName);
            }
            if (isExiting) {
                break;
            }
            printHorizontalLine();
            pw.flush();
        }

        pw.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
        pw.close();
    }

    public static void printGreeting() {
        printHorizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        pw.printf("Hello! I'm \n%s\nWhat can I do for you?\n", logo);
        printHorizontalLine();
        pw.flush();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            pw.print('-');
        }
        pw.println();
    }

    public static void printAddedTask(String str) {
        pw.printf("Added task: %s\n", str);
    }
}

class Task {
    private static int count = 1;
    private int index;
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.index = count;
        count++;
        this.name = name;
        this.isCompleted = false;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isCompleted;
    }

    public void completeTask() {
        this.isCompleted = true;
    }
}
