package prerthan.duke;

import prerthan.duke.IO.Input;
import prerthan.duke.IO.Output;
import prerthan.duke.IO.Storage;
import prerthan.duke.task.TaskList;

/**
 * 
 */
public class Duke {
    public static TaskList tasks;
    public static Input input;
    public static Output output;
    public static Storage fileRW;

    public boolean run() {
        return false;
    }

    /**
     * Runs the main program loop.
     * 
     * @return {@code false} when the user says 'bye'; otherwise, never returns.
     */
    public static boolean programLoop() {
        boolean exitLoop = false;
        String command;

        input.nextLine();
        return false;
    }

    /**
     * Cleans up objects and quits the program by calling {@link System#exit(int)}.
     */
    public static void exit() {
        output.sayGoodBye();

        input.close();
        output.close();

        System.exit(0);
    }

    public static void main(String[] args) {
        // Initialises file and UI I/O
        fileRW = new Storage("data", "duke.txt");
        input = new Input();
        output = new Output();

        // Greets the user.
        output.sayHello();

        // Creates the task list
        tasks = new TaskList();

        if (programLoop())
            exit();
    }
}
