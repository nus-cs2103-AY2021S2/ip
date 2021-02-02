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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof EventCommand) {
            EventCommand evc = (EventCommand) obj;
            return evc.event.equals(this.event) && evc.date.equals(this.date);
        }
        return false;
    }
}
