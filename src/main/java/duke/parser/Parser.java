package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import duke.duke.Duke;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;

import duke.utils.DateValidator;
import duke.utils.DateValidatorUsingDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Command processInput(String userInput, Duke bot) throws InvalidCommandException,
            InvalidArgumentException {
        String[] userInputArray = userInput.split(" ", 2);
        Command userCommand = null;
        if (userInputArray.length == 1) {
            if (!(userInputArray[0].equals("bye") || userInputArray[0].equals("list"))) {
                if (userInputArray[0].equals("todo") || userInputArray[0].equals("done")
                        || userInputArray[0].equals("deadline") || userInputArray[0].equals("event")
                        || userInputArray[0].equals("delete")) {
                    throw new InvalidCommandException("OOPS!!! " + "The description of a "
                            + userInputArray[0] + " cannot be empty.\n");
                } else {
                    throw new InvalidCommandException("OOPS!!! "
                            + "I'm sorry, but I don't know what that means :-()\n");
                }
            }

            if (userInputArray[0].equals("bye")) {
                userCommand = new ByeCommand();
            } else if (userInputArray[0].equals("list")) {
                userCommand = new ListCommand();
            }
        } else {
            switch (userInputArray[0]) {
            case "done":
                try {
                    Integer.parseInt(userInputArray[1]);
                } catch (NumberFormatException ex) {
                    throw new InvalidArgumentException("Invalid command! "
                            + "Please input task number using 'delete (number)'.\n");
                }
                if (Integer.parseInt(userInputArray[1]) > bot.getNumberOfTasks()) {
                    throw new InvalidArgumentException("Please input argument <= to "
                            + bot.getNumberOfTasks() + "!\n");
                }
                userCommand = new DoneCommand(userInputArray[1]);
                break;
            case "delete":
                try {
                    Integer.parseInt(userInputArray[1]);
                } catch (NumberFormatException ex) {
                    throw new InvalidArgumentException("Invalid command! "
                            + "Please input task number using 'delete (number)'.\n");
                }
                if (Integer.parseInt(userInputArray[1]) > bot.getNumberOfTasks()) {
                    throw new InvalidArgumentException("Please input argument <= to "
                            + bot.getList().getLst().size() + "!\n");
                }
                userCommand = new DeleteCommand(userInputArray[1]);
                break;
            case "todo":
                userCommand = new TodoCommand(userInputArray[1]);
                break;
            case "deadline":
                String[] arr = userInputArray[1].split("/by");
                if (arr.length == 1) {
                    throw new InvalidArgumentException("Please input task due date using '/by (date)'!\n");
                }
                String[] str = arr[0].split(" ", 2);
                if (str.length == 1) {
                    throw new InvalidArgumentException("Please input task description!\n");
                }
                String deadLine = arr[1].strip();
                userCommand = new DeadlineCommand(arr[0], processDate(deadLine));
                break;
            case "event":
                String[] a = userInputArray[1].split("/at");
                if (a.length == 1) {
                    throw new InvalidArgumentException("Please input task due date using '/at (date)'!\n");
                }
                String[] s = a[0].split(" ", 2);
                if (s.length == 1) {
                    throw new InvalidArgumentException("Please input task description!\n");
                }
                String eventTime = a[1].strip();
                userCommand = new EventCommand(a[0], processDate(eventTime));
                break;
            }
        }
        return userCommand;
    }

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
