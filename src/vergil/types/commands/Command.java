package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;

public abstract class Command {
    private CommandType type;
    private String[] args;

    public Command(CommandType type, String... args) {
        this.type = type;
        this.args = args;
    }

    public CommandType getType() {
        return type;
    }

    public String getArgument(int index) {
        return args[index];
    }

    public abstract String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException;
}
