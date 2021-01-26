package duke.command;

import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventCommand extends Command{


    public EventCommand(ArrayList<Task> list, String task, LocalDate at){
        super("");
        Event t = new Event(task, at);
        list.add(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.";
        super.dukeReply();

    }
}
