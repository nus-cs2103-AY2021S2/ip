package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;
import vergil.types.exceptions.VergilFormatException;

public class DeleteCommand extends Command {
    private int taskIndex;
    private String taskAsString;

    public DeleteCommand(String... args) {
        super(CommandType.DELETE, args);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        try {
            taskIndex = Integer.parseInt(getArgument(0));
            taskAsString = taskList.getAsString(taskIndex);

            taskList.delete(taskIndex);

            return "Acknowledged. The following task has been deleted:\n"
                    + taskAsString;
        } catch (NumberFormatException e) {
            throw new VergilFormatException("You must enter a number.");
        }
    }
}
