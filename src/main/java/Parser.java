import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime parseInputDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
    }

    public static LocalDateTime parseFileDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy Hmm"));
    }

    public static Command parseInput(String input) throws DukeWrongCommandException {
        String[] parsedInput = input.split(" ", 2);
        String commandStr = parsedInput[0];
        Command c;
        switch(commandStr) {
            case "list":
                c = new ListCommand();
                break;
            case "done":
                c = new DoneCommand(Integer.parseInt(parsedInput[1]));
                break;
            case "todo":
            case "deadline":
            case "event":
                c = new AddCommand(commandStr, parsedInput[1]);
                break;
            case "delete":
                c = new DeleteCommand(Integer.parseInt(parsedInput[1]));
                break;
            default:
                throw new DukeWrongCommandException(commandStr);
        }
        return c;
    }


}