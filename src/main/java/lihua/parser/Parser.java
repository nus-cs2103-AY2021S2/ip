package lihua.parser;

import java.time.LocalDate;

import lihua.commands.AddCommand;
import lihua.commands.Command;
import lihua.commands.DeleteCommand;
import lihua.commands.DoneCommand;
import lihua.commands.ExitCommand;
import lihua.commands.FindCommand;
import lihua.commands.HelpCommand;
import lihua.commands.ListCommand;
import lihua.tasks.Deadline;
import lihua.tasks.Event;
import lihua.tasks.ToDo;

public class Parser {

    // need to handle input argument mismatch here
    // e.g. if the input is 'wrong', the program should handle the exception and prompt get-help message
    /**
     * Parses user input string and returns relevant command.
     *
     * @param userInput User input string.
     * @return Relevant command, as specified in the userInput.
     */
    public Command parseUserInput(String userInput) {
        // Allow multiple white spaces between:
        // first argument, center argument(s), and last argument.
        String[] split = userInput.split("\\s+"); // Split by one or more white spaces.
        String firstArgument = split[0].toLowerCase();
        Command command;
        try {
            switch (firstArgument) {
            case "bye":
                command = new ExitCommand();
                break;
            case "todo":
                ToDo todo = getToDo(userInput);
                command = new AddCommand(todo);
                break;
            case "event":
                Event event = getEvent(userInput);
                command = new AddCommand(event);
                break;
            case "deadline":
                Deadline deadline = getDeadline(userInput);
                command = new AddCommand(deadline);
                break;
            case "done":
                command = new DoneCommand(Integer.valueOf(split[1]));
                break;
            case "delete":
                command = new DeleteCommand(Integer.valueOf(split[1]));
                break;
            case "list":
                // try to see if second argument (date) exists
                try {
                    String dateString = split[1];
                    // date exists
                    LocalDate date = LocalDate.parse(dateString);
                    command = new ListCommand(date); // list on a specific date
                } catch (IndexOutOfBoundsException e) {
                    // date does not exists
                    command = new ListCommand(); // list all
                }
                // If date format is incorrect, an exception will be thrown and caught by below catch statement.
                // A helper command would then be prompted.
                break;
            case "help":
                command = new HelpCommand();
                break;
            case "find":
                command = new FindCommand(split[1]);
                break;
            default:
                command = new HelpCommand(false);
                break;
            }
        } catch (Exception e) {
            command = new HelpCommand(false);
        }

        return command;
    }

    /**
     * Extracts a deadline object out of the user input string.
     *
     * @param userInput User input string.
     * @return The deadline object, as specified by the user input string.
     * @throws Exception If the user input string starts with 'deadline' but the format is incorrect,
     * an exception will be thrown.
     */
    private Deadline getDeadline(String userInput) throws Exception {
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
     * @throws Exception If the user input string starts with 'event' but the format is incorrect,
     * an exception will be thrown.
     */
    private Event getEvent(String userInput) throws Exception {
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
     * @throws Exception If the user input string starts with 'todo' but the format is incorrect,
     * an exception will be thrown.
     */
    private ToDo getToDo(String userInput) throws Exception {
        int index = userInput.indexOf("todo") + 4;
        while (userInput.charAt(index) == ' ') {
            index++;
        }

        return new ToDo(userInput.substring(index));
    }
}
