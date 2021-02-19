/**
 * Represents a command involving the display of saved tasks.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list += String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return ui.list(list);
    }

    /**
     * Checks the equivalence of ListCommand this and Object obj.
     * If obj is an instance of the ListCommand class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ListCommand) {
            return true;
        }
        return false;
    }
}
