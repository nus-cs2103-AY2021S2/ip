package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

/**
 * A Parser class which does all the checking of commands
 * in the programme.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String HELP_COMMAND = "help";

    /**
     * Checks if the command given is "bye"
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "bye"
     */
    public static boolean isBye(String check) {
        return check.equals(BYE_COMMAND);
    }

    /**
     * Checks if the command given is "list"
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "list"
     */
    public static boolean isList(String check) {
        return check.equals(LIST_COMMAND);
    }

    /**
     * Checks if the command given is "done".
     * Function name is named as "isEndOfProgram" to avoid confusion
     * with "isDone" method from other classes.
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "done"
     */
    public static boolean isCompleted(String check) {
        return check.equals(DONE_COMMAND);
    }

    /**
     * Checks if the command given is "todo".
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "todo"
     */
    public static boolean isToDo(String check) {
        return check.equals(TODO_COMMAND);
    }

    /**
     * Checks if the command given is "deadline"
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "deadline"
     */
    public static boolean isDeadline(String check) {
        return check.equals(DEADLINE_COMMAND);
    }

    /**
     * Checks if the command given is "event"
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "event"
     */
    public static boolean isEvent(String check) {
        return check.equals(EVENT_COMMAND);
    }

    /**
     * Checks if the command given is "delete"
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "delete"
     */
    public static boolean isDelete(String check) {
        return check.equals(DELETE_COMMAND);
    }

    /**
     * Checks if the command given is "find"
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "find"
     */
    public static boolean isFind(String check) {
        return check.equals(FIND_COMMAND);
    }

    /**
     * Checks if the command given is "help"
     *
     * @param check the command to be checked
     * @return boolean, where true means command is indeed "help"
     */
    public static boolean isHelp(String check) {
        return check.equals(HELP_COMMAND);
    }

    /**
     * Parses and checks the first word of the input against
     * the command keys before calling on the respective command.
     *
     * @param input typed in by the user
     * @return Command class based on the respective command keyword identified
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] inputList = input.split(" ", 2);
        String command = inputList[0].trim();

        if (isBye(command)) {
            return new ByeCommand(input);
        } else if (isList(command)) {
            return new ListCommand(input);
        } else if (isCompleted(command)) {
            return new DoneCommand(input);
        } else if (isToDo(command) | isEvent(command) | isDeadline(command)) {
            return new AddCommand(input);
        } else if (isDelete(command)) {
            return new DeleteCommand(input);
        } else if (isFind(command)) {
            return new FindCommand(input);
        } else if(isHelp(command)) {
            return new HelpCommand(input);
        } else {
            throw new InvalidCommandException();
        }
    }
}
