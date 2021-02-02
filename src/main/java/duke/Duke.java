package duke;

import java.util.Scanner;

public class Duke {
    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected final TaskList tasks;

    public Duke() {
        tasks = new TaskList();
    }

    /**
     * Runs the command line interface
     */
    public void run() {
        Ui ui = new Ui(tasks, DUKE_LOGO);
        Storage storage = new Storage("data/duke.txt");
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        storage.readTasks(tasks);

        ui.printStart();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parser.processLine(line, tasks);
            storage.saveTasks(tasks);
        }
    }

    /**
     * Main class to handle the input
     *
     * @param args the arguments to the program
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
