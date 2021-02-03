package commands;

import data.Event;
import data.TaskList;
import ui.TextUi;

public class AddEventCommand extends Command {
    public static final String COMMAND_TEXT = "event";

    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds event into given tasks and returns acknowledgement message
     *
     * @param tasks
     * @param ui
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        tasks.add(event);
        return ui.getAddTaskMessage(event, tasks);
    }
}
