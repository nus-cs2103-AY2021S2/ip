import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.out;

/**
 * Skeleton class for the Duke chatbox.
 */

public class Skeleton {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Skeleton() {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        storage.loadFile(tasks);
    }

    public static void main(String[] args) {
        Skeleton skeleton = new Skeleton();
        skeleton.run();
    }

    public String getResponse(String input) {
        String output = parser.readCommands(tasks, storage, input).toString();
        return "COMMAND RECEIVED!" + "\n" + output;
    }
}