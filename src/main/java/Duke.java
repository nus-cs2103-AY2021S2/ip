

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

/**
 * Start point of Duke
 * Initializes DUke and starts the interaction with user
 */
public class Duke {
    private DukeList list;
    private final UI ui;
    private final Storage storage;

    public static void main(String[] args)  {
       new Duke("data/duke.txt").run();
    }
    public Duke(String filePath)  {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            list = new DukeList(storage.load());
        } catch (FileNotFoundException e) {
            list = new DukeList();
        }
    }

    /**
     * Runs the program until termination is called
     */
    public void run()  {
        ui.WelcomeMessage();
        ui.showDivider();
        while (!ui.getIsExit()) {
            try {
                String fullCommand = ui.readCommand();
                ui.commandMessage(Parser.parse(fullCommand, list), list);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMissingArgsError();
            } catch (NumberFormatException | DateTimeParseException | IndexOutOfBoundsException e) {
                ui.showWrongArgsError();
            }
            ui.showDivider();
        }
        storage.save(list);
        ui.GoodByeMessage();
    }
}
