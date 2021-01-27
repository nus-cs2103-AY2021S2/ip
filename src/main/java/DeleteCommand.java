import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(String command, String task, String date) {
        super(command, task, date);
        // TODO Auto-generated constructor stub
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        // TODO Auto-generated method stub 
            int index = Integer.valueOf(task);
            Task curTask = taskList.get(index);
            taskList.delete(index);
            ui.addDeleteString(taskList.size(), curTask.toString());
            storage.rewrite(taskList);
    }

    @Override
    boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
