package duke;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static ArrayList<Task> list;
    public static final String LINE = (char) 9 + "--------------------------------------------------------------------";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! How can I help you?");

        Scanner scanner = new Scanner(System.in);
        list  = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            TaskManager taskManager = new TaskManager();

            if (input.equals("bye")) {
                System.out.println(LINE + "\n" + (char) 9 + (char) 9 + "Bye! See you soon :)\n" + LINE);
                scanner.close();
                break;
            } else {
                taskManager.takeEvent(input, list);
            }

        }
    }
}
