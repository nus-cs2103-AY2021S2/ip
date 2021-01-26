package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("Bye. Hope to see you again soon!");
        this.isBye = true;
    }

    public void execute(Ui ui, Storage s, TaskList list) {
        ui.reply(this.reply);
    }

}
