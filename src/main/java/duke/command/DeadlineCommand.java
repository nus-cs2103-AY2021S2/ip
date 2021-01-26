package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.io.IOException;
import java.time.LocalDate;

public class DeadlineCommand extends Command{

    private final String description;
    private final LocalDate by;


    public DeadlineCommand(String task, LocalDate by){
        super("");
        this.description = task;
        this.by = by;
    }

    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        Deadline t = new Deadline(this.description, this.by);
        list.addTask(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.getSize() + " task" + (list.getSize() != 1 ? "s " : " ") + "in the list.";
        s.storeData(list.getList());
        ui.reply(this.reply);
    }
}
