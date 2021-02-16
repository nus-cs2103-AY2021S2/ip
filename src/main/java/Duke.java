import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class Duke {

    private final UI ui;
    private final Storage storage;
    private DukeList list;

    public Duke()  {
        ui = new UI();
        storage = new Storage("data/duke.txt");
        try {
            list = new DukeList(storage.load());
        } catch (FileNotFoundException e) {
            list = new DukeList();
        }
    }

    /**
     * Takes in a user input and output a response
     * @param input user input
     * @return Response String
     */
    public String getResponse(String input) {

        if (input.equals("bye")) {
            storage.save(list);
            return ui.showGoodByeMessage();
        } else {
            try {
                return ui.commandMessage(Parser.parse(input, list), list);
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showMissingArgsError();
            } catch (NumberFormatException | DateTimeParseException | IndexOutOfBoundsException e) {
                return ui.showWrongArgsError();
            }
        }
    }

    /**
     * Shows the welcome message on startup
     * @return welcome message String
     */
    public static String welcomeMessage() {
        return new UI().showWelcomeMessage();
    }
}
