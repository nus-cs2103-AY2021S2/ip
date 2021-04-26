package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;

/**
 * Represents a command given to Vergil to be executed.
 */
public abstract class Command {
    private CommandType type;
    private String[] args;

    /**
     * Constructs a new Command object.
     *
     * @param   type    the type of the command.
     * @param   args    the arguments required by the command.
     */
    public Command(CommandType type, String... args) {
        this.type = type;
        this.args = args;
    }

    public CommandType getType() {
        return type;
    }

    /**
     * Returns one of the command's arguments (referenced by its index number in the arguments array).
     *
     * @param   index   the argument's index number in the array.
     * @return          the argument located at the given index number.
     */
    public String getArgument(int index) {
        return args[index];
    }

    /**
     * Executes the command and returns Vergil's response.
     *
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks to be operated on by the command.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the correct execution of the command.
     * @throws VergilException  if the command is invalid.
     */
    public abstract String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException;
}
