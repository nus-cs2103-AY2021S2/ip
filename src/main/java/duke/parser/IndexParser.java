package duke.parser;

import duke.exception.DescriptionMissingException;
import duke.exception.InvalidPriorityException;

/**
 * A class represents an IndexParser.
 */
public class IndexParser implements Parser {
    /**
     * Return the index contained in the input string.
     * @param input The input string given by user.
     * @return The index contained in the input string.
     * @throws DescriptionMissingException If the index is not in the input string.
     */
    public static int parseIndex(String input, int sepIndex) throws DescriptionMissingException {
        if (sepIndex == -1) {
            throw new DescriptionMissingException("Please specify the index!");
        }
        String[] instructions = input.substring(sepIndex).trim().split(" ");
        try {
            assert(instructions.length > 0);
            // Rule out the possible effect by "set" statement
            String indexInString = instructions[0].replace("/as", "").trim();
            return Integer.parseInt(indexInString) - 1;
        } catch (NumberFormatException e) {
            throw new DescriptionMissingException("Input index is not valid");
        }
    }

    /**
     * Returns the priority contained in the input string.
     * @param input The input string given by the user.
     * @return The priority contained in the string.
     * @throws DescriptionMissingException If the priority is not a valid one.
     */
    public static int parsePriority(String input) throws DescriptionMissingException, InvalidPriorityException {
        missingAsAlert(input.trim());
        String[] instructions = input.trim().split("/as");

        if (instructions.length < 2) {
            throw new DescriptionMissingException("Please specify the priority!");
        }
        try {
            String priorityInString = instructions[1].trim();
            int priority = Integer.parseInt(priorityInString);
            if (priority < 1 || priority > 3) {
                throw new InvalidPriorityException("Input priority should be between 1 - 3!");
            }
            return priority;
        } catch (NumberFormatException e) {
            throw new DescriptionMissingException("Input priority is not valid");
        }
    }

    private static void missingAsAlert(String input) throws DescriptionMissingException {
        int indexOfBy = input.indexOf("/as");
        if (indexOfBy == -1) {
            throw new DescriptionMissingException("Please make sure you have /as in your input.");
        }
    }
}
