public class AddCommand extends Command{
    private String taskType;
    private String taskInfo;
    public AddCommand(String taskType, String taskInfo) {
        super();
        this.taskType = taskType;
        this.taskInfo = taskInfo;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        Task t = null;
        switch(taskType) {
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
        tl.add(t);
        ui.printAddedTask(t);
        storage.save(tl.toString());
    }
}