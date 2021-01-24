package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


public abstract class Command {
    int targetIndex;

    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public abstract boolean isExit();
    public abstract void execute(Ui ui, TaskList taskList, Storage storage);

}
