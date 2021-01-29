package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;

/**
 * User interface of Duke.
 */
public class UI {

    // Set the desire length of a line.
    private static final int LENGTH_OF_LINE = 80;

    // Welcome page.
    private static final String welcome = " __________________________ \n"
            + "|  HI! THIS IS             |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  What can I do for you?  |\n"
            + "|__________________________|\n";

    // Goodbye page.
    private static final String bye = " __________________________ \n"
            + "|  GOOD BYE!               |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  Always be with you.     |\n"
            + "|__________________________|\n";

    // Underline.
    private static final String horizontalLine = StringParser.generateUnderline(LENGTH_OF_LINE);

    private static final Scanner sc = new Scanner(System.in);
    private static TaskList list = new TaskList();

    /**
     * Print DukeException.
     *
     * @param e DukeException.
     */
    private static void printError(DukeException e) {
        System.out.print(e.getMessage() + "\n");
    }

    /**
     * Loads the save data and greets.
     */
    private static void loadAndSayHello() {
        list = Storage.loadToList();
        System.out.println(welcome);
    }

    /**
     * Saves the data to save data file and says goodbye.
     */
    private static void saveAndGoodBye() {
        Storage.writeToData(list);
        System.out.println(bye);
    }

    /**
     * Main loop for Duke.
     */
    public static void mainLoop() {
        boolean isExit = false;
        String commandStr;
        Command command;

        try {
            Storage.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadAndSayHello();

        while (!isExit) {
            commandStr = sc.nextLine();

            System.out.print(horizontalLine);
            try {
                command = Parser.parseCommand(commandStr);
                command.executeAndPrint(list, LENGTH_OF_LINE);
                isExit = command.isExit();
                if (!isExit) {
                    System.out.print(horizontalLine);
                }
            } catch (DukeException e) {
                printError(e);
                System.out.print(horizontalLine);
            }
        }
        saveAndGoodBye();
    }

}
