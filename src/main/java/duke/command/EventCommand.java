package duke.command;

import duke.task.EventTask;

public class EventCommand extends AddCommand {
    public EventCommand(final String content) {
        super(new EventTask(content.split("/at")[0].trim(), content.split("/at")[1].trim()));
    }
}
