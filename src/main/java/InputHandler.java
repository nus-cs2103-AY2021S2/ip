import CustomExceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class InputHandler {
    private final String input;
    private static final ArrayList<String> validActions
            = new ArrayList<>(Arrays.asList("todo", "deadline", "event", "done", "delete", "list", "bye"));

    public InputHandler(String input) {
        this.input = input;
    }

    public String getAction() {
        return (input + " ").split(" ")[0];
    }

    private String getRemainingTokens() {
        if (!input.contains(" ")) {
            return "";
        }
        return this.input.split(" ", 2)[1];
    }

    public String getDescription() {
        String action = this.getAction();
        String remainingTokens = this.getRemainingTokens();

        if (action.equals("deadline") && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by")[0].trim();
        } else if (action.equals("event") && remainingTokens.contains("/at")) {
            return remainingTokens.split("/at")[0].trim();
        } else {
            return remainingTokens;
        }
    }

    public LocalDateTime getBy() {
        return convertToDateTime(this.getByString());
    }

    public LocalDateTime getAt() {
        return convertToDateTime(this.getAtString());
    }

    private String getByString() {
        String action = this.getAction();
        String remainingTokens = this.getRemainingTokens();
        if (action.equals("deadline") && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by", 2)[1].trim();
        }
        return "";
    }

    private String getAtString() {
        String action = this.getAction();
        String remainingTokens = this.getRemainingTokens();
        if (action.equals("event") && remainingTokens.contains("/at")) {
            return remainingTokens.split("/at", 2)[1].trim();
        }
        return "";
    }

    public boolean inputsAreValid() {
        String action = this.getAction();
        String description = this.getDescription();
        String byString = this.getByString();
        String atString = this.getAtString();

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
}