package duke;

import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static TaskList tasks = new TaskList();

    /*
     * Main class to handle the input
     *
     * @param args the arguments to the program
     */
    public static void main(String[] args) {
        Ui ui = new Ui(tasks, logo);
        Storage storage = new Storage("data/duke.txt");
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        storage.readTasks(tasks);

        ui.printStart();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parser.processLine(line, tasks);
            storage.saveTasks(tasks);
        }
    }




}
