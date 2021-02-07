import commands.ByeCommand;
import commands.Command;
import commands.ListCommand;
import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;
import exceptions.UnsupportedCommandException;
import format.Ui;
import tasklist.TaskList;

import java.io.IOException;

public class NewParser {

    private final TaskList taskList;

    public NewParser(TaskList taskListToWriteInto) {
        this.taskList = taskListToWriteInto;
    }

    /**
     * Main driver for parsing any user input
     * @param userInput User input from terminal
     * @return returns whether to keep scanner open
     */
    public Command parseInputLine(String userInput) throws UnsupportedCommandException {

        // SETTING UP THE VARIABLES NEEDED FOR ERROR CHECKING / PARSING
        int firstSpaceIndex = userInput.indexOf(" "); // todo can consider using split(" ", 2)?
        String firstWord;
        String commandBody;

        if (firstSpaceIndex == -1) {
            firstWord = userInput.trim();
            commandBody = "";
        } else {
            firstWord = userInput.substring(0, firstSpaceIndex);
            commandBody = userInput.substring(firstSpaceIndex).trim();
        }

        try {
            return parseIntoCommand(firstWord, commandBody);
        } catch (UnsupportedCommandException e) {
            throw e;
        }

        /*

        // ERROR CHECKING AND PROCESSING DIFFERENT COMMANDS
        try {
            // check for commands that are invalid on an empty task list
            checkInvalidOnEmptyList(firstWord);

            // if there's only one arg, this function handles whether the one arg is valid
            // and responds accordingly, or whether it's invalid and prints out the correct
            // error messages
            if (firstSpaceIndex == -1) {
                return checkAndHandleIfOneArgIsValid(firstWord);
            }

            // check if more than one arg provided for commands that require only one
            checkIfTooManyArgs(firstWord);

            // just enough args
            parseNextArgs(firstWord, userInput, firstSpaceIndex);

            // UPON SUCCESSFUL COMMAND EXECUTION
            // save tasksFile
            // todo still saveTasksList in parser?
            Storage.saveTasksList(taskList);

        } catch (UnsupportedCommandException | InvalidArgumentException | MissingArgumentException e) {
            // is it better to detect unsupported first command earlier?
            // currently being detected at the end of many if blocks
            // maybe should save list of supported commands in another file (String[]{todo, deadline, event}
            Ui.printException(e.getMessage());
            return true;
        } catch (IOException e) {
            // todo see if you can offer better help
            Ui.print(new String[]{"Oops, error occurred in saving the file.", e.getMessage()});
            return true;
        } catch (Exception e) {
            String errMsg = "didn't expect this exception " + e;
            Ui.printException(errMsg);
            return true;
        }

        return true;

         */
    }

    private Command parseIntoCommand(String firstWord, String commandBody) throws UnsupportedCommandException {
        switch (firstWord) {
        case "list":
            return new ListCommand(commandBody);
        case "bye":
            return new ByeCommand(commandBody);
        default:
            throw new UnsupportedCommandException();
        }
    }
}
