/**
 * The type Bye command.
 */
public class ByeCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeMsg();
    }

    public Boolean isExit() {
        return true;
    }

}
