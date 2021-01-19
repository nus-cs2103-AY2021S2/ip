package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand(ArrayList<Task> list, int index) {
        super("");
        Task t = list.get(index);
        list.remove(index);
        this.reply = "Noted. I've removed this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.";

        super.dukeReply();
    }
}
