package dukebody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duketask.Deadline;
import duketask.Event;
import duketask.Task;
import duketask.ToDo;


public class Parser {
    // members
    private static final String DELIMITER = " :: ";
    private static final DateTimeFormatter DATETIME_PARSE_FORMAT = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HHmm");

    // exception class
    public static class ParserDateTimeParseException extends Exception {
        public ParserDateTimeParseException () {
            super("The datetime format must be:\n" + Parser.DATETIME_PARSE_FORMAT.toString());
        }
    }

    // methods
    /**
     * Returns the string command representing the task information to be parsed.
     * @param task  the task object to create command for
     * @return      the string command associated with the task object, and the
     *              Parser delimiter and datetime parseFormat.
     */
    public static String taskToCommand (Task task) {
        return task.toCommand(Parser.DELIMITER, Parser.DATETIME_PARSE_FORMAT);
    }

    /**
     * Returns the task object represented by a string command.
     * @param command   the string command to be parsed to derive the task information
     * @return          the task object associated with the string command.
     * @throws Duke.UnrecognisedCommandException
     * @throws Task.EmptyDescriptionException
     */
    public static Task commandToTask (String command) throws Duke.UnrecognisedCommandException,
            Task.EmptyDescriptionException {

        // format = type :: state :: description :: createdTime :: others
        String[] parsedCommand = command.split(Parser.DELIMITER);

        switch (parsedCommand[0]) {
        case "T":
            return new ToDo(parsedCommand[2], (Integer.parseInt(parsedCommand[1]) > 0),
                    LocalDateTime.parse(parsedCommand[3], Parser.DATETIME_PARSE_FORMAT));

        case "E":
            return new Event(parsedCommand[2], (Integer.parseInt(parsedCommand[1]) > 0),
                    LocalDateTime.parse(parsedCommand[3], Parser.DATETIME_PARSE_FORMAT),
                    LocalDateTime.parse(parsedCommand[4], Parser.DATETIME_PARSE_FORMAT));

        case "D":
            return new Deadline(parsedCommand[2], (Integer.parseInt(parsedCommand[1]) > 0),
                    LocalDateTime.parse(parsedCommand[3], Parser.DATETIME_PARSE_FORMAT),
                    LocalDateTime.parse(parsedCommand[4], Parser.DATETIME_PARSE_FORMAT));

        default:
            throw new Duke.UnrecognisedCommandException(parsedCommand[0]);
        }
    }

    /**
     * Parses the command input for the subcommand.
     * @param command
     * @param subcommand
     * @return
     * @throws Duke.ExpectedSubcommandException
     */
    public static String[] parseSubcommand (String command, String subcommand) throws
            Duke.ExpectedSubcommandException {

        String[] commandBreakdown = new String[2];
        int subcommandIndex = command.indexOf(subcommand);

        if (subcommandIndex < 0) {
            throw new Duke.ExpectedSubcommandException(subcommand);
        }

        commandBreakdown[0] = (subcommandIndex == 0) ? "" : command.substring(0, subcommandIndex - 1).trim();
        commandBreakdown[1] = command.substring(subcommandIndex + subcommand.length()).trim();
        return commandBreakdown;
    }

    public static LocalDateTime parseDateTime (String dateTime) throws Parser.ParserDateTimeParseException {
        try {
            return LocalDateTime.parse(dateTime, Parser.DATETIME_PARSE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new Parser.ParserDateTimeParseException();
        }
    }
}
