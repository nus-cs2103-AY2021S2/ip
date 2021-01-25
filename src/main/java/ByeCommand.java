import java.io.IOException;
import java.util.List;

public class ByeCommand extends Command {
    static final String COMMAND_WORD = "bye";

    public ByeCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public void execute() {
        try {
            List<String> converted = FileTaskStringConverter.allTaskToAllString(taskList.getList());
            this.storage.writeToFile(converted);
            String endMessage = "Bye. Hope to see you again soon!";
            this.ui.showMsg(endMessage);
        } catch (IOException e) {
            this.ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
