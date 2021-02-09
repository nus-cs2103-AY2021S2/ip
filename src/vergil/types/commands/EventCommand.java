package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.Event;
import vergil.types.exceptions.VergilException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command {
    private String desc;
    private LocalDateTime dateTime;

    public EventCommand(String... args) {
        super(CommandType.EVENT, args);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        desc = getArgument(0);
        dateTime = LocalDateTime.parse(getArgument(1), DateTimeFormatter.ofPattern("d/M/y HHmm"));

        taskList.add(new Event(desc, dateTime));

        return ui.getSuccessMessage("'" + "' has been added as an event task.");
    }
}
