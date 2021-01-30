package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;

/**
 * User interface of Duke.
 */
public class UI {

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

    private static final Scanner sc = new Scanner(System.in);
    static TaskList list = new TaskList();

    /**
     * Print DukeException.
     *
     * @param e DukeException.
     */
    static String printError(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Loads the save data and greets.
     */
    public static String loadAndSayHello() {

        try {
            Storage.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        list = Storage.loadToList();
        return welcome;
    }

    /**
     * Saves the data to save data file and says goodbye.
     */
    public static String saveAndGoodBye() {
        Storage.writeToData(list);
        return bye;
    }

    public static String respond(String commandStr) {
        try {
            Command command = Parser.parseCommand(commandStr);
            return command.executeAndPrint(list);
        } catch (DukeException e) {
            return printError(e);
        }
    }

}
