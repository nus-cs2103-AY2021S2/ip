import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        int firstSpace = fullCommand.indexOf(" ");
        String keyword = firstSpace == -1 ? fullCommand : fullCommand.substring(0, firstSpace).toLowerCase();

        if (fullCommand.isEmpty()) {
            throw new DukeException("Please enter a command");
        }

        if (keyword.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (keyword.equalsIgnoreCase("done") || keyword.equalsIgnoreCase("delete")) {
            int option = Integer.parseInt(fullCommand.substring(firstSpace + 1)) - 1;
            switch (keyword) {
            case "done" :
                return new DoneCommand(option);
            case "delete":
                return new DeleteCommand(option);
            }
        }

        if (firstSpace == -1) {
            throw new DukeException("The description cannot be empty");
        }
    }

    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
    }
}
