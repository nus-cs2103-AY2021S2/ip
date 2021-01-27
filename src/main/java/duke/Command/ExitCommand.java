package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
        return EXECUTION_FAIL;
    }
}
