package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * User interface of Duke.
 */
public class UI {

    // Welcome page.
    private static final String WELCOME = " __________________________ \n"
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
    private static final String BYE = " __________________________ \n"
            + "|  GOOD BYE!               |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  Always be with you.     |\n"
            + "|__________________________|\n";

    private static TaskList listT = new TaskList();
    private static PlaceList listP = new PlaceList();

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

        listT = Storage.loadToTaskList();
        listP = Storage.loadToPlaceList();
        return WELCOME;
    }

    /**
     * Saves the data to save data file and says goodbye.
     */
    public static String saveAndGoodBye() {
        Storage.writeToData(listT, listP);
        return BYE;
    }

    /**
     * Responds according to input string.
     *
     * @param commandStr Input command string.
     * @return Duke's respond.
     */
    public static String respond(String commandStr) {
        try {
            Command command = Parser.parseCommand(commandStr);
            return command.executeAndPrint(listT, listP);
        } catch (DukeException e) {
            return printError(e);
        }
    }

}
