import duke.CommandHandler;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;


/**
 * A CLI-based task management application that allows users
 * to add tasks, events and deadlines.
 *
 * @author Justin Gnoh
 * @since 2021-02-06
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private CommandHandler commandHandler;
    private Parser parser;

    /**
     * This object is created to encapsulate other objects
     * to facilitate Ui and storage paths.
     *
     * @param filePath This is the storage filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        commandHandler = new CommandHandler(ui, parser);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public String getIntroMessage() {
        return ui.getIntroMessage();
    }

    /**
     * This method returns a response based on user input
     *
     * @param input
     * @return This returns a response to user input
     */
    public String getResponse(String input) {

        String trimmedInput = input.trim();
        String command = parser.getCommand(trimmedInput);

        switch (command) {
        case "bye":
            storage.save(taskList);
            Platform.exit();

        case "list":
            return ui.printList(taskList);

        case "done":
            return commandHandler.processDone(input, taskList);

        case "todo":
            return commandHandler.processTodo(input, taskList);

        case "deadline":
            return commandHandler.processDeadline(input, taskList);

        case "event":
            return commandHandler.processEvent(input, taskList);

        case "delete":
            return commandHandler.processDelete(input, taskList);

        case "find":
            return commandHandler.processFind(input, taskList);

        case "help":
            return commandHandler.processHelp();

        case "snooze":
            return commandHandler.processSnooze(input, taskList);

        default:
            return ui.printUnknownCommand();

        }
    }
}
