package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

import java.io.IOException;

public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String task) {
        super("");
        this.description = task;
    }


    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        Todo t = new Todo(this.description);
        list.addTask(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.getSize() + " task" + (list.getSize() != 1 ? "s " : " ") + "in the list.";

        s.storeData(list.getList());
        ui.reply(this.reply);

    }
}






