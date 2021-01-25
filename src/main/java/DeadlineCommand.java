public class DeadlineCommand extends Command {
    static final String COMMAND_WORD = "deadline";
    private String[] taskInputAndDate;

    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String[] taskInputAndDate) {
        super(taskList, ui, storage);
        this.taskInputAndDate = taskInputAndDate;
    }

    @Override
    public void execute() {
        Deadline d = new Deadline(taskInputAndDate[0], taskInputAndDate[1]);
        System.out.println("Got it. I've added this task:\n" + d);
        this.taskList.addTask(d);
    }
}
