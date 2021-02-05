public class DeleteCommand extends Command {
    DeleteCommand(int itemNo) {
        super();
        super.itemNo = itemNo;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String getType() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return null;
    }
}