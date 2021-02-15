package com.tanboonji.jhin.command;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.TaskList;

/**
 * The FindCommand class contains information to execute the "find" command.
 */
public class FindCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "find";
    private static final String ERROR_MESSAGE = "Sorry, please enter a keyword to search for.\n"
            + "Command: find [keyword]";
    private static final String SUCCESS_MATCH_MESSAGE = "Here are the matching tasks in your list:\n"
            + "%s";
    private static final String SUCCESS_NO_MATCH_MESSAGE =
            "Sorry, I couldn't find any tasks matching '%s' in your list.";

    private final String keyword;

    private FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean shouldSaveData() {
        return false;
    }

    @Override
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        TaskList matchingTask = taskList.find(keyword);

        if (matchingTask.getSize() == 0) {
            return String.format(SUCCESS_NO_MATCH_MESSAGE, keyword);
        }
        return String.format(SUCCESS_MATCH_MESSAGE, matchingTask);
    }

    /**
     * Returns new find command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New find command.
     * @throws JhinException If user input does not match find command format.
     */
    public static FindCommand parseArguments(String arguments) throws JhinException {
        if (arguments.trim().equals("")) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }
        return new FindCommand(arguments);
    }
}
