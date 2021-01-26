package duke;

import duke.command.*;
import duke.exception.*;

import java.util.Collections;
import java.util.HashSet;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] commandStr = input.trim().split("\\s+");
        String taskCommand = commandStr[0];
        Command commandType = null;
        String taskDetails = formatInput(taskCommand, input);

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
        case "deadline":
        case "event":
            commandType = new AddTask(taskCommand, taskDetails);
            break;
        case "delete":
            commandType = new DeleteTask(taskCommand, Integer.parseInt(commandStr[1]));
            break;
        case "find":
            commandType = new FindCommand(taskCommand, taskDetails);
            break;
        default:
            break;
        }
        return commandType;
    }

     private static String formatInput(String taskCommand, String input)  throws DukeException {
         String taskDetails = "";

         if (!taskCommand.equals("bye") && !taskCommand.equals("list")) {
             checkValidInput(taskCommand);
             taskDetails = formatTaskDetails(taskCommand, input);
             checkBlankDescription(taskCommand, taskDetails);
         }
         return taskDetails;
     }

    private static String formatTaskDetails(String taskCommand, String input) {
        input = input.replaceFirst(taskCommand + " ", "");
        String taskDetails = "";

        if (taskCommand.equals("event")) {
            taskDetails = input.split(" /at")[0].replaceFirst("event ", "");
        } else if (taskCommand.equals("deadline")) {
            taskDetails = input.split(" /by")[0].replaceFirst("deadline ", "");
        } else {
            taskDetails = input;
        }
        return taskDetails;
    }

    private static void checkValidInput(String taskCommand) throws DukeException {
        HashSet<String> validInputSet = new HashSet<>();
        Collections.addAll(validInputSet, "bye", "list", "done",
                "delete", "todo", "event", "deadline", "find");
        if(!validInputSet.contains(taskCommand)) {
            throw new DukeException(ExceptionType.INVALID_INPUT, taskCommand);
        }
    }

    private static void checkBlankDescription(String taskCommand, String taskDetails) throws DukeException {
        if(taskCommand.equals(taskDetails)) {
            throw new DukeException(ExceptionType.BLANK_DESCRIPTION, taskCommand);
        }
    }
}