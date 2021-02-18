package com.tanboonji.jhin.command;

import com.tanboonji.jhin.model.TaskList;

/**
 * The FindCommand class contains information to execute the "find" command.
 */
public class FindCommand extends Command {

    private static final String SUCCESS_MATCH_MESSAGE_FORMAT = "Here are the matching tasks in your task list:\n"
            + "%s";
    private static final String SUCCESS_NO_MATCH_MESSAGE_FORMAT =
            "Sorry, I couldn't find any tasks matching '%s' in your task list.";

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
            return String.format(SUCCESS_NO_MATCH_MESSAGE_FORMAT, keyword);
        }

        return String.format(SUCCESS_MATCH_MESSAGE_FORMAT, matchingTask);
    }
}
