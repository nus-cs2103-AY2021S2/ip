package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyTodoDescriptionException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * deals with parsing the user command
 */
public class Parser {

    /**
     * @param fullCommand the user command
     * @return a duke.command.Command object which encapsulates the information of a parsed user command
     * @throws DukeException exception when there is an parsing error
     */
    public Command parseCommand(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else {
            String[] parsedCommand = fullCommand.split(" ");

            try {
                switch (parsedCommand[0]) {
                    case "done", "delete":
                        int taskId = Integer.parseInt(parsedCommand[1]) - 1;
                        if (parsedCommand[0].equals("done")) {
                            return new DoneCommand(fullCommand, taskId);
                        } else {
                            return new DeleteCommand(fullCommand, taskId);
                        }
                    case "todo", "deadline", "event":
                        Task task = parseTask(parsedCommand[0], fullCommand.replaceFirst(parsedCommand[0] + " ", ""));
                        return new AddCommand(fullCommand, task);
                    default:
                        throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                throw e;
            } catch (Exception e) {
                throw new DukeException("duke.command.Command parsing error!");
            }
        }
    }

    /**
     * @param taskType the type of the task
     * @param taskStr part of the user command which represents a task
     * @return a duke.task.Task object in the user command
     * @throws DukeException exception when there is a task parsing error
     */
    private Task parseTask(String taskType, String taskStr) throws DukeException {
        String pattern;
        Pattern r;
        Matcher m;

        if (taskType.equals("todo") && taskStr.isEmpty()) {
            throw new EmptyTodoDescriptionException();
        }

        try {
            switch (taskType) {
                case "todo":
                    return new ToDo(taskStr);
                case "deadline":
                    pattern = "(.*) /by (.*)$";
                    r = Pattern.compile(pattern);
                    m = r.matcher(taskStr);
                    if (m.find()) {
                        return new Deadline(m.group(1), LocalDate.parse(m.group(2)));
                    }
                case "event":
                    pattern = "(.*) /at (.*)$";
                    r = Pattern.compile(pattern);
                    m = r.matcher(taskStr);
                    if (m.find()) {
                        return new Event(m.group(1), LocalDate.parse(m.group(2)));
                    }
                default:
                    throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeException("duke.task.Task parsing error!");
        }
    }
}
