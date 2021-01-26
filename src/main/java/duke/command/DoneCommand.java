package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;

public class DoneCommand extends Command{

    private int index;

    public DoneCommand(int index) {
        super("");
        this.index = index;
    }

    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        Task t = list.getItem(index);
        t.done();
        this.reply = "Nice! I've marked this task as done:\n\t  "
                + t.toString();
        s.storeData(list.getList());
        ui.reply(this.reply);
    }
}

