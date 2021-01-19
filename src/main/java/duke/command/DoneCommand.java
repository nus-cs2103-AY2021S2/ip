package duke.command;

import duke.task.*;

import java.util.ArrayList;

public class DoneCommand extends Command{
    public DoneCommand(ArrayList<Task> list, int index) {
        super("");
        Task t = list.get(index);
        t.done();
        this.reply = "Nice! I've marked this task as done:\n\t  "
                + t.toString();
        super.dukeReply();
    }
}

