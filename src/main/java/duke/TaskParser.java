package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses String representations of Tasks into Task objects.
 */
public class TaskParser {
    private static final int SPLIT_LIMIT = 2;
    private static final int NUM_REQUIRED_TIMED_TASK_PARTS = 2;

    /**
     * Parses a String representation of a Task into a Task object, and returns the resultant Task object.
     *
     * @param fullTaskString String representation of the Task to be parsed.
     * @return Task object parsed from the input String.
     * @throws DukeException If an Exception occurs as a result of the String being malformed.
     */
    public static Task parseTask(String fullTaskString) throws DukeException {
        Task newTask;
        String[] taskParts;
        String parsableTaskString;
        String taskTail;
        String description;
        String datetimeString;
        LocalDate datetime;
        boolean isDone = false;

        // For parsing tasks from save data.
        if (fullTaskString.startsWith("[isDone]")) {
            parsableTaskString = fullTaskString.split("\\[isDone]", SPLIT_LIMIT)[1].trim();
            isDone = true;
        } else {
            parsableTaskString = fullTaskString;
        }

        if (parsableTaskString.startsWith("todo")) {
            taskParts = parsableTaskString.split("todo", SPLIT_LIMIT);

            if (taskParts[1].length() == 0) {
                throw new DukeException("Oops! Usage: todo [desc]");
            }
            taskTail = taskParts[1].trim();

            description = taskTail;
            newTask = new Todo(description);

        } else if (parsableTaskString.startsWith("event")) {
            taskTail = parsableTaskString.split("event", SPLIT_LIMIT)[1].trim();
            taskParts = taskTail.split(" /on ");

            if (taskParts.length != NUM_REQUIRED_TIMED_TASK_PARTS) {
                throw new DukeException("Oops! Usage: event [desc] /on [date]");
            }

            description = taskParts[0];
            datetimeString = taskParts[1];

            try {
                datetime = LocalDate.parse(datetimeString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                throw new DukeException("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy");
            }

            newTask = new Event(description, datetime);

        } else if (parsableTaskString.startsWith("deadline")) {
            taskTail = parsableTaskString.split("deadline", SPLIT_LIMIT)[1].trim();
            taskParts = taskTail.split(" /by ");

            if (taskParts.length != NUM_REQUIRED_TIMED_TASK_PARTS) {
                throw new DukeException("Oops! Usage: deadline [desc] /by [date]");
            }

            description = taskParts[0];
            datetimeString = taskParts[1];

            try {
                datetime = LocalDate.parse(datetimeString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                throw new DukeException("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy");
            }

            newTask = new Deadline(description, datetime);

        } else {
            throw new DukeException("Unknown command!");
        }

        if (isDone) {
            newTask.markAsDone();
        }

        return newTask;
    }
}