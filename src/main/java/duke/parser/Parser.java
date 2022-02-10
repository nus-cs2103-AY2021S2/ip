package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.PrintCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * A parser for reading user input
 */
public class Parser {
    private String msg;

    /**
     * Constructor for <code>Parser</code>.
     *
     * @param msg a string of input from user
     */
    public Parser(String msg) {
        this.msg = msg;
    }

    /**
     * Process msg and return the request (command) of the input.
     *
     * @return a string of request
     */
    public String getRequest() {
        String[] token = msg.split(" ");
        return token[0];
    }

    /**
     * Process msg and return the arguments of the input.
     *
     * @return a string of arguments
     */
    public String getArgs() throws DukeException {
        String[] token = msg.split(" ");
        String args = "";
        if (token.length <= 1) {
            throw new DukeException("Description is empty!");
        }
        for (int i = 1; i < token.length; i++) {
            args += token[i];
            if (i < token.length - 1) {
                args += " ";
            }
        }
        return args;
    }

    /**
     * Process msg and return the formatted command of the input.
     * This method return an String array of size 3.
     * The first item represents the name of a task.
     * The second item represents the date of a task.
     * The third item represents the preposition of a task.
     *
     * @return an array consisting of name, date, preposition
     * @throws DukeException
     */
    public String[] getFormattedCommand() throws DukeException {
        try {
            String args = getArgs();
            String[] formattedArr = new String[3];
            formattedArr[0] = args.split(" /")[0];
            formattedArr[1] = args.split("/")[1].substring(args.split("/")[1]
                    .split(" ")[0].length() + 1, args.split("/")[1].length());
            formattedArr[2] = args.split("/")[1].split(" ")[0];
            return formattedArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The format you have entered is wrong.");
        }
    }

    /**
     * Parse msg and return the requested command
     *
     * @return a command for executing
     */
    public Command parse() {
        UserRequest request;
        try {
            request = UserRequest.valueOf(getRequest().trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            request = UserRequest.INVALID;
        } catch (NullPointerException ex) {
            request = UserRequest.INVALID;
        }
        switch (request) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            try {
                return new DoneCommand(getArgs());
            } catch (DukeException ex) {
                return new PrintCommand(ex.getMessage());
            }
        case DELETE:
            try {
                return new DeleteCommand(getArgs());
            } catch (DukeException ex) {
                return new PrintCommand(ex.getMessage());
            }
        case TODO:
            try {
                return new AddTodoCommand(new Todo(getArgs()));
            } catch (DukeException ex) {
                return new PrintCommand(ex.getMessage());
            }
        case DEADLINE:
            try {
                String[] deadlineStr = getFormattedCommand();
                assert(!deadlineStr[0].isEmpty());
                assert(!deadlineStr[1].isEmpty());
                assert(!deadlineStr[2].isEmpty());
                return new AddDeadlineCommand(
                        new Deadline(deadlineStr[0], deadlineStr[1], deadlineStr[2]));
            } catch (DukeException ex) {
                return new PrintCommand(ex.getMessage());
            }
        case EVENT:
            try {
                String[] eventStr = getFormattedCommand();
                assert(!eventStr[0].isEmpty());
                assert(!eventStr[1].isEmpty());
                assert(!eventStr[2].isEmpty());
                return new AddEventCommand(
                        new Event(eventStr[0], eventStr[1], eventStr[2]));
            } catch (DukeException ex) {
                return new PrintCommand(ex.getMessage());
            }
        case FIND:
            try {
                return new FindCommand(getArgs());
            } catch (DukeException ex) {
                return new PrintCommand(ex.getMessage());
            }
        case SORT:
            return new SortCommand();
        default:
            return new PrintCommand("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
