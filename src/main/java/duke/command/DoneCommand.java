package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * A DoneCommand is when the user wants to mark a task as done
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor to initialise the reply and the index to be marked as done
     * @param index index of task to be marked as done
     */
    public DoneCommand(int index) {
        super("");
        this.index = index;
    }


    /**
     * Executes the DoneCommand
     * @param ui The ui to respond to the user's input
     * @param s The storage to save the TaskList to
     * @param list The current list of tasks
     * @return The reply to the DoneCommand
     * @throws IOException when the file fails to be saved
     */
    public String execute(Ui ui, Storage s, TaskList list) throws IOException {
        Task t = list.getItem(index);
        t.done();
        this.reply = "Nice! I've marked this task as done:\n\t  "
                + t.toString();
        s.storeData(list.getList());
        return ui.reply(this.reply);
    }
}

