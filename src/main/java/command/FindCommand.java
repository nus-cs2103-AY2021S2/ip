package command;
import entry.Task;
import oracle.TaskList;
import oracle.Ui;

import java.util.ArrayList;

public class FindCommand implements Command {

    private final String keyword;

    /**
     * @param keyword: String of the keyword to be searched
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @param ui: we give the user the results of the search
     * @param tasks: we find any relevant tasks from this TaskList
     * @return true: tells Oracle whether the loop should be terminated
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t.toString().contains(this.keyword)) {
                results.add(t);
            }
        }
        ui.showSearchResults(this.keyword, results);
        return true;
    }
}
