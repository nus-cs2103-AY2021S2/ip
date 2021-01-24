public class AddEvent extends AddCommand {

    /**
     * Constructor for AddCommand class command name and description.
     *
     * @param commandType
     * @param description Description of the command.
     */
    public AddEvent(String commandType, String description) {
        super(commandType, description);
    }

    @Override
    public TaskList execute(TaskList taskList) {
        int taskID = taskList.getSize() + 1;
        newTask = new EventTask(description, taskID);
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

    @Override
    public String getResponse() {
        return super.getResponse() + super.newTask.toString();
    }
}
