public class UnknownCommand extends Command {
    public UnknownCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("     Unable to figure out this command");
    }

    public boolean isExit() {
        return false;
    }
}
