public class ByeCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //System.out.println("Bye. Hope to see you again soon!");
        ui.byeMsg();
    }

    public Boolean isExit() {
        return true;
    }

}
