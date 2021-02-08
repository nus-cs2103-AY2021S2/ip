public class WelcomeCommand implements Command {

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showWelcome();
    }

}
