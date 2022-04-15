package duke.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.exceptions.DukeExceptionIllegalCommand;

/**
 * Class exclusively to tokenize user input
 */
public final class UserInputTokenizer {

    private UserInputTokenizer() {}

    /**
     * Returns a UserInputTokenSet parsed from user input.
     *
     * All commands should strictly follow the following format:
     * <pre>{@code <command> <text> [/<option> <argument>]* }</pre>
     *
     * In particular:
     * 1. Command and options should not contain spaces.
     * 2. Command and options must be case-insensitive.
     * 3. Options should be strictly prefixed with one '/', and more '/' should not be contained.
     * 4. Leading and trailing whitespaces for each token are stripped.
     * 5. Repeated whitespaces are collapsed.
     * 6. Duplicate options resolve to last specified
     *
     * Commands are stored in '/command' key, and corresponding text in '/text' key,
     * to disambiguate from potential 'command' and 'text' options, and avoids accidental
     * overwriting of command.
     *
     * @param userInput user input string from program
     * @return UserInputTokenSet
     * @see UserInputTokenSet
     */
    public static UserInputTokenSet parse(String userInput) throws DukeExceptionIllegalArgument {
        String[] t = userInput.strip().split("\\s+"); // tokenize and remove duplicate whitespaces
        Queue<String> tokens = new LinkedList<>(Arrays.asList(t));
        UserInputTokenSet tokenSet = new UserInputTokenSet();

        // No command
        assert tokens.peek() != null;
        if (tokens.peek().isEmpty()) {
            throw new DukeExceptionIllegalCommand("Command must be provided.");
        }

        // Read command and text
        tokenSet.set("/command", extractCommand(tokens));
        tokenSet.set("/text", extractText(tokens));

        // Process arguments
        while (!tokens.isEmpty()) {
            String optionToken = extractOption(tokens);
            tokenSet.set(optionToken, extractText(tokens));
        }
        return tokenSet;
    }

    private static String extractCommand(Queue<String> tokens) {
        assert !tokens.isEmpty();
        return tokens.poll().toLowerCase();
    }

    private static String extractOption(Queue<String> tokens) {
        assert !tokens.isEmpty();
        String option = tokens.poll().toLowerCase();
        if (option.startsWith("/")) { // removes option indicator
            return option.substring(1);
        }
        return option;
    }

    private static String extractText(Queue<String> tokens) {
        List<String> textTokens = new ArrayList<>();
        while (!tokens.isEmpty()) { // note: tokens can be empty
            if (isOptionToken(tokens.peek())) {
                break;
            }
            textTokens.add(tokens.poll());
        }
        return String.join(" ", textTokens);
    }

    /** Returns true if only one '/' exists, specifically at start of string */
    private static boolean isOptionToken(String token) {
        return token.startsWith("/") && (token.lastIndexOf("/") == 0);
    }
}
