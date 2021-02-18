package mike;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExceptionCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;

public class Parser {

    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    /**
     * Reads user input and parses it to return a command object
     *
     * @return Command.Command type object with parameters entered by user
     */
    public static Command parseInput(String userInput) {
        String errMsg;
        String[] userInputArr = userInput.split(" ");
        Pattern pattern;
        Matcher matcher;
        DateTimeFormatter format;
        LocalDateTime dateTimeObject;

        switch (userInputArr[0].toLowerCase()) {
        case BYE_COMMAND:
            return new ByeCommand();

        case LIST_COMMAND:
            return new ListCommand();

        case DONE_COMMAND:
            pattern = Pattern.compile("(?i)done (\\d+)");
            matcher = pattern.matcher(userInput);
            if (!matcher.find() || Integer.parseInt(matcher.group(1)) <= 0 ) {
                errMsg = " ☹ OOPS!!! Input does not match Done command format. eg.\n"
                        + "   Done <index of task to mark completed>";
                break;
            }
            int taskIndexToDone = Integer.parseInt(matcher.group(1));
            return new DoneCommand(taskIndexToDone);

        case TODO_COMMAND:
            pattern = Pattern.compile("(?i)todo (.+)");
            matcher = pattern.matcher(userInput);
            if (!matcher.find()) {
                errMsg = " ☹ OOPS!!! Input does not match Todo command format. eg.\n   todo <description>";
                break;
            }
            return new TodoCommand(matcher.group(1));

        case EVENT_COMMAND:
            pattern = Pattern.compile("(?i)event (.+) /at (\\d?\\d-\\d\\d-\\d\\d\\d\\d \\d?\\d:\\d\\d)");
            matcher = pattern.matcher(userInput);
            if (!matcher.find()) {
                errMsg = " ☹ OOPS!!! Input does not match Event command format. eg.\n"
                        + "   event <description> /at <dd-MM-yyyy> <hh:mm>";
                break;
            }
            format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            dateTimeObject = LocalDateTime.parse(matcher.group(2), format);
            return new EventCommand(matcher.group(1), dateTimeObject);

        case DEADLINE_COMMAND:
            pattern = Pattern.compile("(?i)deadline (.+) /by (\\d?\\d-\\d\\d-\\d\\d\\d\\d \\d?\\d:\\d\\d)");
            matcher = pattern.matcher(userInput);
            if (!matcher.find()) {
                errMsg = " ☹ OOPS!!! Input does not match Deadline command format. eg.\n"
                        + "   deadline <description> /by <deadline>";
                break;
            }
            format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            dateTimeObject = LocalDateTime.parse(matcher.group(2), format);
            return new DeadlineCommand(matcher.group(1), dateTimeObject);

        case DELETE_COMMAND:
            pattern = Pattern.compile("(?i)delete (\\d+)");
            matcher = pattern.matcher(userInput);
            if (!matcher.find() || Integer.parseInt(matcher.group(1)) <= 0 ) {
                errMsg = " ☹ OOPS!!! Input does not match Delete command format. eg.\n"
                        + "   Delete <index of task to delete>";
                break;
            }
            int taskIndexToDelete = Integer.parseInt(matcher.group(1));
            return new DeleteCommand(taskIndexToDelete);

        case FIND_COMMAND:
            pattern = Pattern.compile("(?i)find (.+)");
            matcher = pattern.matcher(userInput);
            if (!matcher.find()) {
                errMsg = " ☹ OOPS!!! Input does not match Find command format. eg.\n   find <keyword> ";
                break;
            }
            return new FindCommand(matcher.group(1));

        default:
            errMsg = " OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return new ExceptionCommand(errMsg);
    }
}

