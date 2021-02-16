public class ByeCommand extends Command {

    @Override
    public String execute() {
        Main.exit();
        return Ui.showGoodbyeText();
    }
}
