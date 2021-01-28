public class AddCommand extends Command{
    private String taskType;
    private String taskInfo;
    public AddCommand(String taskType, String taskInfo) {
        super();
        this.taskType = taskType;
        this.taskInfo = taskInfo;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Task t = null;
        switch(taskType) {
            case "todo":
                break;
            case "deadline":
                break;
            case "event":
                break;
        }
        //update storage.
    }
}