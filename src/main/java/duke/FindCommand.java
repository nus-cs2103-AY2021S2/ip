package duke;

import java.util.ArrayList;

public class FindCommand extends Command {

    FindCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    public String execute(TaskManager taskManager, Ui ui, Storage storage) {
        String keyword = parsedCommand[1];
        ArrayList<Task> list = taskManager.retrieveMatchingTasks(keyword);
        return ui.showMatchingTasks(list);
    }
}
