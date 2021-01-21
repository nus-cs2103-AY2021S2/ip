import java.util.Arrays;

public class Parser {
    static int taskNumber(String command) throws HahaTaskNumberNotIntException {
        try {
            return Integer.parseInt("" + command.charAt(command.length() - 1));
        } catch (NumberFormatException ex) {
            throw new HahaTaskNumberNotIntException(command);
        }
    }

    static String[] tokenize(String command) {
        return command.split(" ", 2);
    }

    static void handleEmptyCommand(String input) throws HahaEmptyCommandException {
        if (input.isEmpty()) {
            throw new HahaEmptyCommandException(input);
        }
    }

    static void handleEmptyDescriptionCommand(String input) throws HahaEmptyDescriptionException {
        // TaskType
        String task = Parser.tokenize(input)[0];
        String description = Parser.tokenize(input).length == 2 ? Parser.tokenize(input)[1] : "";
        // Check input has description following task command words
        if (Arrays
                .stream(TaskType.values())
                .map(TaskType::getRep)
                .anyMatch(x -> x.equals(task)) &&
                description.isEmpty()
        ) {
            throw new HahaEmptyDescriptionException(input);
        }
    }

    static void handleWrongCommand(String input) throws HahaWrongCommandException {
        // Check input starts with specified command words
        if (Arrays
                .stream(LegitCommand.values())
                .map(LegitCommand::getRep)
                .noneMatch(command -> Parser.tokenize(input)[0].equals(command))) {
            throw new HahaWrongCommandException(input);
        }
    }


    static void handleSafety(String input) throws HahaEmptyCommandException,
            HahaWrongCommandException,
            HahaEmptyDescriptionException {
        Parser.handleEmptyCommand(input);
        Parser.handleWrongCommand(input);
        Parser.handleEmptyDescriptionCommand(input);
    }

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
            case "bye":
                return LegitCommand.BYE;
            default:
                throw new IllegalStateException("Unexpected value: " + firstWord);
        }
    }

    static LegitCommand parseInput(String input) throws HahaEmptyCommandException,
            HahaWrongCommandException,
            HahaEmptyDescriptionException {
        // Safety checks
        Parser.handleSafety(input);
        // Interpret
        return Parser.handleInterpret(input);
    }
}
