package alice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class TaskBuilder {

	private static final Pattern TODO_REGEX = Pattern.compile("todo\\s+(.*)");
	private static final Pattern DEADLINE_REGEX = Pattern.compile("deadline\\s+(.*)\\s+/by\\s+(.*)");
	private static final Pattern EVENT_REGEX = Pattern.compile("event\\s+(.*)\\s+/at\\s+(.*)");

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");

	public static LocalDate parseDate(String dateString) throws AliceException {
		try {
			return LocalDate.parse(dateString, formatter);
		} catch (DateTimeParseException dateTimeParseException) {
			throw new AliceException("Invalid date supplied, " + dateTimeParseException.getMessage());
		}
	}

	public static Task buildTask(String[] tokens) throws AliceException, IllegalArgumentException {
		Task task;
		if (tokens[1].trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		switch (tokens[0]) {
		case "todo":
			task = new TaskTodo(tokens[1], false);
			break;
		case "deadline":
			if (tokens[2].trim().length() == 0) {
				throw new IllegalArgumentException();
			}
			task = new TaskDeadline(tokens[1], false, parseDate(tokens[2]));
			break;
		case "event":
			if (tokens[2].trim().length() == 0) {
				throw new IllegalArgumentException();
			}
			task = new TaskEvent(tokens[1], false, parseDate(tokens[2]));
			break;
		default:
			throw new IllegalStateException();
		}
		return task;
	}
}
