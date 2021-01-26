import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    // exception classes
    public static class InvalidTaskTypeException extends Exception {
        private static final long serialVersionUID = 2286254111257413392L;

        public InvalidTaskTypeException (String taskType) {
            super("! Encountered Task Type: " + taskType + " during parsing of command");
        }
    }

    public static class EmptySubcommandException extends Exception {
        private static final long serialVersionUID = 1L;

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

    public static Task commandToTask (String command) throws Parser.InvalidTaskTypeException, 
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
            throw new Parser.InvalidTaskTypeException(parsedCommand[0]);
        }
    }

    public static Event parseNewTaskCommand (String command, String subcommand) 
            throws Parser.EmptySubcommandException, Task.EmptyDescriptionException, 
            DateTimeParseException {

        int subcommandIndex = command.indexOf(subcommand);

        if (subcommandIndex < 0) {
            throw new Parser.EmptySubcommandException(subcommand);
        }

        return new Event (command.substring(0, subcommandIndex - 1).trim(), 
                LocalDateTime.parse(command.substring(subcommandIndex + 3).trim(),
                Parser.parseFormat));
    }
}
