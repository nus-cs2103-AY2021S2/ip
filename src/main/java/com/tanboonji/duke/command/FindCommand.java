package com.tanboonji.duke.command;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.exception.InvalidCommandArgumentException;
import com.tanboonji.duke.model.Task;

/**
 * The FindCommand class contains information to execute the "find" command.
 */
public class FindCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "find";
    private static final String ERROR_MESSAGE = "Sorry, please enter a keyword to search for.\n"
            + "\tCommand: find [keyword]";

    private final String keyword;

    private FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean shouldSaveData() {
        return false;
    }

    @Override
    public boolean shouldExitDuke() {
        return false;
    }

    @Override
    public String execute() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks in your list:\n");
        int numbering = 1;
        for (Task task: taskList.getList()) {
            if (task.containsText(keyword)) {
                builder.append("\t")
                        .append(numbering++)
                        .append(".")
                        .append(task)
                        .append("\n");
            }
        }

        if (numbering == 1) {
            builder.append("\tYou currently have 0 tasks.");
        }
        return builder.toString().trim();
    }

    /**
     * Returns new find command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New find command.
     * @throws DukeException If user input does not match find command format.
     */
    public static FindCommand parseArguments(String arguments) throws DukeException {
        if (arguments.trim().equals("")) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }
        return new FindCommand(arguments);
    }
}
