package monica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import monica.command.AddDeadline;
import monica.command.AddEvent;
import monica.command.AddTodo;
import monica.command.Command;
import monica.command.DeleteCommand;
import monica.command.DoneCommand;
import monica.command.ExitCommand;
import monica.command.FindCommand;
import monica.command.HelpCommand;
import monica.command.ListCommand;
import monica.task.Deadline;
import monica.task.Event;
import monica.task.Todo;

/**
 * Represents a parser that processes input command lines from a user.
 */
public class Parser {
    /**
     * Parses the input line from a user.
     * @param commandLine Input string to be parsed.
     * @return A command based on the input.
     * @throws MonicaException If invalid command is given.
     */
    public static Command parse(String commandLine) throws MonicaException {
        assert commandLine != null : "Command line cannot be processed.";
        if (commandLine.isBlank()) {
            throw new MonicaException("I didn't receive any command.");
        }
        String[] messages = commandLine.split(" ");
        String commandType = messages[0];
        boolean isIncomplete = messages.length == 1;

        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "delete":
            if (isIncomplete) {
                throw new MonicaException("The delete index is missing.");
            }
            try {
                int id = Integer.parseInt(messages[1]);
                return new DeleteCommand(id);
            } catch (NumberFormatException e) {
                throw new MonicaException("The delete index must be an integer.");
            }
        case "done":
            if (isIncomplete) {
                throw new MonicaException("The done index is missing.");
            }
            try {
                int id = Integer.parseInt(messages[1]);
                return new DoneCommand(id);
            } catch (NumberFormatException e) {
                throw new MonicaException("The done index must be an integer.");
            }
        case "deadline":
            if (isIncomplete) {
                throw new MonicaException("The deadline description is missing.");
            }
            try {
                return generateDeadline(processTask(commandLine));
            } catch (Exception ex) {
                throw new MonicaException("The deadline description is in wrong format.");
            }
        case "event":
            if (isIncomplete) {
                throw new MonicaException("The event description is missing.");
            }
            try {
                return generateEvent(processTask(commandLine));
            } catch (Exception ex) {
                throw new MonicaException("The event description is in wrong format.");
            }
        case "todo":
            if (isIncomplete) {
                throw new MonicaException("The todo description is missing.");
            }
            return new AddTodo(new Todo(processTask(commandLine), 0));
        case "list":
            return new ListCommand();
        case "find":
            if (isIncomplete) {
                throw new MonicaException("The keyword is missing.");
            }
            return new FindCommand(processTask(commandLine));
        case "help":
            return new HelpCommand();
        default:
            throw new MonicaException("I don't know what '" + commandLine + "' means.");
        }
    }

    /**
     * Processes the String to get the task description.
     * @param message Input string to be processed.
     * @return A task description.
     */
    public static String processTask(String message) {
        assert message != null : "Task description cannot be obtained.";
        return message.substring(message.indexOf(' ') + 1);
    }

    /**
     * Generates a AddEvent command from the task description.
     * @param description Event description.
     * @return A command that adds a Event to the task list.
     */
    public static AddEvent generateEvent(String description) {
        assert description != null : "Event description cannot be obtained.";
        String name = description.substring(0, description.indexOf(" /at "));
        String time = description.substring(description.indexOf(" /at ") + 5);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime at = LocalDateTime.parse(time, dateTimeFormat);

        return new AddEvent(new Event(name, 0, at));
    }

    /**
     * Generates a AddDeadline command from the task description.
     * @param description Deadline description.
     * @return A command that adds a Deadline to the task list.
     */
    public static AddDeadline generateDeadline(String description) {
        assert description != null : "Deadline description cannot be obtained.";
        String name = description.substring(0, description.indexOf(" /by "));
        String time = description.substring(description.indexOf(" /by ") + 5);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(time, dateTimeFormat);

        return new AddDeadline(new Deadline(name, 0, by));
    }



}
