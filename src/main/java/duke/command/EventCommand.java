package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {

    private final String description;
    private final LocalDate at;


    public EventCommand(String task, LocalDate at) {
        super("");
        this.description = task;
        this.at = at;
    }


    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        Event t = new Event(this.description, this.at);
        list.addTask(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.getSize() + " task" + (list.getSize() != 1 ? "s " : " ") + "in the list.";
        s.storeData(list.getList());
        ui.reply(this.reply);
    }
}





