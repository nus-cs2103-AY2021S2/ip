package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.command.Command;
import duke.command.AddDeadline;
import duke.command.AddEvent;
import duke.command.AddTodo;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a parser that processes input command lines from a user.
 */
public class Parser {
    /**
     * Parses the input line from a user.
     * @param commandLine Input string to be parsed.
     * @return A command based on the input.
     * @throws DukeException If invalid command is given.
     */
    public static Command parse(String commandLine) throws DukeException{
        String[] messages = commandLine.split(" ");
        String commandType = messages[0];

        switch (commandType) {
        case "bye":
            return new ExitCommand();

        case "delete":
            if (messages.length == 1) {
                throw new DukeException("The task index is missing.");
            }
            return new DeleteCommand(Integer.parseInt(messages[1]));

        case "done":
            if (messages.length == 1) {
                throw new DukeException("The task index is missing.");
            }
            int id = Integer.parseInt(messages[1]);
            return new DoneCommand(id);

        case "deadline":
            if (messages.length == 1) {
                throw new DukeException("The task description is missing.");
            }
            try {
                return generateDeadline(processTask(commandLine));
            } catch (Exception ex) {
                throw new DukeException("Deadline description is in wrong format.");
            }

        case "event":
            if (messages.length == 1) {
                throw new DukeException("The task description is missing.");
            }
            try {
                return generateEvent(processTask(commandLine));
            } catch (Exception ex) {
                throw new DukeException("Event description is in wrong format.");
            }

        case "todo":
            if (messages.length == 1) {
                throw new DukeException("The task description is missing.");
            }
            return new AddTodo(new Todo(processTask(commandLine), 0));

        case "list":
            return new ListCommand();

        default:
            throw new DukeException("I'm sorry, but I don't know what " + commandType + " means.");

        }
    }

    /**
     * Processes the String to get the task description.
     * @param message Input string to be processed.
     * @return A task description.
     */
    public static String processTask(String message){
        return message.substring(message.indexOf(' ') + 1);
    }

    /**
     * Generates a AddEvent command from the task description.
     * @param description Event description.
     * @return A command that adds a Event to the task list.
     * @throws Exception If invalid description is given.
     */
    public static AddEvent generateEvent(String description) throws Exception {

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
     * @throws Exception If invalid description is given.
     */
    public static AddDeadline generateDeadline(String description) throws Exception {
        String name = description.substring(0, description.indexOf(" /by "));
        String time = description.substring(description.indexOf(" /by ") + 5);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(time, dateTimeFormat);

        return new AddDeadline(new Deadline(name, 0, by));
    }



}
