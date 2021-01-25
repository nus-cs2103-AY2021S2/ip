public class ByeCommand extends Command {
    public ByeCommand(String[] commandSplit) {
        super(commandSplit);
    }

    void execute(TaskList task) throws DukeException {
        task.rewriteTasks();
        Ui.printWithStyle("Bye. Hope to see you again soon!");
    }
}
