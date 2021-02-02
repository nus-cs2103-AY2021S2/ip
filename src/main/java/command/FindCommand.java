package main.java.command;

import java.util.List;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;

/**
 * Command to find tasks associated with a search keyword
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Create a search command to search tasks with a specific keyword
     * @param keyword keyword string to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Call task manager to search for keywords
     * then call ui to display the taks
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     */
    @Override
    public void execute(TaskManager tm, Ui ui) {
        List<Task> result = tm.findByKeyword(keyword);
        ui.displaySearchResult(result);
    }
}
