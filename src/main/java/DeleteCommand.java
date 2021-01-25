import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            storage.overwrite(taskList.delete(taskDescription));
        } catch (Exception ex) {
            System.out.println("     " + ex.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
