package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilFormatException;
import vergil.types.exceptions.VergilException;

public class DoneCommand extends Command {
    private int taskIndex;
    private String taskAsString;

    public DoneCommand(String... args) {
        super(CommandType.DONE, args);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        try {
            taskIndex = Integer.parseInt(getArgument(0));
            taskAsString = taskList.getAsString(taskIndex);

            taskList.complete(taskIndex);

            return "Success! The following task has been completed:\n"
                    + taskAsString;
        } catch (NumberFormatException e) {
            throw new VergilFormatException("You must enter a number.");
        }
    }
}
