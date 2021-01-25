public class ByeCommand extends Command {
    public ByeCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
