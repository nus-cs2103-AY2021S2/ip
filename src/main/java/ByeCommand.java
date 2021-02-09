public class ByeCommand implements Command {
    public String action() {
        return Ui.exit();
    }
}