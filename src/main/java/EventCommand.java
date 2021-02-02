public class EventCommand extends Command{
    String at;

    public EventCommand(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(description, at);
        taskList.addEventTask(event);
        storage.writeToFile(taskList.getList());
        ui.showTaskAdded(event);
    }
}
