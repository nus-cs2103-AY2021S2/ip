package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.Deadline;
import vergil.types.exceptions.VergilException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {
    private String desc;
    private LocalDateTime dateTime;

    public DeadlineCommand(String... args) {
        super(CommandType.DEADLINE, args);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        desc = getArgument(0);
        dateTime = LocalDateTime.parse(getArgument(1), DateTimeFormatter.ofPattern("d/M/y HHmm"));

        taskList.add(new Deadline(desc, dateTime));

        return ui.getSuccessMessage("'" + desc + "' has been added as a deadline task.");

    }
}
