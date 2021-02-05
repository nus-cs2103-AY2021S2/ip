import Commands.*;
import CustomExceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private static final ArrayList<String> validActions
            = new ArrayList<>(Arrays.asList("todo", "deadline", "event", "done", "delete", "list", "bye"));

    public static Command parse(String input) {
        if (!inputIsValid(input)) {
            return new DoNothingCommand();
        }

        String action = getAction(input);
        String description = getDescription(input);
        LocalDateTime by = convertToDateTime(getByString(input));
        LocalDateTime at = convertToDateTime(getAtString(input));

        switch (action) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(description);
            case "delete":
                return new DeleteCommand(description);
            case "todo":
                return new AddToDoCommand(description);
            case "deadline":
                return new AddDeadlineCommand(description, by);
            case "event":
                return new AddEventCommand(description, at);
            default:
                return new DoNothingCommand();
        }
    }

    private static String getAction(String input) {
        return (input + " ").split(" ")[0].toLowerCase();
    }

    private static String getRemainingTokens(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.split(" ", 2)[1];
    }

    private static String getDescription(String input) {
        String action = getAction(input);
        String remainingTokens = getRemainingTokens(input);

        if (action.equals("deadline") && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by")[0].trim();
        } else if (action.equals("event") && remainingTokens.contains("/at")) {
            return remainingTokens.split("/at")[0].trim();
        } else {
            return remainingTokens;
        }
    }

    private static String getByString(String input) {
        String action = getAction(input);
        String remainingTokens = getRemainingTokens(input);
        if (action.equals("deadline") && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by", 2)[1].trim();
        }
        return "";
    }

    private static String getAtString(String input) {
        String action = getAction(input);
        String remainingTokens = getRemainingTokens(input);
        if (action.equals("event") && remainingTokens.contains("/at")) {
            return remainingTokens.split("/at", 2)[1].trim();
        }
        return "";
    }

    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static LocalDateTime convertToDateTime(String dateTimeString) {
        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            dateTime = null;
        }

        if (null != dateTime) {
            return dateTime;
        }

        try {
            LocalDate date = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("uuuu-MM-dd"));
            return LocalDateTime.of(date, LocalTime.MIDNIGHT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static boolean inputIsValid(String input) {
        String action = getAction(input);
        String description = getDescription(input);
        String byString = getByString(input);
        String atString = getAtString(input);

        try {
            if (!validActions.contains(action)) {
                throw new InvalidActionException(action);
            }

            if ((!action.equals("bye") && !action.equals("list")) & description.length() == 0) {
                throw new MissingDescriptionException(action);
            }

            if ((action.equals("done") || action.equals("delete")) && (!isInteger(description))) {
                throw new InvalidTaskNumberException();
            }

            if (action.equals("deadline") && byString.length() == 0) {
                throw new MissingDeadlineException();
            }

            if (action.equals("event") && atString.length() == 0) {
                throw new MissingEventTimeException();
            }

            if (action.equals("deadline") && null == convertToDateTime(byString)) {
                throw new DateTimeFormatNotRecognizedException(byString);
            }

            if (action.equals("event") && null == convertToDateTime(atString)) {
                throw new DateTimeFormatNotRecognizedException(atString);
            }

        } catch (MissingDescriptionException
                | InvalidActionException
                | InvalidTaskNumberException
                | MissingDeadlineException
                | MissingEventTimeException
                | DateTimeFormatNotRecognizedException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}