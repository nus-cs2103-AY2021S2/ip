package duke;

public class AddCommand extends Command {
    private String taskType;
    private String taskInfo;

    /**
     * Constructs add command.
     *
     * @param taskType Type of task to add.
     * @param taskInfo Information of task to be parsed.
     */
    public AddCommand(String taskType, String taskInfo) {
        super();
        this.taskType = taskType;
        this.taskInfo = taskInfo;
    }

    /**
     * Identifies task type and create the task with the task info.
     * Task is then added to the task list and stored on disk.
     *
     * @param tl task list.
     * @param ui user interface object.
     * @param storage storage of task list.
     * @throws DukeException if task cannot be created.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        Task t = null;
        switch (taskType) {
        case "todo":
            t = ToDo.create(taskInfo);
            break;
        case "deadline":
            t = Deadline.create(taskInfo);
            break;
        case "event":
            t = Event.create(taskInfo);
            break;
        default:
            throw new DukeWrongCommandException(taskType);
        }
        assert t != null : "Task should not be null!";
        tl.add(t);
        storage.save(tl.toString());
        return ui.printTaskMsg(t, tl, "add");
    }
}
