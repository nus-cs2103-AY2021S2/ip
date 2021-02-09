/**
 * done command class
 */
public class DoneCommand extends Command {
    /**
     *
     * @param itemNo item number that is done
     */
    DoneCommand(int itemNo) {
        super();
        super.itemNo = itemNo;
    }

    /**
     *
     * @return false since its not bye
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     *
     * @return done
     */
    @Override
    public String getType() {
        return "done";
    }

    /**
     *
     * @return null since it does not need a description
     */
    @Override
    public String getDescription() {
        return null;
    }
}

