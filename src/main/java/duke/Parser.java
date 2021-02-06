package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.exception.DukeInsufficientParametersException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidParametersException;
import duke.exception.DukeMissingFlagException;
import duke.exception.DukeUnknownCommandException;

public class Parser {
    private static String[] acceptedCommands = {
        "todo", "deadline", "event", "bye", "list", "done", "delete", "find"
    };

    private static boolean verifyCommand(String c) {
        boolean found = false;
        for (String cmd : acceptedCommands) {
            if (c.equals(cmd)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private static LocalDate processDate(String dateString) throws DukeInvalidDateException {
        assert dateString.length() > 0 : "Empty Date String";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException();
        }
    }

    private static String[] extractFlag(String c, String s, String flag) throws DukeMissingFlagException {
        String[] output = s.split(" " + flag + " ");
        if (output.length < 2) {
            throw new DukeMissingFlagException(c, flag);
        }
        return output;
    }

    /**
     *  Command parser.
     *
     *  @param c Command to be parsed.
     *  @return Command object.
     *  @throws DukeException Exception depending on error.
     */
    public static Command parse(String c) throws DukeException {
        String[] params = c.strip().split(" ", 2);

        //command integrity verification
        if (!verifyCommand(params[0])) {
            throw new DukeUnknownCommandException();
        }

        //zero-param commands
        if (params[0].equals("bye")) {
            return new ExitCommand();
        } else if (params[0].equals("list")) {
            return new ListCommand();
        }

        //command integrity verification - parameter check
        if (params.length < 2) {
            throw new DukeInsufficientParametersException(params[0]);
        }

        //non-zero-param commands
        try {
            String[] args;
            LocalDate date;

            switch (params[0]) {
            case "done":
                return new DoneCommand(Integer.parseInt(params[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(params[1]));
            case "todo":
                return new ToDoCommand(params[1]);
            case "deadline":
                args = extractFlag(params[0], params[1], "/by");
                assert args.length > 1 : "Broken splitting";
                date = processDate(args[1]);
                return new DeadlineCommand(args[0], date);
            case "event":
                args = extractFlag(params[0], params[1], "/at");
                assert args.length > 1 : "Broken splitting";
                date = processDate(args[1]);
                return new EventCommand(args[0], date);
            case "find":
                return new FindCommand(params[1]);
            default:
                throw new DukeUnknownCommandException();
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeInvalidParametersException();
        }
    }

}
