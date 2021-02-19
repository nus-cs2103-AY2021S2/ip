package lihua.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import lihua.commands.AddCommand;
import lihua.commands.Command;
import lihua.commands.DeleteCommand;
import lihua.commands.DoneCommand;
import lihua.commands.ExitCommand;
import lihua.commands.FindCommand;
import lihua.commands.HelpCommand;
import lihua.commands.ListCommand;
import lihua.commons.enums.ListTagCode;
import lihua.tasks.Deadline;
import lihua.tasks.Event;
import lihua.tasks.ToDo;

public class Parser {

    /**
     * Parses user input string and returns relevant command.
     *
     * @param userInput User input string.
     * @return Relevant command, as specified in the userInput.
     */
    public Command parseUserInput(String userInput) {
        assert userInput != null;
        // Allow multiple white spaces between:
        // first argument, center argument(s), and last argument.
        String[] split = userInput.split("\\s+"); // Split by one or more white spaces.
        Command command;
        try {
            String firstArgument = split[0].toLowerCase();
            command = parseUserInput(userInput, firstArgument, split);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            command = new HelpCommand(false);
        }
        assert command != null;
        return command;
    }

    /**
     * Parses user input string and returns relevant command.
     *
     * @param userInput User input string.
     * @param firstArgument First argument in the user input string.
     * @param split User input string. Split by one or more white spaces.
     * @return Relevant command, as specified in the userInput.
     * @throws IndexOutOfBoundsException If the command is not in the correct format, then an exception will be thrown.
     */
    private Command parseUserInput(String userInput, String firstArgument, String[] split)
            throws IndexOutOfBoundsException {
        assert firstArgument.toLowerCase().equals(firstArgument);
        switch (firstArgument) {
        case "bye":
            return new ExitCommand();
        case "todo":
            return new AddCommand(getToDo(userInput));
        case "event":
            return new AddCommand(getEvent(userInput));
        case "deadline":
            return new AddCommand(getDeadline(userInput));
        case "done":
            // exception could be thrown here, if first argument is not of integer format
            return new DoneCommand(Integer.valueOf(split[1]));
        case "delete":
            // same as above
            return new DeleteCommand(Integer.valueOf(split[1]));
        case "list":
            return getListCommand(userInput, split);
        case "help":
            return new HelpCommand(true);
        case "find":
            return new FindCommand(split[1]);
        default:
            return new HelpCommand(false);
        }
    }

    private Command getListCommand(String userInput, String[] split) {
        try {
            String[] tagSplit = userInput.split("-");
            String tag = tagSplit[1].split("\\s+")[0];
            if (tag.equals("time")) {
                return new ListCommand(ListTagCode.TIME_NORMAL);
            }

            String dateString = split[1];
            LocalDate date = LocalDate.parse(dateString);
            return new ListCommand(date);
        } catch (IndexOutOfBoundsException e) {
            return new ListCommand();
        } catch (DateTimeParseException e) {
            return new HelpCommand(false);
        }
    }

    /**
     * Extracts a deadline object out of the user input string.
     *
     * @param userInput User input string.
     * @return The deadline object, as specified by the user input string.
     * @throws IndexOutOfBoundsException If the user input string starts with 'deadline' but the format is incorrect,
     * an exception will be thrown.
     */
    private Deadline getDeadline(String userInput) throws IndexOutOfBoundsException {
        assert userInput != null;
        String[] split = userInput.split("\\s+/by\\s+");
        String date = split[1]; // Assume the argument is correct, or an Exception will be thrown.
        userInput = split[0];

        int index = userInput.indexOf("deadline") + 8;
        while (userInput.charAt(index) == ' ') {
            index++;
        }
        String content = userInput.substring(index);

        return new Deadline(content, LocalDate.parse(date));
    }

    /**
     * Extracts an event object out of the user input string.
     *
     * @param userInput User input string.
     * @return The event object, as specified by the user input string.
     * @throws IndexOutOfBoundsException If the user input string starts with 'event' but the format is incorrect,
     * an exception will be thrown.
     */
    private Event getEvent(String userInput) throws IndexOutOfBoundsException {
        assert userInput != null;
        String[] split = userInput.split("\\s+/at\\s+");
        String date = split[1]; // Assume the argument is correct, or an Exception will be thrown.
        userInput = split[0];

        int index = userInput.indexOf("event") + 5;
        while (userInput.charAt(index) == ' ') {
            index++;
        }
        String content = userInput.substring(index);

        return new Event(content, LocalDate.parse(date));
    }


    /**
     * Extracts a todo object out of the user input string.
     *
     * @param userInput User input string.
     * @return The todo object, as specified by the user input string.
     * @throws IndexOutOfBoundsException If the user input string starts with 'todo' but the format is incorrect,
     * an exception will be thrown.
     */
    private ToDo getToDo(String userInput) throws IndexOutOfBoundsException {
        assert userInput != null;
        int index = userInput.indexOf("todo") + 4;
        while (userInput.charAt(index) == ' ') {
            index++;
        }

        return new ToDo(userInput.substring(index));
    }
}
