import commands.Command;
import exceptions.UnsupportedCommandException;
import format.Ui;
import tasklist.TaskList;

import java.io.IOException;

// w6 - think about how to improve code quality here
public class Duke {
    private static TaskList taskList;
    private boolean hasExitCommandBeenSent;
    private Parser parser;

    /**
     * Initialise duke with a taskList and a parser.
     */
    public Duke() {
        this.setTaskList();
        this.setParser();
    }

    private void setParser() {
        this.parser = new Parser();
    }

    // both gui and cli need to do this
    public void setTaskList() {
        try {
            taskList = Storage.setupTaskList();
        } catch (IOException e) {
            Ui.print(new String[]{"Something went wrong in loading the task file and parsing", e.getMessage()});
            taskList = new TaskList();
        }
        // todo ensure a tasklist is always created?
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
