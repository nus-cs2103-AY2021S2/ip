import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskBuilder {

    private static final Pattern TODO_REGEX = Pattern.compile("todo\\s+(.*)");
    private static final Pattern DEADLINE_REGEX = Pattern.compile("deadline\\s+(.*)\\s+/by\\s+(.*)");
    private static final Pattern EVENT_REGEX = Pattern.compile("event\\s+(.*)\\s+/at\\s+(.*)");

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static LocalDate parseDate(String dateString) throws AliceException {
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new AliceException("Invalid date supplied");
        }
    }

    public static Task buildTask(String input) throws AliceException {
        Task task;
        Matcher matcher;
        String[] tokens = input.split("\\s+");
        switch (tokens[0]) {
            case "todo":
                matcher = TODO_REGEX.matcher(input);
                if (!matcher.find()) {
                    throw new AliceException("todo Usage: todo [activity]");
                }
                task = new TaskTodo(matcher.group(1).trim(), false);
                break;
            case "deadline":
                matcher = DEADLINE_REGEX.matcher(input);
                if (!matcher.find()) {
                    throw new AliceException("deadline Usage: deadline [activity] /by [deadline]");
                }
                task = new TaskDeadline(matcher.group(1).trim(), false, parseDate(matcher.group(2).trim()));
                break;
            case "event":
                matcher = EVENT_REGEX.matcher(input);
                if (!matcher.find()) {
                    throw new AliceException("event Usage: event [activity] /at [time]");
                }
                task = new TaskEvent(matcher.group(1).trim(), false, parseDate(matcher.group(2).trim()));
                break;
            default: throw new IllegalStateException();
        }
        return task;
    }
}
