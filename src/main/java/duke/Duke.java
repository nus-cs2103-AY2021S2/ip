package duke;

import java.io.IOException;
import java.text.ParseException;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;


public class Duke {
    public static final String FILE_DIR = "data";
    public static final String FILE_NAME = "duke.txt";
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;
    private boolean isTerminated;

    /**
     * Constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_DIR, FILE_NAME);
        isTerminated = false;
    }
    public boolean getIsTerminated() {
        return isTerminated;
    }

    /**
     * Handle Startup Procedure, which includes messages and loading from file.
     *
     * @return dialog for startup.
     */
    public String startUpProcedure() {
        ui.printStartUp();
        try {
            ui.printLoadStart();
            taskList = storage.loadTaskList();
            ui.printLoadSuccess();
        } catch (IOException e) {
            ui.printLoadFail();
        }
        return ui.flushMessage();
    }

    /**
     * Checks whether the command is a termination command.
     *
     * @param c Command to check
     * @return Whether the command is a termination command
     */
    private boolean isCommandTerminate(Command c) {
        return c == null;
    }

    /**
     * The main application loop
     *
     * @param line The input to be processed
     * @return Whether we can process new inputs
     */
    public String processInput(String line) {
        try {
            Command c = Parser.parse(line);
            if (isCommandTerminate(c)) { //Bye command
                handleTermination();
            } else {
                updateAndGenerateOutput(c);
            }
        } catch (ParseException e) {
            ui.handleParsingError(e);
        } catch (InvalidCommandException e) {
            ui.handleInvalidCommand(e);
        } catch (EmptyArgumentException e) {
            ui.handleEmptyArgument(e);
        } catch (BadDateArgumentException e) {
            ui.handleBadDate(e);
        } finally {
            saveIfNeeded();
        }
        return ui.flushMessage();
    }
    private void updateAndGenerateOutput(Command c)
            throws EmptyArgumentException, BadDateArgumentException, InvalidCommandException {
        String data = taskList.run(c);
        ui.printCommandMessage(c, data);
    }

    /**
     * Saves the content of taskList if it has been changed by the command
     */
    private void saveIfNeeded() {
        if (taskList.isEdited()) {
            try {
                storage.saveTaskList(taskList);
                taskList.markSaved();
            } catch (IOException e) {
                ui.dumpState(taskList);
            }
        }
    }

    /**
     * Handle Termination, including private variables and messages.
     */
    private void handleTermination() {
        isTerminated = true;
        ui.generateShutDownMessage();
    }
}
