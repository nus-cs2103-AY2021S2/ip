package duke.command;

public class DoneCommand extends Command {
    private final String taskNumber;

    public DoneCommand(String taskNumber) {
        super("done");
        this.taskNumber = taskNumber;
    }

    public String getTaskNumber() {
        return taskNumber;
    }
}
