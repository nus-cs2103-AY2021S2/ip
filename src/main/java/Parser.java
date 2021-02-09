import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses the command input by the user and returns the action to be carried out.
     *
     * @param command Command input by the user.
     * @return Action to be carried out.
     */
    public static String parseCommand(String command) {
        return command.split(" ")[0];
    }

    /**
     * Returns the index of the task to be marked as done.
     *
     * @param command Command input by the user.
     * @return Index of task to be marked as done.
     */
    public static int parseDoneIndex(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    private static LocalDateTime dateTimeParser(String input) {
        return LocalDateTime.parse(input,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Parses the stored string and finds the type of task.
     *
     * @param task String retrieved from Storage.
     * @return Type of task.
     */
    public static String parseTypeOfTask(String task) {
        String[] split = task.split("\\|");
        String typeOfTask = split[0].trim();
        return typeOfTask;
    }

    /**
     * Parses the stored string and finds the completion status.
     *
     * @param task String retrieved from Storage.
     * @return Completion status of the task.
     */
    public static String parseCompletionStatus(String task) {
        String[] split = task.split("\\|");
        String isDone = split[1].trim();
        return isDone;
    }

    /**
     * Parses the stored string and returns the description of the task.
     *
     * @param task String retrieved from Storage.
     * @return Description of the task.
     */
    public static String parseName(String task) {
        String[] split = task.split("\\|");
        String name = split[2].trim();
        return name;
    }

    /**
     * Parses the stored string and returns date-time data.
     *
     * @param task String retrieved from Storage.
     * @return LocalDateTime object containing the due date/date of event.
     */
    public static LocalDateTime parseTime(String task) {
        String[] split = task.split("\\|");
        String typeOfTask = split[0].trim();
        LocalDateTime time = typeOfTask.equals("T")
                             ? Parser.dateTimeParser("01-01-2001 0101")
                             : Parser.dateTimeParser(split[3].trim());
        return time;
    }

}

