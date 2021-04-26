package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.Event;
import vergil.types.exceptions.VergilException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an 'event' command.
 */
public class EventCommand extends Command {
    private String desc;
    private LocalDateTime dateTime;

    /**
     * Constructs a new 'event' command.
     *
     * @param   args    the 'event' command arguments
     *                  (i.e., the description and date-and-time).
     */
    public EventCommand(String... args) {
        super(CommandType.EVENT, args);
    }

    /**
     * Executes the 'event' command and saves the list.
     *
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks to add the event task to.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the successful addition of the event task.
     * @throws VergilException  if there's another task clashing with the task the command is trying
     *                          to add.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        desc = getArgument(0);
        dateTime = LocalDateTime.parse(getArgument(1), DateTimeFormatter.ofPattern("d/M/y HHmm"));

        taskList.add(new Event(desc, dateTime));
        storage.save(taskList);

        return ui.getSuccessMessage("'" + "' has been added as an event task.");
    }
}
