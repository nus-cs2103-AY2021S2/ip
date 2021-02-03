public class SaveCommand extends Command {
    private String command;

    public SaveCommand() {
        this.command = "";
    }

    public SaveCommand(String command) {
        this.command = command;
    }

    public String excute(TaskList taskList) {
        Ui.save();
        return Ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return command.equalsIgnoreCase("bye");
    }
}
