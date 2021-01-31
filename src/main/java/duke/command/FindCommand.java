package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        ArrayList<Task> tasks = tm.findTasks(this.searchTerm);
        ui.println("Listing all matching tasks: ");
        ui.showTasks(tasks);

        StringBuilder res = new StringBuilder("Listing all matching tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            res.append(num)
                    .append(": ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        if (tasks.size() == 0) {
            res.append("--- No Tasks Found ---");
        }
        return res.toString();
    }
}
