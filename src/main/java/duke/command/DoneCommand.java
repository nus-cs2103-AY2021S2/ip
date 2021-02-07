package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        if (0 <= index && index < tasks.size()) {
            tasks.markAsDone(index);
            String headerText = "\tNice! I've marked this task as done:\n";
            String taskMarkedText = String.format("\t%s\n", tasks.getTaskDescription(index));
            return headerText + taskMarkedText;
        } else {
            return "\tOops! The index is out of bound.\n";
        }
    }

}
