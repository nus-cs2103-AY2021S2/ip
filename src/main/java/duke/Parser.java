package duke;

import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskException;
import duke.task.ToDo;

public class Parser {

    static final String TODO_REGEX = "^\\[T\\] \\[(?: |X)\\] ..*$";
    static final String DEADLINE_REGEX = "^\\[D\\] \\[(?: |X)\\] ..* \\(by: ..*\\)$";
    static final String EVENT_REGEX = "^\\[E\\] \\[(?: |X)\\] ..* \\(at: ..*\\)$";


    /**
     * Parses the user input to find and handle commands containing keywords.
     * @param userInput user command input to be parsed
     * @throws DukeException if command is in an incorrect format.
     */
    public static Command handleInput(String userInput) throws DukeException {
        String[] splitBySpaces = userInput.trim().split("\\s+");
        String keyword = splitBySpaces[0];
        if (keyword.equals("list")) {
            return new ListCommand(splitBySpaces);
        } else if (keyword.equals("done")) {
            return new DoneCommand(splitBySpaces);
        } else if (keyword.equals("deadline") || keyword.equals("todo") || keyword.equals("event")) {
            return new AddCommand(splitBySpaces);
        } else if (keyword.equals("delete")) {
            return new DeleteCommand(splitBySpaces);
        } else if (keyword.equals("bye")) {
            return new ByeCommand(splitBySpaces);
        } else if (keyword.equals("find")) {
            return new FindCommand(splitBySpaces);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses input string as a duke.task.Task.
     * @param input input string to be parsed.
     * @return duke.task.Task object corresponding to input string.
     */
    public static Task stringToTask(String input) throws TaskException, DukeException {
        //Check if it is a valid task first
        Pattern toDoPattern = Pattern.compile(TODO_REGEX);
        Pattern deadlinePattern = Pattern.compile(DEADLINE_REGEX);
        Pattern eventPattern = Pattern.compile(EVENT_REGEX);
        //If it is a To Do command
        if (toDoPattern.matcher(input).find()) {
            String[] inputSplitBySpaces = input.trim().split("\\s+");
            //[ ] Task description
            String taskDescriptionWithDoneBrackets = Helper.join(inputSplitBySpaces, 1);
            String taskDescription = taskDescriptionWithDoneBrackets.substring(4);
            return new ToDo(taskDescription);
        } else {
            boolean matchDeadline = deadlinePattern.matcher(input).find();
            boolean matchEvent = eventPattern.matcher(input).find();
            //If it is a deadline or event command
            if (matchDeadline || matchEvent) {
                String[] inputSplitBySpaces = input.trim().split("\\s+");
                if (matchDeadline) {
                    //Index of /by
                    int byIndex = Helper.arrayIndexOf(inputSplitBySpaces, "(by:");
                    //E.g. [ ] sample_description
                    String taskDescriptionWithDoneBrackets = Helper.join(inputSplitBySpaces, 1, byIndex - 1);
                    String taskDescription = taskDescriptionWithDoneBrackets.substring(4);
                    String dueDateWithClosingBracket = Helper.join(inputSplitBySpaces, byIndex + 1,
                            inputSplitBySpaces.length - 1);
                    String dueDate = dueDateWithClosingBracket.substring(0, dueDateWithClosingBracket.length() - 1);
                    return new Deadline(taskDescription, dueDate);
                } else {
                    //Index of /at
                    int atIndex = Helper.arrayIndexOf(inputSplitBySpaces, "(at:");
                    //E.g. [ ] sample_description
                    String taskDescriptionWithDoneBrackets = Helper.join(inputSplitBySpaces, 1, atIndex - 1);
                    String taskDescription = taskDescriptionWithDoneBrackets.substring(4);
                    String eventDateWithClosingBracket = Helper.join(inputSplitBySpaces, atIndex + 1,
                            inputSplitBySpaces.length - 1);
                    String eventDate = eventDateWithClosingBracket.substring(0,
                            eventDateWithClosingBracket.length() - 1);
                    return new Event(taskDescription, eventDate);
                }
            } else {
                throw new TaskException("Invalid task entry.");
            }
        }
    }
}
