public class SaveCommand extends Command {
    private String command;

    public SaveCommand(String command) {
        this.command = command;
    }

    /**
     * Main function of SaveCommand task, saving tasks into fileName given at the start of the program.
     * @param taskList
     * @return String , goodbye message to display
     */
    public String execute(TaskList taskList, Ui ui) {
        ui.save();
        return Ui.showGoodbye();
    }
}
