package com.tanboonji.jhin.command;

import com.tanboonji.jhin.model.TaskList;

/**
 * The FindCommand class contains information to execute the "find" command.
 */
public class FindCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "find";
    private static final String SUCCESS_MATCH_MESSAGE = "Here are the matching tasks in your list:\n"
            + "%s";
    private static final String SUCCESS_NO_MATCH_MESSAGE =
            "Sorry, I couldn't find any tasks matching '%s' in your list.";

    private final String keyword;

    /**
     * Class constructor specifying keyword to be searched in task list.
     *
     * @param keyword Keyword to be searched in task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
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
}
