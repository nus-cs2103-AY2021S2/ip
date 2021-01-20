public class TodoCommand extends AddCommand {

    public TodoCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String getResponse() {
        return String.format(" Got it. I've added this task: \n" +
                "       %s\n" +
                " Now you have %d tasks in the list.", this.taskToAdd.taskDescription, this.taskList.getNumTasks());
    }
}
