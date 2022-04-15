/**
   * DoneCommand inherits Command
   * @param command the main action of the command
   * @param task the index of task user wants to mark as done
   * @param date is a null value for Done Command
   */

public class DoneCommand extends Command {

    public DoneCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
            int index = Integer.valueOf(this.task);
            Task curTask = taskList.get(index);
            curTask.markAsDone();

            storage.rewrite(taskList);
        return ui.addDoneString(curTask.toString());
    }

    @Override
    boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
