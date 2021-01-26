import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Parser() {

    }

    void invalidCommandChecker(String taskType) throws InvalidCommandException {
        if (!(taskType.equals("todo") || taskType.equals("done") || taskType.equals("list") || taskType.equals("event")
                || taskType.equals("deadline") || taskType.equals("delete") || taskType.equals("bye"))) {
            throw new InvalidCommandException("Sorry, I don't know what that means...");
        }
    }

    void emptyDescriptionChecker(String[] tokens) throws EmptyDescriptionException {
        if (tokens.length < 2) {
            throw new EmptyDescriptionException("Sorry, nothing was written after the command so I am unable to process...");
        }
    }

    public String parseDate(String date) {
        LocalDate d1 = LocalDate.parse(date);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String parseTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                .format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public Command parse(String fullCommand) throws DukeException {
        String[] tokens = fullCommand.split(" ", 2);
        String taskType = tokens[0];

        invalidCommandChecker(taskType);

        switch (taskType) {
        case "list": {
            return new PrintListCommand();
        }
        case "done": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];
            int taskIndex = Integer.parseInt(taskInfo) - 1;
            return new DoneCommand(taskIndex);
        }
        case "delete": {
            emptyDescriptionChecker(tokens);

            String taskInfo = tokens[1];
            int taskIndex = Integer.parseInt(taskInfo) - 1;
            return new DeleteCommand(taskIndex);
        }
        case "todo": {
            String taskInfo = tokens[1];
            return new AddCommand(new ToDo(taskInfo));
        }
        case "deadline": {
            String taskInfo = tokens[1];
            if (!(taskInfo.contains("/by"))) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }
            String[] taskInfoArr = taskInfo.split(" /by ", 2);
            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }
            String[] dateAndTime = taskInfoArr[1].split(" ");
            String date = parseDate(dateAndTime[0]);
            String time = parseTime(dateAndTime[1]);
            String by = date + " " + time;
            return new AddCommand(new Deadline(taskInfoArr[0], by));
        }
        case "event": {
            String taskInfo = tokens[1];
            if (!(taskInfo.contains("/at"))) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }
            String[] taskInfoArr = taskInfo.split(" /at ", 2);
            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
            }
            String[] dateAndTime = taskInfoArr[1].split(" ");
            String date = parseDate(dateAndTime[0]);
            String time = parseTime(dateAndTime[1]);
            String by = date + " " + time;
            return new AddCommand(new Event(taskInfoArr[0], by));
        }
        case "bye": {
            return new ExitCommand();
        }
        default: {
            return null;
        }
        }
    }
}
