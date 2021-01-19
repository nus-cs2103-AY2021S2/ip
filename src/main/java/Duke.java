import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    private static void printHorizontalLine() {
        printIndentOutput("_____________________________________________________");
    }

    private static boolean checkMatchString(String line, String match) {
        return line.length() >= match.length() && line.substring(0, match.length()).equals(match);
    }

    private static void printTaskListStatus(ArrayList<Task> tasks, Task curTask) {
        printIndentOutput("Got it. I've added this task:");
        printIndentOutput("   " + curTask);
        printIndentOutput("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printHorizontalLine();
        printIndentOutput("What can I do for you?");
        printHorizontalLine();

        Scanner stdin = new Scanner(System.in);
        String line = stdin.nextLine();
        boolean end = false;

        while (line != null) {
            printHorizontalLine();

            if (line.equals("bye")) {
                printIndentOutput("Bye. Hope to see you again soon!");
                end = true;
            } else if (line.equals("list")) {
                printIndentOutput("Here are the tasks in you list:");
                for (int i = 0; i < tasks.size(); i++) {
                    printIndentOutput((i + 1) + ". " + tasks.get(i));
                }
            } else if (checkMatchString(line, "done ")) {
                int index = Integer.parseInt(line.split(" ", 2)[1]);
                Task curTask = tasks.get(index - 1);
                curTask.markAsDone();
                printIndentOutput("Nice! I've marked this task as done:");
                printIndentOutput("   " + curTask);
            } else if (checkMatchString(line, "todo ")) {
                String taskName = line.split(" ", 2)[1];
                Task curTask = new Todo(taskName);
                tasks.add(curTask);
                printTaskListStatus(tasks, curTask);
            } else if (checkMatchString(line, "deadline ")) {
                // TODO: Handle error
                String[] commandArgs = line.split(" ", 2)[1].split(" /by ", 2);
                String taskName = commandArgs[0];
                String deadline = commandArgs[1];
                Task curTask = new Deadline(taskName, deadline);
                tasks.add(curTask);
                printTaskListStatus(tasks, curTask);
            } else if (checkMatchString(line, "event ")) {
                String[] commandArgs = line.split(" ", 2)[1].split(" /at ", 2);
                String taskName = commandArgs[0];
                String date = commandArgs[1];
                Task curTask = new Event(taskName, date);
                tasks.add(curTask);
                printTaskListStatus(tasks, curTask);
            } else {
                Task curTask = new Task(line);
                tasks.add(new Task(line));
                printTaskListStatus(tasks, curTask);
            }

            printHorizontalLine();

            if (end) break;

            line = stdin.nextLine();
        }
    }
}
