public class DoneCommand extends Command {
    DoneCommand(int itemNo) {
        super();
        super.itemNo = itemNo;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String getType() {
        return "done";
    }

    @Override
    public String getDescription() {
        return null;
    }
}

