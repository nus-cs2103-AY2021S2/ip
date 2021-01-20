/**
 * Owen is a personal assistant chatbot.
 * 
 * As Owen is an immutable class, modifications will return a copy
 * with updated internal state.
 */
public class Owen implements Chatbot {
    private final boolean isRunning;
    private final Response latestResponse;
    private final TaskList taskList;

    /**
     * Creates an Owen chatbot with a hello response.
     */
    public Owen() {
        this.isRunning = true;

        StringBuilder stringBuilder = new StringBuilder();
        String logo = ""
                + " /\\_/\\ \n"
                + "((OvO))\n"
                + "():::()\n"
                + " VV-VV \n";
        stringBuilder.append(logo);
        stringBuilder.append("\nHello I am Owen the Owl!");
        this.latestResponse = new DefaultResponse(stringBuilder.toString());

        this.taskList = new TaskList();
    }

    private Owen(boolean isRunning, Response latestResponse, TaskList taskList) {
        this.isRunning = isRunning;
        this.latestResponse = latestResponse;
        this.taskList = taskList;
    }

    @Override
    public Owen shutdown() {
        Response shutdownResponse = new DefaultResponse("Bye. Hope to see you again soon!");
        return new Owen(false, shutdownResponse, this.taskList);
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public Response getResponse() {
        return this.latestResponse;
    }

    @Override
    public Owen parseCommand(String command) {
        String[] splitCommand = command.split(" ", 2);
        String parsedCommand = splitCommand[0];

        switch (parsedCommand) {
        case "list":
            Response listResponse = new DefaultResponse(this.taskList.toString());
            return new Owen(this.isRunning, listResponse, this.taskList);
        case "done":
            int taskNumber = Integer.parseInt(splitCommand[1]);
            TaskList doneTaskList = this.taskList.markAsDone(taskNumber);
            String doneFormat = "Nice! I've marked this task as done:\n    %s";
            Response doneResponse = new DefaultResponse(String.format(
                    doneFormat, doneTaskList.getTask(taskNumber).toString()));
            return new Owen(this.isRunning, doneResponse, doneTaskList);
        case "bye":
            return this.shutdown();
        default:
            TaskList addedTaskList = this.taskList.addTask(command);
            int numTasks = addedTaskList.getNumTasks();
            String addedFormat = ""
                    + "Got it. I've added this task:\n"
                    + "    %s\n"
                    + "Now you have %d tasks in the list.";
            Response addResponse = new DefaultResponse(String.format(
                    addedFormat, addedTaskList.getTask(numTasks), numTasks));
            return new Owen(this.isRunning, addResponse, addedTaskList);
        }
    }
}
