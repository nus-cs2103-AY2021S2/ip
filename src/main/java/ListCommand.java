import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand(String command, String task, String date) {
        super(command, task, date);
        // TODO Auto-generated constructor stub
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        // TODO Auto-generated method stub
            ui.list(storage.read(),this.date);
    }

    @Override
    boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
