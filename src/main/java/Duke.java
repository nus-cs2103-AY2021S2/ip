import commands.Command;
import exceptions.UnsupportedCommandException;
import format.Ui;
import tasklist.TaskList;

import java.io.IOException;

// w6 - think about how to improve code quality here
public class Duke {
    private static TaskList taskList;
    private boolean hasExitCommandBeenSent;
    private NewParser newParser;
    // storage location should be here

    // assume default storage/tasklist settings if no arguments given to constructor
    public Duke() {
        this.setTaskList();
        this.setParser();
    }

    private void setParser() {
        this.newParser = new NewParser(taskList);
    }

    // both gui and cli need to do this
    public void setTaskList() {
        try {
            taskList = Storage.setupTaskList();
        } catch (IOException e) {
            Ui.print(new String[]{"Something went wrong in loading the task file and parsing",
                    e.getMessage()});
        }
        // todo ensure a tasklist is always created?
    }


    // javafx code adapted/taken from https://se-education.org/guides/tutorials/javaFxPart3.html


    /**
     * This is the main function that generates a response to user inputted commands and strings.
     * Todo has only been tested with gui, not with cli.
     * @param input User input
     * @return Response after processing the command(s) and argument(s) from the user input
     */
    String getResponse(String input) {
        Command c;

        // parse command
        try {
            c = newParser.parseInputLine(input);
        } catch (UnsupportedCommandException e) {
            return e.getMessage();
        }

        // run command
        assert c != null;
        c.run(taskList);

        // update exit signal boolean so duke knows to close scanner/gui
        if (c.hasSentExitDukeSignal()) {
            this.hasExitCommandBeenSent = true;
            // todo how to exit gui and where
        }

        // save to hard disk - think about how to OOP saving behaviour later
        boolean needRewrite = true; // todo take this from command object instead
        if (needRewrite) {
            try {
                Storage.saveTasksList(taskList);
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        // c.debug();
        return c.getCommandOutputMsg();
    }

    public boolean hasExitCommandBeenSent() {
        return hasExitCommandBeenSent;
    }
}
