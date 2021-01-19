package duke.command;

import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TodoCommand extends Command{

    public TodoCommand(ArrayList<Task> list, String line){
        super("");
        Todo t = new Todo(line);
        list.add(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.";
        super.dukeReply();

    }
}
