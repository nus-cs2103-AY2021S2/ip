package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.Todo;
import vergil.types.exceptions.VergilException;

public class TodoCommand extends Command {
    private String desc;

    public TodoCommand(String... args) {
        super(CommandType.TODO, args);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        desc = getArgument(0);

        taskList.add(new Todo(desc));

        return "Success! '" + desc + "' has been added as a todo task.";
    }
}
