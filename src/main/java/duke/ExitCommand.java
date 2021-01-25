package duke;

public class ExitCommand extends Command{
    public ExitCommand(){
        super(null,null);
        this.isExit = true;
    }

    @Override
    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage) throws DukeException {
        ui.displayEndMessage();
        return null;
    }
}
