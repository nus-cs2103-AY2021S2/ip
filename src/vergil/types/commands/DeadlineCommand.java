package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.Deadline;
import vergil.types.exceptions.VergilException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a 'deadline' command.
 */
public class DeadlineCommand extends Command {
    private String desc;
    private LocalDateTime dateTime;

    /**
     * Constructs a new 'deadline' command.
     *
     * @param   args    the 'deadline' command arguments
     *                  (i.e., the description and date-and-time).
     */
    public DeadlineCommand(String... args) {
        super(CommandType.DEADLINE, args);
    }

    /**
     * Executes the 'deadline' command and saves the list.
     *
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks to add the deadline task to.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the successful addition of the deadline task.
     * @throws VergilException  if there's another task clashing with the task the command is trying
     *                          to add.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        desc = getArgument(0);
        dateTime = LocalDateTime.parse(getArgument(1), DateTimeFormatter.ofPattern("d/M/y HHmm"));

        taskList.add(new Deadline(desc, dateTime));
        storage.save(taskList);

        return ui.getSuccessMessage("'" + desc + "' has been added as a deadline task.");

    }
}
