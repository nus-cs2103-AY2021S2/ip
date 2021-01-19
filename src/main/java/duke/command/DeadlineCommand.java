package duke.command;

import duke.task.Deadline;
import duke.task.Task;

import java.util.ArrayList;

public class DeadlineCommand extends Command{


    public DeadlineCommand(ArrayList<Task> list, String task, String by){
        super("");
        Deadline t = new Deadline(task, by);
        list.add(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.";
        super.dukeReply();

    }
}
