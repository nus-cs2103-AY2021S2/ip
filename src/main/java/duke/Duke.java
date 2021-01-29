package duke;

import java.util.Scanner;

/**
 * Driver program for Duke.
 */
public class Duke {

    /**
     * Run function for Duke.
     * @param parser parser to parse user commands
     * @param sc a scanner
     */
    static void run(Parser parser, Scanner sc) {
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            parser.parse(command);
            if (command.equals("bye")) {
                break;
            }
        }
    }

    /**
     * The main driver function.
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        Ui.greet();
        run(new Parser(taskList), sc);
    }
}
