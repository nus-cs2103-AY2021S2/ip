public class EventCommand extends Command {
    private String event;
    private String date;

    EventCommand(String event, String date) {
        this.event = event;
        this.date = date;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(this.event, this.date);
        tasks.add(task);
        ui.addtask(task.toString(), tasks.size());
        storage.savetasks(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
