public class DoneCommand extends Command {
    protected int index;
    public DoneCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            ui.printMessage(tasks.doneTask(this.index));
        } catch(Exception e) {
            ui.printMessage(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}