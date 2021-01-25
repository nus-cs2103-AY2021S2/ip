import java.io.IOException;

public class DoneCommand extends Command {
    public DoneCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.overwrite(taskList.done(taskDescription, ui));
        } catch (Exception ex) {
            System.out.println("     " + ex.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
