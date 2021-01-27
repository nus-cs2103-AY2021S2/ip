import java.io.IOException;

public class AddCommand extends Command {

    public AddCommand(String command, String task, String date) {
        super(command, task, date);
        // TODO Auto-generated constructor stub
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        // TODO Auto-generated method stub
        if (this.command.equals("todo")) {
            Task newTask = new ToDos(task);
            taskList.add(newTask);
            storage.store(taskList.size() + "." + newTask.toString());
            ui.addCommandString(taskList.size(), newTask.toString());
            
        } else if ( this.command.equals("deadline")){
            Task newTask = new Deadline(this.task, this.date);
            taskList.add(newTask);
            storage.store(taskList.size() + "." + newTask.toString());
            ui.addCommandString(taskList.size(), newTask.toString());

        } else if (this.command.equals("event")) {
            Task newTask = new Events(this.task, this.date);
            System.out.println("openis");
            taskList.add(newTask);
            storage.store(taskList.size() + "." + newTask.toString());
            ui.addCommandString(taskList.size(), newTask.toString());
        }
    }

    @Override
    boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
