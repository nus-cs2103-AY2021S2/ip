package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;
    public DeleteCommand(int index) {
        super("");
        this.index = index;
    }

    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        Task t = list.getItem(index);
        list.deleteTask(index);
        this.reply = "Noted. I've removed this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.getSize() + " task" + (list.getSize() != 1 ? "s " : " ") + "in the list.";
        s.storeData(list.getList());
        ui.reply(this.reply);
    }
}
