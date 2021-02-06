package UserInterface;

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
            = new ArrayList<>(Arrays.asList("TODO", "DEADLINE", "EVENT", "DONE", "DELETE", "LIST", "BYE"));

    public static Command parse(String input) {
        if (!isInputValid(input)) {
            return new DoNothingCommand();
        }

        String action = getAction(input);
        String description = getDescription(input);
        LocalDateTime deadlineDateTime = convertToDateTime(getByDateTimeString(input));
        LocalDateTime eventDateTime = convertToDateTime(getAtDateTimeString(input));

        switch (action.toUpperCase()) {
            case "BYE":
                return new ByeCommand();
            case "LIST":
                return new ListCommand();
            case "DONE":
                return new DoneCommand(description);
            case "DELETE":
                return new DeleteCommand(description);
            case "TODO":
                return new AddToDoCommand(description);
            case "DEADLINE":
                return new AddDeadlineCommand(description, deadlineDateTime);
            case "EVENT":
                return new AddEventCommand(description, eventDateTime);
            default:
                return new DoNothingCommand();
        }
    }

    private static String getAction(String input) {
        return (input + " ").split(" ")[0];
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

    private static String getByDateTimeString(String input) {
        String action = getAction(input);
        String remainingTokens = getRemainingTokens(input);
        if (action.equals("deadline") && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by", 2)[1].trim();
        }
        return "";
    }

    private static String getAtDateTimeString(String input) {
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

    private static boolean isInputValid(String input) {
        String action = getAction(input);
        String description = getDescription(input);
        String byDateTimeString = getByDateTimeString(input);
        String atDateTimeString = getAtDateTimeString(input);

        try {
            if (!validActions.contains(action.toUpperCase())) {
                throw new InvalidActionException(action);
            }

            if ((!action.equals("bye") && !action.equals("list")) & description.length() == 0) {
                throw new MissingDescriptionException(action);
            }

            if ((action.equals("done") || action.equals("delete")) && (!isInteger(description))) {
                throw new TaskNumberNotIntException();
            }

            if (action.equals("deadline") && byDateTimeString.length() == 0) {
                throw new MissingDeadlineException();
            }

            if (action.equals("event") && atDateTimeString.length() == 0) {
                throw new MissingEventTimeException();
            }

            if (action.equals("deadline") && null == convertToDateTime(byDateTimeString)) {
                throw new DateTimeFormatException(byDateTimeString);
            }

            if (action.equals("event") && null == convertToDateTime(atDateTimeString)) {
                throw new DateTimeFormatException(atDateTimeString);
            }

        } catch (MissingDescriptionException
                | InvalidActionException
                | TaskNumberNotIntException
                | MissingDeadlineException
                | MissingEventTimeException
                | DateTimeFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}