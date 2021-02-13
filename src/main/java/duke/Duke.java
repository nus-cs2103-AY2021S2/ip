package duke;

import java.io.IOException;
import java.text.ParseException;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;


public class Duke {
    private Ui ui;
    private TaskList taskList;
    private boolean isTerminated;

    /**
     * Constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        isTerminated = false;
    }
    public boolean getIsTerminated() {
        return isTerminated;
    }

    /**
     * Handle startup Procedure //TODO: Give better comment
     *
     * @return dialog for startup.
     */
    public String startUpProcedure() {
        ui.printStartUp();
        try {
            ui.printLoadStart();
            taskList = Storage.loadTaskList();
            ui.printLoadSuccess();
        } catch (IOException e) {
            ui.printLoadFail();
        }
        return ui.flushMessage();
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
            if (c == null) { //Bye command
                handleTermination();
                return ui.flushMessage();
            }
            String data = taskList.run(c);
            ui.printCommandMessage(c, data);
        } catch (ParseException e) {
            ui.handleException(e);
        } catch (InvalidCommandException e) {
            ui.handleException(e);
        } catch (EmptyArgumentException e) {
            ui.handleException(e);
        } catch (BadDateArgumentException e) {
            ui.handleException(e);
        } finally {
            if (taskList.isEdited()) {
                try {
                    Storage.saveTaskList(taskList);
                    taskList.markSaved();
                } catch (IOException e) {
                    ui.dumpState(taskList);
                }
            }
        }
        return ui.flushMessage();
    }

    /**
     * Handle Termination //TODO: Fix this javadoc properly
     */
    private void handleTermination() {
        isTerminated = true;
        ui.generateShutDownMessage();
    }
}
