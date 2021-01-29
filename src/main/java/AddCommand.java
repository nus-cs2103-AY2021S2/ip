public class AddCommand extends Command{
    String[] args;

    public AddCommand(String[] args){
        this.args = args;
    }

    @Override
    String[] run() {
        return args;
    }

    @Override
    TaskList.Action getType() {
        return TaskList.Action.ADD;
    }
}
