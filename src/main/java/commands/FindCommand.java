package commands;

import java.util.stream.Collectors;

import data.TaskList;
import ui.TextUi;

public class FindCommand extends Command {
    public static final String COMMAND_TEXT = "find";

    private String query;

    /**
     * Creates a Find Command with the given query
     *
     * @param query
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Returns the tasks message that matches the query
     *
     * @param tasks
     * @param ui
     * @return response message
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        TaskList filteredTasks = tasks.stream()
                .filter(t -> t.getDescription().contains(query))
                .collect(Collectors.toCollection(TaskList::new));

        return ui.getTasksMessage(filteredTasks);
    }
}
