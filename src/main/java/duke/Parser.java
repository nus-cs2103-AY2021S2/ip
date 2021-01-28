package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

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

            case "find":
                if (messages.length == 1) {
                    throw new DukeException("The keyword is missing.");
                }
                return new FindCommand(processTask(commandLine));

            default:
                throw new DukeException("I'm sorry, but I don't know what " + commandType + " means.");

        }
    }

    //Gets task description
    public static String processTask(String message){
        return message.substring(message.indexOf(' ') + 1);
    }

    public static AddEvent generateEvent(String description) throws Exception {

        String name = description.substring(0, description.indexOf(" /at "));
        String time = description.substring(description.indexOf(" /at ") + 5);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime at = LocalDateTime.parse(time, dateTimeFormat);

        return new AddEvent(new Event(name, 0, at));
    }

    public static AddDeadline generateDeadline(String description) throws Exception {
        String name = description.substring(0, description.indexOf(" /by "));
        String time = description.substring(description.indexOf(" /by ") + 5);

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(time, dateTimeFormat);

        return new AddDeadline(new Deadline(name, 0, by));
    }



}
