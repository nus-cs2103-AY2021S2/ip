package duke.command;

import duke.TaskList;

public class SearchCommand extends Command{
    private String searchTerm;

    public SearchCommand(String searchTerm){
        this.searchTerm = searchTerm;
    }

    @Override
    public String[] run() {
        String[] searchTerm = {this.searchTerm};
        return searchTerm;
    }

    @Override
    public TaskList.Action getType() {
        return TaskList.Action.SEARCH;
    }
}
