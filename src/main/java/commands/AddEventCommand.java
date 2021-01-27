package commands;

import java.io.IOException;

import data.Event;
import data.TaskList;
import ui.TextUi;

public class AddEventCommand extends Command {
    public static final String COMMAND_TEXT = "event";

    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        tasks.add(event);
        ui.writeAddTask(event, tasks);
    }
}
