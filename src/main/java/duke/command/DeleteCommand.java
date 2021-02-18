package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A DeleteCommand is when the user wants to delete a task from the list
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor to initialise the reply and the index to be deleted
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        super("");
        this.index = index;
    }

    /**
     * Executes the DeleteCommand
     * @param ui The ui to respond to the user's input
     * @param s The storage to save the TaskList to
     * @param list The current list of tasks
     * @return The reply to the DeleteCommand
     * @throws IOException when the list fails to be saved
     */
    public String execute(Ui ui, Storage s, TaskList list) throws IOException {
        Task t = list.getItem(index);
        list.deleteTask(index);
        this.reply = "Noted. I've removed this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.getSize() + " task" + (list.getSize() != 1 ? "s " : " ") + "in the list.";
        s.storeData(list.getList());
        return ui.reply(this.reply);
    }
}
