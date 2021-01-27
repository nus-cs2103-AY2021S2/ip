package duke.command;

public class DeleteCommand extends Command{
    String taskNumber;

    public DeleteCommand(String taskNumber) {
        super("delete");
        this.taskNumber = taskNumber;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    @Override
    public void run() {

    }
}
