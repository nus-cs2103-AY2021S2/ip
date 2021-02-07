package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDesException;
import duke.handler.*;
import duke.tasks.TaskList;


import java.io.IOException;
import java.time.format.DateTimeFormatter;


/**
 * Main class for Duke.
 */
public class Duke {
    private boolean willExit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" d.MMM.yyyy HH:mm");

    /**
     * Class constructor taking in String storagePath.
     * @param storagePath path to the storage of data.
     */
    private Duke(String storagePath) {
        storage = new Storage(storagePath);
        tasks = storage.load();
        ui = new Ui();
    }

    /**
     * Main method of Duke program.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the main logic of Duke program.
     */
    public void run() {
        ui.displayIntro();
        while (willExit == false) {
            try {
                CommandHandler commandHandler = Parser.parseFromInput(ui.readCommand());
                checkDeleteDonePossible(commandHandler, tasks);
                commandHandler.execute(ui, storage, tasks);
                if (commandHandler instanceof ByeHandler) {
                    willExit = true;
                }
            } catch (DukeException e) {
                String output = e.getMessage();
                ui.respond(output);
            }
        }
        ui.close();
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
}