package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * User interface of Duke.
 */
public class UI {

    // Welcome page.
    private static final String WELCOME = " __________________________ " + System.lineSeparator()
            + "| HI! THIS IS DUKE.          |" + System.lineSeparator()
            + "| What can I do for you?  |" + System.lineSeparator()
            + "|__________________________|" + System.lineSeparator();

    // Goodbye page.
    private static final String BYE = " __________________________ " + System.lineSeparator()
            + "|  GOOD BYE!                   |" + System.lineSeparator()
            + "|  Duke always be with you. |" + System.lineSeparator()
            + "|__________________________|" + System.lineSeparator();

    private static TaskList listT = new TaskList();
    private static PlaceList listP = new PlaceList();

    /**
     * Print DukeException.
     *
     * @param e DukeException.
     */
    static String printError(DukeException e) {
        return e.getMessage() + System.lineSeparator();
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
