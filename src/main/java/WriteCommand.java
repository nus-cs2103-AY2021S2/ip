import java.io.IOException;

public class WriteCommand extends Command {
    private String secondPartOfCommand;
    private String firstPartOfCommand;

    public WriteCommand(String firstPartOfCommand,String secondPartOfCommand) {
        this.firstPartOfCommand = firstPartOfCommand;
        this.secondPartOfCommand = secondPartOfCommand;
    }

    //this method return string, adds to task list and write to file
    //too many things in 1 method, will need to split up
    public String action() {
        Task task = null;
        if (firstPartOfCommand.equals("todo")) {
            task = new Todo(secondPartOfCommand);
        } else if (firstPartOfCommand.equals("deadline")) {
            task = new Deadline(secondPartOfCommand);
        } else if (firstPartOfCommand.equals("event")) {
            task = new Event(secondPartOfCommand);
        } else {
            assert false : "In wrong part of WriteCommand action";
        }
        TaskList.addTask(task);
        super.writeToFile();
        return Ui.addTask(task, TaskList.getListSize());
    }
}