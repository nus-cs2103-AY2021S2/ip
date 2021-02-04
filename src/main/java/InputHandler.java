import CustomExceptions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class InputHandler {
    private final String input;
    private final ArrayList<String> validActions
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

    public String getBy() {
        String action = this.getAction();
        String remainingTokens = this.getRemainingTokens();

        if (action.equals("deadline") && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by", 2)[1].trim();
        }
        return "";
    }

    public String getAt() {
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

        try {
            if (!this.validActions.contains(action)) {
                throw new InvalidActionException(action);
            }

            if ((!action.equals("bye") && !action.equals("list")) & description.length() == 0) {
                throw new MissingDescriptionException(action);
            }

            if ((action.equals("done") || action.equals("delete")) && (!isInteger(description))) {
                throw new InvalidTaskNumberException();
            }

            if (action.equals("deadline") && this.getBy().length() == 0) {
                throw new MissingDeadlineException();
            }

            if (action.equals("event") && this.getAt().length() == 0) {
                throw new MissingEventTimeException();
            }

        } catch (MissingDescriptionException
                | InvalidActionException
                | InvalidTaskNumberException
                | MissingDeadlineException
                | MissingEventTimeException e) {
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
}
