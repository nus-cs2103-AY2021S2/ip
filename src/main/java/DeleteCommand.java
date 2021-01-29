public class DeleteCommand extends IndexedCommand{
    public DeleteCommand(int position) {
        super(position);
    }

    @Override
    TaskList.Action getType() {
        return TaskList.Action.DELETE;
    }
}
