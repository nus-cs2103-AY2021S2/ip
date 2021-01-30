package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * An EventCommand is when the user wants to add an event task to the list
 */
public class EventCommand extends Command {

    private final String description;
    private final LocalDate at;

    /**
     * Constructor to initialise the task description and when it's /at
     * @param task the task description
     * @param at when the event is /at
     */
    public EventCommand(String task, LocalDate at) {
        super("");
        this.description = task;
        this.at = at;
    }

    /**
     * Executes the command
     * @param ui the ui to respond to the user's input
     * @param s The storage to save the tasklist to
     * @param list The current list of tasks
     * @throws IOException when file fails to be saved
     */
    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        Event t = new Event(this.description, this.at);
        list.addTask(t);
        this.reply = "Got it. I've added this task:\n\t" + t.toString()
                + "\n\tNow you have " + list.getSize() + " task" + (list.getSize() != 1 ? "s " : " ") + "in the list.";
        s.storeData(list.getList());
        ui.reply(this.reply);
    }
}





