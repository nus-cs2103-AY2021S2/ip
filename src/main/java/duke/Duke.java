package duke;

import java.util.ArrayList;
//import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Duke takes and handles inputs from the user.
 *
 * @author  Nicole Ang
 */
public class Duke extends Application {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public void start(Stage stage) {
    }

    /**
     * Takes user inputted tasks and passes them to the TaskManager.
     *
     * @param input Input taken in through GUI.
     */
    public String getResponse(String input) {
        TaskManager taskManager = new TaskManager();
        return taskManager.takeEvent(input, tasks);
    }

    /**
     * Takes user inputted tasks and passes them to the TaskManager.
     *
     * @param args  Java command line inputs.
     */
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        // System.out.println("Hello! How can I help you?");

        // Scanner scanner = new Scanner(System.in);
        // tasks = new ArrayList<>();

        // while (scanner.hasNext()) {
        //    String input = scanner.nextLine();
        //    TaskManager taskManager = new TaskManager();

        //    if (input.equals("bye")) {
        //        System.out.println(LINE + "\n" + (char) 9 + (char) 9 + "Bye! See you soon :)\n" + LINE);
        //        scanner.close();
        //        break;
        //    } else {
        //        taskManager.takeEvent(input, tasks);
        //    }

        // }
        // return;
    }
}
