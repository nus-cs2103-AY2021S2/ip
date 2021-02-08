public class AddCommand implements Command {
    Task taskToAdd;

    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.taskToAdd);
        String numOfTasks = tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
        ui.printMessage(
                "Got it. I've added this task:\n  " + this.taskToAdd + "\nNow you have " + numOfTasks + " in the list.");
    }
}
