package duke.parser;

import java.util.Locale;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandOption;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.main.Deadline;
import duke.main.DukeException;
import duke.main.Event;
import duke.main.Task;
import duke.main.Todo;

/**
 * Parses the user input and return corresponding Command objects for execution.
 */
public class Parser {

    /**
     * Constructor
     */
    public Parser() {

    }
    /**
     * Parses the user input.
     * 1. Takes in the first word from user input and carries out relevant actions based on
     *      the word by printing out corresponding replies.
     * 2. A command is NOT case sensitive.
     *      For example, "LIST"/"list"/"List" will have the same effect.
     * 3. However, no additional whitespaces should be entered.
     *      For example, "LIST "/"list "/"List " will not work.
     * 4. Disclaimer: the idea of using .valueOf and convert to UpperCase is inspired
     *      based on discussion of #Issue 14 in forum.
     *      Credit to @samuelfangjw who mentioned it first.
     * @param message user input
     * @throws DukeException thrown if user enters a valid command but invalid related information
     *                       or an invalid command
     */
    public Command parseMessage(String message) throws DukeException {
        String[] msgArray = message.split(" ", 2);
        String commandWord = msgArray[0].strip();
        String otherInfo = null;
        if (msgArray.length > 1) {
            otherInfo = msgArray[1].strip();
        }
        Command command;
        try {
            CommandOption commandEnum = CommandOption.valueOf(commandWord.toUpperCase(Locale.ROOT));
            switch (commandEnum) {
            case LIST:
                command = new ListCommand();
                break;
            case DONE:
                command = preCompleteTask(otherInfo);
                break;
            case TODO:
                command = preTodoTask(otherInfo);
                break;
            case EVENT:
                command = preEventTask(otherInfo);
                break;
            case DEADLINE:
                command = preDeadlineTask(otherInfo);
                break;
            case DELETE:
                command = preDeleteTask(otherInfo);
                break;
            case FIND:
                command = preFindTask(otherInfo);
                break;
            default:
                throw new DukeException("Unexpected value: " + commandEnum);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I do not understand this command.");
        }

        return command;
    }

    private Command preCompleteTask(String taskIndex) throws DukeException {
        int index = validateInteger(taskIndex);
        return new DoneCommand(index);
    }

    private Command preDeleteTask(String taskIndex) throws DukeException {
        int index = validateInteger(taskIndex);
        return new DeleteCommand(index);
    }

    private int validateInteger(String taskIndex) throws DukeException {
        int index;
        if (taskIndex == null) {
            throw new DukeException("Please enter a task index.");
        }

        try {
            index = Integer.parseInt(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Task index entered is not an integer.");
        }
        return index;
    }

    private Command preTodoTask(String description) throws DukeException {
        if (description == null) {
            throw new DukeException("Please provide a description when creating todo.");
        }
        return new AddCommand(new Todo(description));
    }

    private Command preDeadlineTask(String otherInfo) throws DukeException {
        String[] validatedInfo = validateOtherInfo(otherInfo, "/by");
        String description = validatedInfo[0];
        String by = validatedInfo[1];
        Task deadline = new Deadline(description, by);
        return new AddCommand(deadline);
    }

    private Command preEventTask(String otherInfo) throws DukeException {
        String[] validatedInfo = validateOtherInfo(otherInfo, "/at");
        String description = validatedInfo[0];
        String at = validatedInfo[1];
        Task event = new Event(description, at);
        return new AddCommand(event);
    }

    private String[] validateOtherInfo(String otherInfo, String splitBy) throws DukeException {
        try {
            String[] temp = otherInfo.split(splitBy, 2);
            String description = temp[0].strip();
            String time = temp[1].strip();
            if (description.equals("") || time.equals("")) {
                throw new DukeException("Please provide a description or a by date "
                        + "when creating deadline.");
            }
            return new String[] {description, time};
        } catch (NullPointerException e) {
            throw new DukeException("Please provide the relevant information "
                    + "when creating a task.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please use /by when creating deadline or "
                    + "/at when creating event.");
        }
    }

    private Command preFindTask(String keyword) throws DukeException {
        if (keyword == null || keyword.equals(" ") || keyword.equals("")) {
            throw new DukeException("Please enter a keyword when finding the task.");
        }
        return new FindCommand(keyword);
    }
}
