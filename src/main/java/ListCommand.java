public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() <= 0) {
            ui.printIndentOutput("The current list is empty.");
        } else {
            ui.printIndentOutput("Here are the tasks in you list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.printIndentOutput((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
