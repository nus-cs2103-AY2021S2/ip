import java.io.IOException;
/**
   * DoneCommand inherits Command
   * @param command the main action of the command
   * @param task the index of task user wants to mark as done
   * @param date is a null value for Done Command
   */

public class DoneCommand extends Command {

    public DoneCommand(String command, String task, String date) {
        super(command, task, date);
        // TODO Auto-generated constructor stub
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        // TODO Auto-generated method stub
       
            int index = Integer.valueOf(task);
            Task curTask = taskList.get(index);
            curTask.markAsDone();
            ui.addDoneString(curTask.toString());
            storage.rewrite(taskList);
    }

    @Override
    boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
