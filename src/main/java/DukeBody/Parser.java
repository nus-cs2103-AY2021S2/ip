package dukebody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duketask.Deadline;
import duketask.Event;
import duketask.Task;
import duketask.ToDo;


public class Parser {
    // exception classes
    /**
     * Exception thrown when an unrecognised command is provided by the
     * user during parsing.
     */
    public static class UnrecognisedCommandException extends Exception {
        private static final long serialVersionUID = 6864325922556059437L;

        public UnrecognisedCommandException(String command) {
            super("! Unrecognised command: " + command + " encountered during parsing.");
        }
    }

    /**
     * Exception thrown when an empty input is provided by the user where
     * a subcommand is expected.
     */
    public static class EmptySubcommandException extends Exception {
        private static final long serialVersionUID = 6820026755838853943L;

        public EmptySubcommandException(String subcommand) {
            super("! The subcommand " + subcommand + " cannot be empty");
        }
    }

    // members
    private static String delimiter = " :: ";
    private static DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm");

    // methods
    /**
     * Returns the string command representing the task information to be parsed.
     * @param task  the task object to create command for
     * @return      the string command associated with the task object, and the
     *              Parser delimiter and datetime parseFormat.
     */
    public static String taskToCommand (Task task) {
        return task.toCommand(Parser.delimiter, Parser.parseFormat);
    }

    /**
     * Returns the task object represented by a string command.
     * @param command   the string command to be parsed to derive the task information
     * @return          the task object associated with the string command.
     * @throws Parser.UnrecognisedCommandException
     * @throws Task.EmptyDescriptionException
     */
    public static Task commandToTask (String command) throws Parser.UnrecognisedCommandException,
            Task.EmptyDescriptionException {

        // format = type :: state :: description :: createdTime :: others
        String[] parsedCommand = command.split(Parser.delimiter);

        switch (parsedCommand[0]) {
        case "T":
            return new ToDo(parsedCommand[2], (Integer.parseInt(parsedCommand[1]) > 0),
                    LocalDateTime.parse(parsedCommand[3], Parser.parseFormat));

        case "E":
            return new Event(parsedCommand[2], (Integer.parseInt(parsedCommand[1]) > 0),
                    LocalDateTime.parse(parsedCommand[3], Parser.parseFormat),
                    LocalDateTime.parse(parsedCommand[4], Parser.parseFormat));

        case "D":
            return new Deadline(parsedCommand[2], (Integer.parseInt(parsedCommand[1]) > 0),
                    LocalDateTime.parse(parsedCommand[3], Parser.parseFormat),
                    LocalDateTime.parse(parsedCommand[4], Parser.parseFormat));

        default:
            throw new Parser.UnrecognisedCommandException(parsedCommand[0]);
        }
    }

    /**
     * Returns the task object created by parsing user input commands.
     * @param taskType      the type of task to create. must be todo, event or deadline.
     * @param command       the task description and subcommands where applicable such
     *                      as /at in event and /by in deadline task creations.
     * @return      the task object in default undone state and created at the
     *              current datetime.
     * @throws Parser.UnrecognisedCommandException
     * @throws Parser.EmptySubcommandException
     * @throws Task.EmptyDescriptionException
     * @throws DateTimeParseException
     */
    public static Task parseNewCommand (String taskType, String command)
            throws Parser.UnrecognisedCommandException, Parser.EmptySubcommandException,
            Task.EmptyDescriptionException, DateTimeParseException {

        int subcommandIndex;
        Task task;

        switch (taskType) {
        case "todo":
            task = new ToDo(command);
            break;

        case "event":
            subcommandIndex = command.indexOf("/at");
            if (subcommandIndex < 0) {
                throw new Parser.EmptySubcommandException("/at");
            }

            task = new Event (command.substring(0, subcommandIndex - 1).trim(),
                    LocalDateTime.parse(command.substring(subcommandIndex + 3).trim(),
                    Parser.parseFormat));
            break;

        case "deadline":
            subcommandIndex = command.indexOf("/by");
            if (subcommandIndex < 0) {
                throw new Parser.EmptySubcommandException("/by");
            }

            task = new Deadline (command.substring(0, subcommandIndex - 1).trim(),
                    LocalDateTime.parse(command.substring(subcommandIndex + 3).trim(),
                    Parser.parseFormat));
            break;

        default:
            throw new Parser.UnrecognisedCommandException(taskType);
        }

        return task;
    }
}
