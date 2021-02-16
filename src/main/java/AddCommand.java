/**
   * AddCommand inherits Command
   * @param command the main action of the command (todo,deadline,event)
   * @param task the description of the task
   * @param date the due date specified by user
   */

public class AddCommand extends Command {

    public AddCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.command.equals("todo")) {
            Task newTask = new ToDos(task);
            taskList.add(newTask);
            storage.store(taskList.size() + "." + newTask.toString());
            return ui.addCommandString(taskList.size(), newTask.toString());
            
        } else if ( this.command.equals("deadline")){
            Task newTask = new Deadline(this.task, this.date);
            taskList.add(newTask);
            storage.store(taskList.size() + "." + newTask.toString());
            return ui.addCommandString(taskList.size(), newTask.toString());

        } else if (this.command.equals("event")) {
            Task newTask = new Events(this.task, this.date);
            taskList.add(newTask);
            storage.store(taskList.size() + "." + newTask.toString());
            return ui.addCommandString(taskList.size(), newTask.toString());
        } else {
            assert false;
            return "error";
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
    
}
