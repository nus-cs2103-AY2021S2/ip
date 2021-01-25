package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String commandType) {
        super.commandType = commandType;
        super.commandDetails = "";
        super.dateTime = "";
        super.outputMessage = "";
        super.index = -1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)  {
        this.outputMessage = " Bye. Hope to see you again soon!";
        ui.display(outputMessage);
    }

    @Override
    public boolean continueInput() {
        return false;
    }
}
