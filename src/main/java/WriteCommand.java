import java.io.IOException;

public class WriteCommand implements Command {
    protected String secondPartOfCommand;

    public WriteCommand(String secondPartOfCommand) {
        this.secondPartOfCommand = secondPartOfCommand;
    }

    //this method return string, adds to task list and write to file
    //too many things in 1 method, will need to split up
    public String action() {
        Task task;
        if (secondPartOfCommand.equals("todo")) {
            task = new Todo(secondPartOfCommand);
        } else if (secondPartOfCommand.equals("deadline")) {
            task = new Deadline(secondPartOfCommand);
        } else {
            task = new Event(secondPartOfCommand);
        }
        TaskList.addTask(task);
        writeToFile();
        return Ui.addTask(task, TaskList.getListSize());
    }


    public void writeToFile() {
        /*try {
            //FileAccessor.writeToFile(TaskList.getList());
        } catch (IOException e) {
            System.out.println(Ui.informUnableSave());
        }*/
    }

}