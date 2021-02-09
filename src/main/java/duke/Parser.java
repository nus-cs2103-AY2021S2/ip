package duke;

import java.util.Collections;
import java.util.HashSet;

import duke.command.AddTask;
import duke.command.Command;
import duke.command.DeleteTask;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.ExceptionType;

/**
 * Parser class which translates input of user to task command, task description and date
 */
public class Parser {

    /**
     * Decipher command based on the input of the user
     *
     * @param input Input provided by the user
     * @return Command to be executed by the Duke Bot
     * @throws DukeException If the user gives an empty description (except bye and list commands) or an invalid input
     */
    public static Command parse(String input) throws DukeException {
        String[] commandStr = input.trim().split("\\s+");
        String taskCommand = commandStr[0];
        String taskDetail = formatInput(taskCommand, input);
        Command commandType = handleNewCommand(taskCommand, commandStr, taskDetail);
        return commandType;
    }

    private static Command handleNewCommand(String taskCommand, String[] commandStr, String taskDetail) {
        Command commandType = null;

        switch (taskCommand) {
        case "bye":
            commandType = new ExitCommand(taskCommand);
            break;
        case "list":
            commandType = new ListCommand(taskCommand);
            break;
        case "done":
            commandType = new DoneCommand(taskCommand, Integer.parseInt(commandStr[1]));
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            commandType = new AddTask(taskCommand, taskDetail);
            break;
        case "delete":
            commandType = new DeleteTask(taskCommand, Integer.parseInt(commandStr[1]));
            break;
        case "find":
            commandType = new FindCommand(taskCommand, taskDetail);
            break;
        default:
            break;
        }
        return commandType;
    }

    private static String formatInput(String taskCommand, String input) throws DukeException {
        String taskDetail = "";
        boolean haveDescription = !taskCommand.equals("bye") && !taskCommand.equals("list");

        if (haveDescription) {
            checkValidInput(taskCommand);
            taskDetail = formatTaskDetail(taskCommand, input);
            checkBlankDescription(taskCommand, taskDetail);
        }
        return taskDetail;
    }

    private static String formatTaskDetail(String taskCommand, String input) {
        String taskDetail = input.replaceFirst(taskCommand + " ", "");
        return taskDetail;
    }

    private static void checkValidInput(String taskCommand) throws DukeException {
        HashSet<String> validInputSet = new HashSet<>();

        Collections.addAll(validInputSet, "bye", "list", "done",
                "delete", "todo", "event", "deadline", "find");

        boolean isInvalidCommand = !validInputSet.contains(taskCommand);
        if (isInvalidCommand) {
            throw new DukeException(ExceptionType.INVALID_INPUT, taskCommand);
        }
    }

    private static void checkBlankDescription(String taskCommand, String taskDetail) throws DukeException {
        boolean isBlankDescription = taskCommand.equals(taskDetail);

        if (isBlankDescription) {
            throw new DukeException(ExceptionType.BLANK_DESCRIPTION, taskCommand);
        }
    }
}
