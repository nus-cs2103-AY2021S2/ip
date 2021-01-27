package duke.command;

public class DoneCommand extends Command{
    String taskNumber;

    public DoneCommand(String taskNumber) {
        super("done");
        this.taskNumber = taskNumber;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    @Override
    public void run() {

    }
}
