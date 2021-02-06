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
    private static String[] intervalFlags = {
            "/year", "/month", "/day"
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

    private static boolean isFlagInArgument(String arg, String[] flags) {
        for (String flag: flags) {
            if (arg.contains(flag)) {
                return true;
            }
        }
        return false;
    }

    private static String[] extractFlagFromArgument(String arg, String[] flags) {
        String[] output = new String[3];
        for (String flag : flags) {
            if (!arg.contains(flag)) {
                continue;
            }
            String[] data = arg.split(" " + flag + " ", 2);
            String beforeFlag = data[0];
            String afterFlag = data[1];

            return new String[] {beforeFlag, afterFlag, flag};
        }
        return output;
    }

    private static String[] extractOptionalFlags(String[] args, String[] optionalFlags) {
        //[name, date, instances, interval, type]
        String name;
        String date;
        String instance = null;
        String interval = null;
        String type = null;

        if (isFlagInArgument(args[0], optionalFlags)) {
            String[] temp = extractFlagFromArgument(args[0], optionalFlags);

            name = temp[0];
            date = args[1];
            instance = temp[1].split(" ")[0];
            interval = temp[1].split(" ")[1];
            type = temp[2];

        } else if (isFlagInArgument(args[1], optionalFlags)) {
            String[] temp = extractFlagFromArgument(args[1], optionalFlags);

            name = args[0];
            date = temp[0];
            instance = temp[1].split(" ")[0];
            interval = temp[1].split(" ")[1];
            type = temp[2];

        } else {
            name = args[0];
            date = args[1];
        }

        return new String[] {name, date, instance, interval, type};
    }

    private static EventCommand eventTaskHandler(String[] params) throws DukeException {
        try {
            String[] args;
            args = extractFlag(params[0], params[1], "/at");

            //optional flag can come before or after the mandatory flag
            args = extractOptionalFlags(args, intervalFlags);

            assert args.length > 1 : "Broken splitting";

            String name = args[0];
            LocalDate date = processDate(args[1]);

            if (args[2] != null) {
                int instances = Integer.parseInt(args[2]);
                int interval = Integer.parseInt(args[3]);
                String intervalType = args[4];
                return new EventCommand(name, date, instances, interval, intervalType);
            }
            return new EventCommand(name, date);

        } catch (Exception e) {
            throw new DukeInvalidParametersException();
        }
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
                return eventTaskHandler(params);
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
