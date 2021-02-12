package duke.command;

import duke.TaskList;

public class SearchCommand extends Command {
    private String searchTerm;

    /**
     * Create command to search for Task with a particular substring.
     *
     * @param searchTerm Case and whitespace sensitive search input
     */
    public SearchCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String[] getCommandParameters() {
        String[] searchTerm = {this.searchTerm};
        return searchTerm;
    }

    @Override
    public TaskList.Action getType() {
        return TaskList.Action.SEARCH;
    }
}
