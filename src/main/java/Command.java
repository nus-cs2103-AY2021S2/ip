import java.io.IOException;

public abstract class Command {

    void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {}

    public boolean isExit(){
        return false;
    }
}
