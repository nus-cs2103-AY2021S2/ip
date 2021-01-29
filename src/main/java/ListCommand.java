public class ListCommand extends Command {

    @Override
    String[] run() {
        return new String[0];
    }

    @Override
    TaskList.Action getType() {
        return TaskList.Action.LIST;
    }
}
