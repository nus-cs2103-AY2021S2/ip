/**
 * Represents a command involving the search of a task.
 */
public class FindCommand extends Command {
    private String name;

    /**
     * Constructor for FindCommand.
     * @param name Name of task.
     */
    FindCommand(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String string = "";
        for (int i = 0; i < tasks.size(); i++) {
            int index = tasks.get(i).toString().indexOf(this.name);
            if (index >= 0) {
                string += String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
            }
        }
        return ui.find(string);
    }

    /**
     * Checks the equivalence of FindCommand this and Object obj.
     * If obj is an instance of the FindCommand class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindCommand) {
            FindCommand findCommand = (FindCommand) obj;
            return findCommand.name.equals(this.name);
        }
        return false;
    }
}
