import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    private static void printHorizontalLine() {
        printIndentOutput("_____________________________________________________");
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
            } else if (line.length() >= 5 && line.substring(0, 5).equals("done ")) {
                int index = Integer.parseInt(line.split(" ")[1]);
                Task curTask = tasks.get(index - 1);
                curTask.markAsDone();
                printIndentOutput("Nice! I've marked this task as done:");
                printIndentOutput("   " + curTask);
            } else {
                tasks.add(new Task(line));
                printIndentOutput("added: " + line);
            }

            printHorizontalLine();

            if (end) break;

            line = stdin.nextLine();
        }
    }
}
