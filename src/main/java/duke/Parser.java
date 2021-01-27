package duke;

import duke.command.*;
import duke.exception.DukeArgumentException;
import duke.exception.DukeDateTimeException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final Ui ui = new Ui();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parse(String userInput, TaskList tasks) {
        String[] split = userInput.split("\\s+");
        String command = split[0];
        Command res = null;
        try {
            switch (command) {
                case "todo":
                    checkStringArgument(split, command);
                    String description = userInput.substring(5);
                    res = new AddCommand(new ToDo(description));
                    break;
                case "deadline":
                    checkStringArgument(split, command);
                    String[] deadlineDetails = userInput.substring(9).split(" /by ");
                    checkDateTime(deadlineDetails);
                    String deadlineDescription = deadlineDetails[0];
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDetails[1], formatter);
                    res = new AddCommand(new Deadline(deadlineDescription, deadlineDateTime));
                    break;
                case "event":
                    checkStringArgument(split, command);
                    String[] eventDetails = userInput.substring(6).split(" /at ");
                    checkDateTime(eventDetails);
                    String eventDescription = eventDetails[0];
                    LocalDateTime eventDateTime = LocalDateTime.parse(eventDetails[1], formatter);
                    res = new AddCommand(new Event(eventDescription, eventDateTime));
                    break;
                case "list":
                    res = new ListCommand();
                    break;
                case "done":
                    int completedTaskIdx = checkNumericalArgument(split, tasks);
                    res = new DoneCommand(completedTaskIdx);
                    break;
                case "delete":
                    int taskToDelete = checkNumericalArgument(split, tasks);
                    res = new DeleteCommand(taskToDelete);
                    break;
                case "find":
                    checkStringArgument(split, command);
                    String keyword = userInput.substring(5);
                    res = new FindCommand(keyword);
                    break;
                case "bye":
                    res = new ExitCommand();
                    break;
                default:
                    res = new InvalidCommand();
            }
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        return res;
    }

    private static void checkStringArgument(String[] split, String command) throws DukeArgumentException {
        if (split.length == 1 && command.equals("find")) {
            throw new DukeArgumentException("You have not entered a keyword!");
        } else if (split.length == 1) {
            throw new DukeArgumentException("You have not entered a task description!");
        }
    }

    private static void checkDateTime(String[] details) throws DukeDateTimeException {
        if (details.length < 2 || details[1].isBlank()) {
            throw new DukeDateTimeException("You have not entered a date and time!");
        }
    }

    private static int checkNumericalArgument(String[] split, TaskList tasks) throws DukeArgumentException {
        if (split.length > 2) {
            throw new DukeArgumentException("Please enter a numerical value for the task index!");
        }
        try {
            int taskIndex = Integer.parseInt(split[1])-1;
            tasks.getTask(taskIndex);
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new DukeArgumentException("Please enter a numerical value for the task index!");
        } catch (IndexOutOfBoundsException e) {
            if (split.length == 1) {
                throw new DukeArgumentException("You have not entered a task index!");
            } else {
                throw new DukeArgumentException("Please enter a valid task index!");
            }
        }
    }
}
