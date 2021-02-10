import commands.Command;
import commands.ListCommand;
import exceptions.UnsupportedCommandException;
import format.Ui;
import tasklist.TaskList;

import java.io.IOException;
import java.util.Scanner;

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
    }




    /**
     * Entry point of the duke programme if run from terminal.
     * @param args Irrelevant argument
     */
    public static void main(String[] args) {

    }

    // javafx code adapted/taken from https://se-education.org/guides/tutorials/javaFxPart3.html

    /**
     * gui version of getting response based on userinput, instead of CLI scanner.
     * technically this getResponse method can be used within runDuke
     * todo this description
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        // parse command
        Command c = null;
        c = new ListCommand("");

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
        boolean needRewrite = true; // take this from command object instead
        if (needRewrite) {
            try {
                Storage.saveTasksList(taskList);
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        c.debug();
        return c.getCommandOutputMsg();
    }
}
