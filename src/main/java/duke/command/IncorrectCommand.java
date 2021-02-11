package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class IncorrectCommand extends Command {
    private String message;
    public IncorrectCommand(String message) {
        super();
        this.message = message;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        assert message != null;

        return message;
    }
}
