import java.io.IOException;

import commands.Command;
import exceptions.UnsupportedCommandException;
import format.Ui;
import tasklist.TaskList;

/**
 * Represents the Kiwi tasklist app. Named as Duke for legacy purposes.
 */
public class Duke {
    private static TaskList taskList;
    private boolean hasExitCommandBeenSent;
    private Parser parser;

    /**
     * Initialise duke with a taskList and a parser.
     */
    public Duke() {
        this.initTaskList();
        this.initParser();
    }

    private void initParser() {
        this.parser = new Parser();
    }

    /**
     * Initialise taskList for the app
     */
    private void initTaskList() {
        try {
            taskList = Storage.setupTaskList();
        } catch (IOException e) {
            Ui.print(new String[]{"Something went wrong in loading the task file and parsing", e.getMessage()});
            taskList = new TaskList();
        }
    }


    // javafx code adapted/taken from https://se-education.org/guides/tutorials/javaFxPart3.html


    /**
     * This is the main function that generates a response to user inputted commands and strings.
     *
     * @param input User input
     * @return Response after processing the command(s) and argument(s) from the user input
     */
    String getResponse(String input) {
        Command c;

        // parse command
        try {
            c = parser.parseInputLine(input);
        } catch (UnsupportedCommandException e) {
            return Ui.formatException(e.getMessage());
        }

        // run command
        assert c != null;
        c.run(taskList);

        // update exit signal boolean so javafx application can be exited
        this.hasExitCommandBeenSent = c.hasSentExitDukeSignal();

        // save task list to hard disk
        String errMsg = saveToHardDisk();

        // return output
        return c.getCommandOutputMsg() + errMsg;
    }

    private String saveToHardDisk() {
        try {
            Storage.saveTasksList(taskList);
            return "";
        } catch (IOException e) {
            return "\n" + e.getMessage();
        }
    }

    /**
     * Returns whether an exit command has been sent to duke.
     * @return
     */
    public boolean hasExitCommandBeenSent() {
        return hasExitCommandBeenSent;
    }
}
