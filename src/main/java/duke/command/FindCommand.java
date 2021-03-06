package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String fullCommand, String keyword) {
        super(fullCommand);
        this.keyword = keyword;
    }

    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> result = tasks.find(keyword);
        String resp = "";
        for (int i = 0; i < result.size(); i++) {
            resp = resp + (i + 1) + "." + result.get(i).toString();
        }
        return resp;
    }

    public boolean isExit() {
        return false;
    }
}
