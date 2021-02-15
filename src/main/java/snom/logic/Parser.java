package snom.logic;

import java.util.Arrays;

import snom.common.core.Messages;
import snom.common.exceptions.SnomException;
import snom.logic.commands.AddCommand;
import snom.logic.commands.Command;
import snom.logic.commands.CommandEnum;
import snom.logic.commands.DeleteCommand;
import snom.logic.commands.ExitCommand;
import snom.logic.commands.FindCommand;
import snom.logic.commands.FinishCommand;
import snom.logic.commands.HelpCommand;
import snom.logic.commands.ListCommand;

/**
 * Represents a Parser to parse user input into {@code Command}
 */
public class Parser {

    /**
     * Returns a {@code Command} child object based on the given string of command.
     *
     * @param userInput     string of user input
     * @return               Command child classes
     * @throws SnomException if command is unknown
     */
    public static Command parse(String userInput) throws SnomException {
        String commandStr = parseCommandStr(userInput);
        String commandContent = parseCommandContent(userInput);
        CommandEnum commandEnum = CommandEnum.getCommand(commandStr);
        switch(commandEnum) {
        case LIST:
            return new ListCommand(CommandEnum.LIST, commandContent);
        case FINISH:
            return new FinishCommand(CommandEnum.FINISH, commandContent);
        case DELETE:
            return new DeleteCommand(CommandEnum.DELETE, commandContent);
        case BYE:
            return new ExitCommand(CommandEnum.BYE, commandContent);
        case TODO:
            return new AddCommand(CommandEnum.TODO, commandContent);
        case DEADLINE:
            return new AddCommand(CommandEnum.DEADLINE, commandContent);
        case EVENT:
            return new AddCommand(CommandEnum.EVENT, commandContent);
        case FIND:
            return new FindCommand(CommandEnum.FIND, commandContent);
        case HELP:
            return new HelpCommand(CommandEnum.HELP, commandContent);
        default:
            throw new SnomException(String.format(Messages.ERROR_INVALID_COMMAND, commandStr));
        }
    }

    /**
     * Returns the command string from user input
     *
     * @param userInput user input
     * @return          command as string
     */
    public static String parseCommandStr(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Returns the content of the command from user input
     *
     * @param userInput user input
     * @return          content of the command
     */
    public static String parseCommandContent(String userInput) {
        String[] splitInputs = userInput.split(" ", 2);
        if (splitInputs.length >= 2) {
            return splitInputs[1];
        } else {
            return "";
        }
    }

    /**
     * Returns an array of task numbers to be finished/deleted
     *
     * @param taskNumString  string containing all the task numbers
     * @return               array of task numbers
     * @throws SnomException if no numbers was given
     */
    public static int[] parseTaskNumbers(String taskNumString) throws SnomException {
        if (taskNumString.isBlank()) {
            throw new SnomException(Messages.ERROR_INVALID_MIN_TASK_NUM);
        }

        try {
            int[] taskNumbers = Arrays.stream(taskNumString.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return taskNumbers;
        } catch (NumberFormatException e) {
            throw new SnomException(Messages.ERROR_INVALID_MIN_TASK_NUM_TYPE);
        }
    }
}
