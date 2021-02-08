package tlylt.haha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Utility class to parse user input.
 */
public class Parser {
    /**
     * Returns task number.
     *
     * @param command String to be parsed.
     * @return Correct task number as an integer.
     * @throws HahaTaskNumberNotIntException Task number given is not an integer.
     */
    static int taskNumber(String command) throws HahaTaskNumberNotIntException {
        try {
            return Integer.parseInt("" + command.charAt(command.length() - 1));
        } catch (NumberFormatException ex) {
            throw new HahaTaskNumberNotIntException(command);
        }
    }

    /**
     * Helps to separate user input into two strings.
     *
     * @param command User input string.
     * @return Command + details.
     */
    static String[] tokenize(String command) {
        assert command.length() > 0 : "command is too short";
        return command.split(" ", 2);
    }

    /**
     * Handles edge case of an empty command.
     *
     * @param input User input string.
     * @throws HahaEmptyCommandException Command given is empty.
     */
    static void handleEmptyCommand(String input) throws HahaEmptyCommandException {
        if (input.isEmpty()) {
            throw new HahaEmptyCommandException(input);
        }
    }

    /**
     * Gets the date in LocalDate.
     *
     * @param str User input string.
     * @return Parsed date in LocalDate.
     */
    static LocalDate getDate(String str) {
        if (str.contains("/at")) {
            return LocalDate.parse(str.split("/at ")[1].split(" ")[0]);
        } else {
            return LocalDate.parse(str.split("/by ")[1].split(" ")[0]);
        }
    }

    /**
     * Gets the time in LocalTime.
     *
     * @param str User input string.
     * @return Parsed time in LocalTime.
     */
    static LocalTime getTime(String str) {
        String[] token = str.split(" ");
        return LocalTime.parse(token[token.length - 1]);
    }

    /**
     * Handles edge case of an command without it's required description.
     *
     * @param input User input string.
     * @throws HahaEmptyDescriptionException Command's required description is missing.
     */
    static void handleEmptyDescriptionCommand(String input) throws HahaEmptyDescriptionException {
        // TaskType
        String task = Parser.tokenize(input)[0];
        String description = Parser.tokenize(input).length == 2 ? Parser.tokenize(input)[1] : "";
        // Check input has description following task command words
        if (Arrays
                .stream(TaskType.values())
                .map(TaskType::getRep)
                .anyMatch(x -> x.equals(task))
                &&
                description.isEmpty()
        ) {
            throw new HahaEmptyDescriptionException(input);
        }
    }

    /**
     * Handles invalid commands.
     *
     * @param input User input string.
     * @throws HahaWrongCommandException Command not recognized.
     */
    static void handleWrongCommand(String input) throws HahaWrongCommandException {
        // Check input starts with specified command words
        if (Arrays
                .stream(LegitCommand.values())
                .map(LegitCommand::getRep)
                .noneMatch(command -> Parser.tokenize(input)[0].equals(command))) {
            throw new HahaWrongCommandException(input);
        }
    }

    /**
     * Ensures safety of given command.
     *
     * @param input User input string.
     * @throws HahaEmptyCommandException     Command is empty.
     * @throws HahaWrongCommandException     Command is not recognized.
     * @throws HahaEmptyDescriptionException Command does not have required description.
     */
    static void handleSafety(String input) throws HahaEmptyCommandException,
            HahaWrongCommandException,
            HahaEmptyDescriptionException {
        Parser.handleEmptyCommand(input);
        Parser.handleWrongCommand(input);
        Parser.handleEmptyDescriptionCommand(input);
    }

    /**
     * Interprets command and returns respective command using LegitCommand Enum.
     *
     * @param command User input string.
     * @return Proper command specified in LegitCommand.
     */
    static LegitCommand handleInterpret(String command) {
        String[] tokenized = Parser.tokenize(command);
        String firstWord = tokenized[0];
        switch (firstWord) {
        case "todo":
            LegitCommand.TODO.setDetail(tokenized[1]);
            return LegitCommand.TODO;
        case "deadline":
            LegitCommand.DEADLINE.setDetail(tokenized[1]);
            return LegitCommand.DEADLINE;
        case "event":
            LegitCommand.EVENT.setDetail(tokenized[1]);
            return LegitCommand.EVENT;
        case "list":
            return LegitCommand.LIST;
        case "done":
            LegitCommand.DONE.setDetail(tokenized[1]);
            return LegitCommand.DONE;
        case "delete":
            LegitCommand.DELETE.setDetail(tokenized[1]);
            return LegitCommand.DELETE;
        case "find":
            LegitCommand.FIND.setDetail(tokenized[1]);
            return LegitCommand.FIND;
        case "bye":
            return LegitCommand.BYE;
        default:
            assert false : firstWord;
            throw new IllegalStateException("Unexpected value: " + firstWord);
        }
    }

    /**
     * Parses user input string.
     *
     * @param input User input string.
     * @return Parsed LegitCommand.
     * @throws HahaEmptyCommandException     Command is empty.
     * @throws HahaWrongCommandException     Command is not recognized.
     * @throws HahaEmptyDescriptionException Command does not have required description.
     */
    static LegitCommand parseInput(String input) throws HahaEmptyCommandException,
            HahaWrongCommandException,
            HahaEmptyDescriptionException {
        // Safety checks
        Parser.handleSafety(input);
        // Interpret
        return Parser.handleInterpret(input);
    }

    /**
     * Checks each line and generates corresponding task.
     *
     * @param line A line of User input.
     * @return Constructed task.
     */
    static Task parseLine(String line) {
        String[] tokens = line.split("\\|");
        String type = tokens[0];
        boolean isDone = tokens[1].equals("true");
        String description = tokens[2];
        Task task;
        switch (type) {
        case "T":
            task = new Todo(isDone, description);
            break;
        case "D":
            task = new Deadline(isDone, description);
            break;
        case "E":
            task = new Event(isDone, description);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        return task;
    }
}
