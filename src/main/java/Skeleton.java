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
    private UI ui;
    private Parser parser;

    public Skeleton() {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.ui = new UI();
        this.parser = new Parser();
    }

    public void run() {
        //ui.greeting();
        storage.loadFile(tasks);
        //parser.readCommands(tasks, storage);
    }

    public static void main(String[] args) {
        Skeleton skeleton = new Skeleton();
        skeleton.run();
    }

    public String getResponse(String input) {
        String output = parser.readCommands(tasks, storage, input);
        return "COMMAND RECEIVED! + \n" + output;
    }
}