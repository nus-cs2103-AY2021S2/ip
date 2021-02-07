package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        if (0 <= index && index < tasks.size()) {
            final Task removed = tasks.delete(index);
            String headerText = "\tNoted. I've removed this task: \n";
            String taskRemovedText = String.format("\t%s\n", removed);
            String currentTasksText = String.format("\tNow you have %d task%s in the list.\n", tasks.size(), tasks.size() == 1 ? "" : "s");
            return headerText + taskRemovedText + currentTasksText;
        } else {
            return "\tOops! The index is out of bound.\n";
        }
    }

}
