package bearbear.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bearbear.bearbear.BearBear;
import bearbear.command.Command;
import bearbear.command.DeadlineCommand;
import bearbear.command.DeleteCommand;
import bearbear.command.DoneCommand;
import bearbear.command.EventCommand;
import bearbear.command.FindCommand;
import bearbear.command.TodoCommand;
import bearbear.command.ValidateCommand;
import bearbear.exceptions.InvalidArgumentException;
import bearbear.exceptions.InvalidCommandException;
import bearbear.utils.DateValidator;
import bearbear.utils.DateValidatorUsingDateFormat;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into a command.
     * @param userInput User input string.
     * @param bot A Duke object that manages task list operations.
     * @return User command.
     * @throws InvalidCommandException If user input is an unrecognised command.
     * @throws InvalidArgumentException If argument is missing from user input.
     */
    public static Command processInput(String userInput, BearBear bot) throws InvalidCommandException,
            InvalidArgumentException {
        String[] userInputArray = userInput.split(" ", 2);
        String command = userInputArray[0];
        Command userCommand;
        if (userInputArray.length == 1) {
            userCommand = ValidateCommand.validateSingleArgumentCommand(command);
        } else {
            String input = userInputArray[1];
            switch (userInputArray[0]) {
            case "done":
                int taskNumber = ValidateCommand.validateDoneCommand(input, bot);
                userCommand = new DoneCommand(taskNumber);
                break;
            case "delete":
                int taskIndex = ValidateCommand.validateDeleteCommand(input, bot);
                userCommand = new DeleteCommand(taskIndex);
                break;
            case "todo":
                userCommand = new TodoCommand(input);
                break;
            case "deadline":
                String[] arr = ValidateCommand.validateDeadlineCommand(input);
                String deadLine = arr[1].strip();
                userCommand = new DeadlineCommand(arr[0], processDate(deadLine));
                break;
            case "event":
                String[] a = ValidateCommand.validateEventCommand(input);
                String eventTime = a[1].strip();
                userCommand = new EventCommand(a[0], processDate(eventTime));
                break;
            case "find":
                userCommand = new FindCommand(input);
                break;
            default:
                throw new InvalidCommandException("Invalid Command!\n");
            }
        }
        return userCommand;
    }

    /**
     * Parses user input date in "yyyy-mm-dd" or "yyyy-mm-dd HHHH" form as formatted string.
     * @param date User input date.
     * @return User input date in the format "dd/mm/yyyy HHHH".
     */
    public static String processDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d HHmm");
        DateValidator dateTimeValidator = new DateValidatorUsingDateFormat(dateTimeFormatter);

        if (!dateTimeValidator.isValid(date)) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            DateValidator dateValidator = new DateValidatorUsingDateFormat(dateFormatter);
            if (!dateValidator.isValid(date)) {
                return date;
            } else {
                LocalDate d1 = LocalDate.parse(date);
                String day = d1.getDayOfWeek().toString();
                String month = d1.getMonth().toString();
                int year = d1.getYear();
                return String.format("%s %s %d", day, month, year);
            }
        } else {
            String[] deadLineArray = date.split(" ");
            LocalDate d1 = LocalDate.parse(deadLineArray[0]);
            String day = d1.getDayOfWeek().toString();
            String month = d1.getMonth().toString();
            int year = d1.getYear();
            return String.format("%s %s %d %s", day, month, year, deadLineArray[1]);
        }
    }
}
