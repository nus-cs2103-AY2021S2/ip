public class HelpCommand extends Command {

    HelpCommand(String[] details) {
        super(details);
        taskType = Input.valueOf(details[0]);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskType == Input.ERROR) {
            System.out.println("That is not a valid command! Here's some help!");
        }
        ui.showHelp();
    }
}
