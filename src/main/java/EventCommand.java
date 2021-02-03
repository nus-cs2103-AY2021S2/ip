public class EventCommand implements Command {
    private final String taskDescription;
    private final String taskTime;

    public EventCommand(String taskDescription, String taskTime) {
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        try {
            tasks.add(new Event(this.taskDescription, this.taskTime));
        } catch (CommandFormatException e) {
            ui.showFormatException("EventCommand");
            return true;
        }
        ui.showNewTask(tasks.size(), tasks.get(tasks.size()-1));
        return true;
    }
}
