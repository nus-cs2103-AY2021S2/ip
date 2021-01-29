package duke;

public class AddCommand extends Command {
    private String taskType;
    private String taskInfo;

    public AddCommand(String taskType, String taskInfo) {
        super();
        this.taskType = taskType;
        this.taskInfo = taskInfo;
    }

    /**
     * Identify task type and create the task with the task info.
     * Task is then added to the task list and stored on disk.
     *
     * @param tl task list.
     * @param ui user interface object.
     * @param storage storage of task list.
     * @throws DukeException if task cannot be created.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
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
        }
        if (t != null) {
            tl.add(t);
            ui.printAddedTask(t);
            Storage.save(tl.toString());
        }
    }
}