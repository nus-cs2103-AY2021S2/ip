package Mike;

import Command.*;
import Exception.MikeInvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DELETE_COMMAND = "delete";

    /**
     * Reads user input and parses it
     *
     * @return Command.Command type object with parameters entered by user
     */
    public static Command parseInput(Scanner scanner) throws MikeInvalidInputException {
        String userInput = scanner.nextLine();
        String[] userInputArr = userInput.split(" ");
        Pattern pattern;
        Matcher matcher;

        /*TODO: abstract out try catches and use string error message instead*/
        switch (userInputArr[0].toLowerCase()) {
            case BYE_COMMAND:
                return new ByeCommand();

            case LIST_COMMAND:
                return new ListCommand();

            case DONE_COMMAND:
                try {
                    return new DoneCommand(Integer.parseInt(userInputArr[1]));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Done command format. eg.\n" +
                            "   Done <index of task to mark completed>");
                }

            case TODO_COMMAND:
                pattern = Pattern.compile("(?i)todo (.+)");
                matcher = pattern.matcher(userInput);
                if(matcher.find()) {
                    return new TodoCommand(matcher.group(1));
                } else {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Todo command format. eg.\n" +
                            "   todo <description>");
                }

            case EVENT_COMMAND:
                try {
                    pattern = Pattern.compile("(?i)event (.+) /at (\\d?\\d-\\d\\d-\\d\\d\\d\\d \\d?\\d:\\d\\d)");
                    matcher = pattern.matcher(userInput);
                    LocalDateTime dateTimeObject;

                    matcher.find();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    dateTimeObject = LocalDateTime.parse(matcher.group(2), format);

                    return new EventCommand(matcher.group(1), dateTimeObject);
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Event command format. eg.\n" +
                            "   event <description> /at <dd-MM-yyyy> <hh:mm>");
                }

            case DEADLINE_COMMAND:
                try {
                    pattern = Pattern.compile("(?i)deadline (.+) /by (\\d?\\d-\\d\\d-\\d\\d\\d\\d \\d?\\d:\\d\\d)");
                    matcher = pattern.matcher(userInput);
                    LocalDateTime dateTimeObject;

                    matcher.find();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    dateTimeObject = LocalDateTime.parse(matcher.group(2), format);

                    return new DeadlineCommand(matcher.group(1), dateTimeObject);
                } catch (IllegalStateException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Deadline command format. eg.\n" +
                            "   deadline <description> /by <deadline>");
                }

            case DELETE_COMMAND:
                try {
                    return new DeleteCommand(Integer.parseInt(userInputArr[1]));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new MikeInvalidInputException(
                            " ☹ OOPS!!! Input does not match Delete command format. eg.\n" +
                            "   Delete <index of task to delete>");
                }

            default:
                throw new MikeInvalidInputException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

