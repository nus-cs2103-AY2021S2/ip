package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super.command = command;
        super.description = "";
        super.date = "";
    }

    private String getTaskListContents(TaskList tasks) {
        String contents = "Here are the tasks in your list:";

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i);
            contents += String.format("\n\t%d.%s", i, task.toString());
        }

        return contents;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        output = getTaskListContents(tasks);
        ui.response(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
