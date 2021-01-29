package DukeBody;

import DukeTask.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser {
    // exception classes
    public static class UnrecognisedCommandException extends Exception {
        private static final long serialVersionUID = 6864325922556059437L;

        public UnrecognisedCommandException(String command) {
            super("! Unrecognised command: " + command + " encountered during parsing.");
        }
    }

    public static class EmptySubcommandException extends Exception {
        private static final long serialVersionUID = 6820026755838853943L;

        public EmptySubcommandException(String subcommand) {
            super("! The subcommand " + subcommand + " cannot be empty");
        }
    }

    // members
    private static String delimiter = " :: ";

    protected final static DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm");

    // methods
    public static String taskToCommand (Task task) {
        return task.toCommand(Parser.delimiter, Parser.parseFormat);
    }

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

    public static Task parseNewCommand (String taskType, String command) 
            throws Parser.UnrecognisedCommandException, Parser.EmptySubcommandException, 
            Task.EmptyDescriptionException, DateTimeParseException {

        int subcommandIndex; Task task;

        switch (taskType) {
        case "todo":
            task = new ToDo(command);
            break;

        case "event":
            subcommandIndex = command.indexOf("/at");
            if (subcommandIndex < 0) { throw new Parser.EmptySubcommandException("/at"); }
            task = new Event (command.substring(0, subcommandIndex - 1).trim(), 
                    LocalDateTime.parse(command.substring(subcommandIndex + 3).trim(),
                    Parser.parseFormat));
            break;

        case "deadline":
            subcommandIndex = command.indexOf("/by");
            if (subcommandIndex < 0) { throw new Parser.EmptySubcommandException("/by"); }
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
