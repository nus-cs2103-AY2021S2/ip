package commands;

import java.io.IOException;
import java.util.stream.Collectors;

import data.TaskList;
import ui.TextUi;

public class FindCommand extends Command {
    public static final String COMMAND_TEXT = "find";

    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        TaskList filteredTasks = tasks.stream()
            .filter(t -> t.getDescription().contains(query))
            .collect(Collectors.toCollection(TaskList::new));

        ui.writeTasks(filteredTasks);
    }
}
