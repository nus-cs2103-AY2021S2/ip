public class ListCommand extends Command {

    public ListCommand(String[] commandSplit) {
        super(commandSplit);
    }

    @Override
    void execute(TaskList list) {
        list.printList();
    }
}
