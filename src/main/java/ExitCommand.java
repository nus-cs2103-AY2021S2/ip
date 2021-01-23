public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
