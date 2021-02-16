package duke;

import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDesException;
import duke.handler.ByeHandler;
import duke.handler.CommandHandler;
import duke.handler.DeleteHandler;
import duke.handler.DoneHandler;
import duke.handler.Parser;
import duke.tasks.TaskList;


/**
 * Main class for Duke.
 */
public class Duke {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" d.MMM.yyyy HH:mm");
    private static final String STORAGE_PATH = "data/tasks.txt";

    private boolean isExit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor taking in String storagePath.
     * @param storagePath path to the storage of data.
     */
    public Duke(String storagePath) {
        storage = new Storage(storagePath);
        tasks = storage.load();
        ui = new Ui();
    }

    /**
     * Returns the response given an input.
     * @param input The user input.
     * @return The respective response.
     */
    public String getResponse(String input) {
        boolean toString = true;
        try {
            CommandHandler commandHandler = Parser.parseFromInput(input);
            checkDeleteDonePossible(commandHandler, tasks);
            String output = commandHandler.execute(ui, storage, tasks);
            if (commandHandler instanceof ByeHandler) {
                isExit = true;
            }
            return output;
        } catch (DukeException e) {
            String errorOutput = e.getMessage();
            return errorOutput;
        }
    }

    /**
     * Checks if Done or Delete command an be called based on
     * the number of tasks available, and the parsed request.
     * @param commandHandler commandHandler of Done or Delete, with task index.
     * @param taskList list of tasks.
     * @throws DukeInvalidDesException If command task number > number of tasks.
     */
    public static void checkDeleteDonePossible(CommandHandler commandHandler, TaskList taskList)
            throws DukeInvalidDesException {
        if (commandHandler instanceof DoneHandler) {
            if (((DoneHandler) commandHandler).getTaskNum() > taskList.getNumOfTasks()) {
                throw new DukeInvalidDesException("DONE");
            }
        } else if (commandHandler instanceof DeleteHandler) {
            if (((DeleteHandler) commandHandler).getTaskNum() > taskList.getNumOfTasks()) {
                throw new DukeInvalidDesException("DELETE");
            }
        }
    }

    /**
     * Returns the boolean value of whether program is to exit.
     * @return true if ready to exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
