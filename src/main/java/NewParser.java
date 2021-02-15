import commands.*;
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
//        assert false==true : "ha!";
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
    }

    private Command parseIntoCommand(String firstWord, String commandBody) throws UnsupportedCommandException {
        // w6 multiple cases --> abstract out all possible strings that correspond to one command later.
        // also allow users to define own aliases
        switch (firstWord) {
        case "list":
        case "ls":
            System.out.println(commandBody);
            return new ListCommand(commandBody);
        case "bye":
            return new ByeCommand(commandBody);
        case "todo":
            return new TodoCommand(commandBody);
        case "event":
        case "e":
            return new EventCommand(commandBody);
        case "deadline":
            return new DeadlineCommand(commandBody);
        case "done":
            return new DoneCommand(commandBody);
        case "delete":
        case "del":
            return new DeleteCommand(commandBody);
        case "find":
            return new FindCommand(commandBody);
        default:
            throw new UnsupportedCommandException();
        }
    }
}
