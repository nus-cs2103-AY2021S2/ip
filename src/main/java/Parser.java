import java.time.LocalDate;

/**
 * Parse the input from user and make sense of what the user is trying to do.
 */
public class Parser {
    /**
     * Takes in a line of input from user and makes sense of what the command is.
     * If it is a valid command, create the corresponding command and return it.
     * Else, throw a Duke exception to inform user of an invalid command.
     * @param fullCommand The line of command that user input.
     * @return A corresponding command if valid.
     * @throws DukeException If user input an invalid command.
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        String[] first = fullCommand.split(" ", 2);
        switch (first[0]) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        case ("todo"):
            return new AddCommand(new Todo(first[1]));
        case ("event"): {
            String[] second = first[1].split("/at", 2);
            return new AddCommand(new Event(second[0].trim(), LocalDate.parse(second[1].trim())));
        }
        case ("deadline"): {
            String[] second = first[1].split("/by", 2);
            return new AddCommand(new Deadline(second[0].trim(), LocalDate.parse(second[1].trim())));
        }
        case ("done"):
            return new DoneCommand(Integer.parseInt(first[1]));
        case ("delete"):
            return new DeleteCommand(Integer.parseInt(first[1]));
        default:
            throw new DukeException("Unknown command received.");
        }
    }
}
