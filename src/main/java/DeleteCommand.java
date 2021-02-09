/**
 * delete command class
 */
public class DeleteCommand extends Command {
    /**
     *
     * @param itemNo the item number to be deleted
     */
    DeleteCommand(int itemNo) {
        super();
        super.itemNo = itemNo;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     *
     * @return delete
     */
    @Override
    public String getType() {
        return "delete";
    }

    /**
     *
     * @return null since it has no descriptiom
     */
    @Override
    public String getDescription() {
        return null;
    }
}