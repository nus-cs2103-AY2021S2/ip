public class ListCommand extends Command {

    public ListCommand(String input, TaskList tl) {
        super(input,tl);
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage){
        tasks.showAllTask();
        return null;
    }

}
