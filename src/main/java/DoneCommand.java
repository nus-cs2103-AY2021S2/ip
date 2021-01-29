public class DoneCommand extends IndexedCommand{
    public DoneCommand(int position) {
        super(position);
    }

    @Override
    TaskList.Action getType() {
        return TaskList.Action.DONE;
    }
}
