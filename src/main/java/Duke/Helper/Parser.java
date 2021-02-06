package Duke.Helper;

import Duke.Constant.Constants;
import Duke.Exception.EmptyTaskException;
import Duke.Exception.InvalidTask;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Todo;

/**
 * A class deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the input string into a ToDo command so that it can be added to the task list.
     * @param command A string that needed to be parsed to a Todo instance
     * @return A Todo command corresponding to the input
     * @throws EmptyTaskException A Todo command with no description.
     */
    public Todo parseTodo(String command) throws EmptyTaskException {
        String description = command.substring(5);
        if (description.isEmpty()) {
            throw new EmptyTaskException("todo");
        }
        return new Todo(description);
    }

    /**
     * Parses the input string into a Deadline command so that it can be added to the task list.
     * @param command A string that needed to be parsed to a Deadline instance
     * @return A Deadline command corresponding to the input or null if the date and time format in the string is wrong.
     * @throws InvalidTask The input string does not contain the word "/by", which at a signature in a deadline command.
     * @throws EmptyTaskException A Deadline command with no description.
     */
    public Deadline parseDeadline(String command) throws InvalidTask, EmptyTaskException {
        String content = command.substring(9);
        if (content.isEmpty()) {
            throw new EmptyTaskException("deadline");
        }
        int byIndex = content.indexOf("/by");
        if (byIndex == -1) {
            throw new InvalidTask("deadline");
        } else {
            String description = content.substring(0, byIndex - 1);
            String by = content.substring(byIndex + 4);
            DateTimeProcessor processor = new DateTimeProcessor(by);
            String time = processor.getFullDateTime();
            if (time.equals(Constants.INVALID_DATETIME_FORMAT_SHORT)) {
                return null;
            } else {
                return new Deadline(description, time);
            }
        }
    }

    /**
     * Parses the input string into an Event command so that it can be added to the task list.
     * @param command A string that needed to be parsed to an Event instance
     * @return An Event command corresponding to the input or null if the date and time format in the string is wrong.
     * @throws InvalidTask The input string does not contain the word "/at", which at a signature in an event command.
     * @throws EmptyTaskException An Event command with no description.
     */
    public Event parseEvent(String command) throws EmptyTaskException, InvalidTask {
        String content = command.substring(6);
        if (content.isEmpty()) {
            throw new EmptyTaskException("event");
        }
        int atIndex = content.indexOf("/at");
        if (atIndex == -1) {
            throw new InvalidTask("event");
        } else {
            String description = content.substring(0, atIndex - 1);
            String at = content.substring(atIndex + 4);
            DateTimeProcessor processor = new DateTimeProcessor(at);
            String time = processor.getFullDateTime();
            if (time.equals(Constants.INVALID_DATETIME_FORMAT_SHORT)) {
                return null;
            } else {
                return new Event(description, time);
            }
        }
    }
}
