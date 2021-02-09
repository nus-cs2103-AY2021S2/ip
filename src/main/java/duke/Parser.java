package duke;

import duke.command.*;
import duke.exception.DukeCommandException;
import duke.exception.DukeToDoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Parser {


    /**
     * Parses a userInput and outputs a command for execution
     * @param userInput user's input
     * @return Different Command depending on input
     */
    public static Command parseCommand(String userInput) throws DukeToDoException, DukeCommandException {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];

        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            return parseDone(splitInput[1]);
        } else if (command.equals("delete")) {
            return parseDelete(splitInput[1]);
        } else if (command.equals("find")) {
            return parseFind(splitInput[1]);
        }else if (command.equals("remind")) {
            return parseRemind(userInput);
        }else if (isValidTaskCommand(command)) {
            return parseTask(userInput);
        } else {
            throw new DukeCommandException("Sorry Human. I do not comprehend.");
        }

    }

    public static boolean isValidTaskCommand(String command) {
        return command.equals("event") || command.equals("deadline") || command.equals("todo");
    }

    /**
     * Parse the command args in the context of Done
     * @param arg user's input without the command part
     * @return DoneCommand
     */
    public static Command parseDone(String arg) {
        int idxDone = Integer.parseInt(arg) - 1;
        return new DoneCommand(idxDone);
    }

    /**
     * Parse the command args in the context of Delete
     * @param arg user's input without the command part
     * @return DeleteCommand
     */
    public static Command parseDelete(String arg) {
        int idxDel = Integer.parseInt(arg) - 1;
        return new DeleteCommand(idxDel);
    }

    /**
     * Parse the command args in the context of Find
     * @param keyword keyword to search the task description for
     * @return FindCommand
     */
    public static Command parseFind(String keyword) {
        return new FindCommand(keyword);
    }

    /**
     * Parse the command args in the context of Remind
     * @param input user's input
     * @return FindCommand
     * @throws DukeCommandException exceptions in user command inputs for remind
     */
    public static Command parseRemind(String input) throws DukeCommandException {
        String[] splitInput = input.split(" ");
        switch (splitInput.length) {
        case 1:
            return new RemindCommand();
        case 2:
            try {
                int numDays = Integer.parseInt(splitInput[1]);
                return new RemindCommand(numDays);
            } catch (NumberFormatException e) {
                throw new DukeCommandException("Only give numbers for argument of remind");
            }
        default:
            throw new DukeCommandException("Give the command in the correct format below:\nremind <number>");
        }
    }


    /**
     * Parse the command in the context of Task
     * @param input user's input
     * @return TaskCommand
     */
    public static Command parseTask(String input) throws DukeToDoException, DukeCommandException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];

        switch (command) {
        case "todo":
            if(splitInput.length == 1) {
                throw new DukeToDoException("The description of a todo cannot be empty.");
            }
            String taskDescription = splitInput[1].trim();
            return new TaskCommand(command, taskDescription);

        case "deadline":
            assert splitInput[1].contains("/by") : "Missing keyword '/by'";
            String[] taskDetails = splitInput[1].split("/by");
            taskDescription = taskDetails[0].trim();
            LocalDateTime endTime =  LocalDateTime.parse(taskDetails[1].trim(),
                    dateTimeFormatter);
            return new TaskCommand(command, taskDescription, endTime);

        case "event":
            assert splitInput[1].contains("/at") : "Missing keyword '/at'";
            taskDetails = splitInput[1].split("/at");
            taskDescription = taskDetails[0].trim();
            LocalDateTime eventTime =  LocalDateTime.parse(taskDetails[1].trim(),
                    dateTimeFormatter);
            return new TaskCommand(command, taskDescription, eventTime);

        default:
            throw new DukeCommandException("Invalid Command.");
        }
    }
}
