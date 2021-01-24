package alice.task;

import alice.AliceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskBuilder {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");

	private static LocalDate parseDate(String dateString) throws AliceException {
		try {
			return LocalDate.parse(dateString, formatter);
		} catch (DateTimeParseException dateTimeParseException) {
			throw new AliceException("Invalid date supplied, " + dateTimeParseException.getMessage());
		}
	}

	public static Task buildTask(String[] tokens) throws AliceException, IllegalArgumentException {
		Task task;
		if (tokens.length < 2 || tokens[1].trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		switch (tokens[0].trim()) {
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
