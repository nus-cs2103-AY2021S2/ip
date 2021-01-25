package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String[] commandSplit) {
        super(commandSplit);
    }

    public void execute(TaskList task) throws DukeException {
        task.rewriteTasks();
        Ui.printWithStyle("Bye. Hope to see you again soon!");
    }
}
