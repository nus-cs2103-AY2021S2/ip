package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * A DeadlineCommand is the command when the user wants to add a deadline task
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final LocalDate by;

    /**
     * Constructor to initialise the description of the task and when it has to be done /by
     * @param task task description
     * @param by when the task has to be done /by
     */
    public DeadlineCommand(String task, LocalDate by) {
        super("");
        this.description = task;
        this.by = by;
    }

    /**
     * Executes the DeadlineCommand
     * @param ui the ui to respond to the user's input
     * @param s The storage to save the tasklist to
     * @param list The current list of tasks
     * @throws IOException when the list fails to be saved
     */
    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        Deadline t = new Deadline(this.description, this.by);
        list.addTask(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.getSize() + " task" + (list.getSize() != 1 ? "s " : " ") + "in the list.";
        s.storeData(list.getList());
        ui.reply(this.reply);
    }
}
