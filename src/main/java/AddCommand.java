public class AddCommand implements Command {

    private final String command;
    private final String description;
    private Task newTask;
    private int numTasks;

    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public boolean shouldExit() {
        return false;
    }

    public TaskList execute(TaskList taskList) {

        int taskID = taskList.getSize() + 1;
        switch (command) {
            case "todo":
                newTask = new ToDoTask(description, taskID);
                break;
            case "event":
                newTask = new EventTask(description, taskID);
                break;
            case "deadline":
                newTask = new DeadlineTask(description, taskID);
                break;
            default:
                newTask = new ToDoTask(description, taskID);
                break;
        }
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

    public String getResponse() {
        return "Got it. I've added this task:\n" + newTask.toString()
        + "\nNow you have " + numTasks + " tasks in the list.";
    }

}
