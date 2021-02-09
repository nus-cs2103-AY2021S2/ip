/**
 * list command when the user types in list
 */
public class ListCommand extends Command {
    /**
     * creates a list command object
     */
    public ListCommand() {
        super();
    }

    /**
     *
     * @return false it is not a terminating command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     *
     * @return list
     */
    @Override
    public String getType() {
        return "list";
    }

    /**
     *
     * @return null because it has no description
     */
    @Override
    public String getDescription() {
        return null;
    }
}

