public class EventCommand extends Command {
    static final String COMMAND_WORD = "event";
    private String[] taskInputAndDate;

    public EventCommand(TaskList taskList, Ui ui, Storage storage, String[] taskInputAndDate) {
        super(taskList, ui, storage);
        this.taskInputAndDate = taskInputAndDate;
    }

    @Override
    public void execute() {
        Event e = new Event(taskInputAndDate[0], taskInputAndDate[1]);
        System.out.println("Got it. I've added this task:\n" + e);
        this.taskList.addTask(e);
    }
}
