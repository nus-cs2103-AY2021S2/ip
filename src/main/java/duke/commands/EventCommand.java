package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * class EventCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "event"
 */
public class EventCommand extends Command{
    private String eventDescription;
    private String period;

    /**
     * Constructor: creates a new EventCommand
     * @param eventDescription description of Event
     * @param period period of Event
     */
    public EventCommand(String eventDescription, String period) {
        this.eventDescription = eventDescription;
        this.period = period;
    }

    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task event = new Event(eventDescription, period);
            tasks.addTask(event);
            ui.showAddTask(event, tasks);
            storage.saveFile(tasks.getTaskList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * isExit: checks if Duke should terminate
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
