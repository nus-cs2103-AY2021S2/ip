import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    private String desc = "";
    private LocalDateTime dateTime;

    public DeadlineCommand(String desc, LocalDateTime dateTime) {
        this.desc = desc;
        this.dateTime = dateTime;
    }

    @Override
    public void execute() throws DukeCommandException {
        try {
            Deadline deadline = taskManager.addDeadline(this.desc, this.dateTime);
            ui.printAddMsg(deadline, taskManager.getTasksSize());
        } catch (DukeTaskException e) {
            throw new DukeCommandException("deadline", desc, e.getMessage());
        }
    }
}
