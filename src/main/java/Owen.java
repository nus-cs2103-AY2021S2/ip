/**
 * Owen is a personal assistant chatbot.
 * 
 * As Owen is an immutable class, modifications will return a copy
 * with updated internal state.
 */
public class Owen implements Chatbot {
    private enum Command {
        TODO,
        EVENT,
        DEADLINE,
        LIST,
        DONE,
        DELETE,
        BYE,
    }

    private static final String STORAGE_PATH = "data/owen.txt";

    private final boolean isRunning;
    private final Response latestResponse;
    private final TaskList taskList;
    private final Storage storage;

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

        this.storage = new Storage(STORAGE_PATH);
        this.taskList = this.storage.readTaskList();
    }

    private Owen(boolean isRunning, Response latestResponse, TaskList taskList, Storage storage) {
        this.isRunning = isRunning;
        this.latestResponse = latestResponse;
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public Owen shutdown() {
        Response shutdownResponse = new DefaultResponse("Bye. Hope to see you again soon!");
        return new Owen(false, shutdownResponse, this.taskList, this.storage);
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
        String parsedCommandString = splitCommand[0];

        try {
            // Try converting command to enum
            Command parsedCommand;
            try {
                parsedCommand = Command.valueOf(parsedCommandString.toUpperCase());
            } catch (IllegalArgumentException exception) {
                throw new OwenException("I'm sorry, but I don't know what that means...");
            }

            switch (parsedCommand) {
            case TODO:
            case EVENT:
            case DEADLINE:
                return this.addTask(command);
            case LIST:
                return this.listTasks();
            case DONE:
                if (splitCommand.length < 2) {
                    throw new OwenException("Task number must be specified...");
                }
                return this.doneTask(this.parseTaskNumber(splitCommand[1]));
            case DELETE:
                if (splitCommand.length < 2) {
                    throw new OwenException("Task number must be specified...");
                }
                return this.deleteTask(this.parseTaskNumber(splitCommand[1]));
            case BYE:
                return this.shutdown();
            default:
                throw new OwenException("I'm sorry, but I don't know what that means...");
            }
        } catch (OwenException exception) {
            Response exceptionResponse = new DefaultResponse(exception.getMessage());
            return new Owen(this.isRunning, exceptionResponse, this.taskList, this.storage);
        }
    }

    private int parseTaskNumber(String taskNumber) throws OwenException {
        try {
            return Integer.parseInt(taskNumber);
        } catch (NumberFormatException exception) {
            throw new OwenException("Task number must be specified...");
        }
    }

    private Owen addTask(String task) throws OwenException {
        TaskList addedTaskList = this.taskList.addTask(task);
        int numTasks = addedTaskList.getNumTasks();
        String addedFormat = ""
                + "Got it. I've added this task:\n"
                + "    %s\n"
                + "Now you have %d tasks in the list.";
        Response addResponse = new DefaultResponse(String.format(
                addedFormat, addedTaskList.getTask(numTasks), numTasks));
        Storage addStorage = this.storage.writeTaskList(addedTaskList);
        return new Owen(this.isRunning, addResponse, addedTaskList, addStorage);
    }

    private Owen listTasks() {
        Response listResponse = new DefaultResponse(this.taskList.toString());
        return new Owen(this.isRunning, listResponse, this.taskList, this.storage);
    }

    private Owen doneTask(int taskNumber) throws OwenException {
        TaskList doneTaskList = this.taskList.markAsDone(taskNumber);
        String doneFormat = "Nice! I've marked this task as done:\n    %s";
        Response doneResponse = new DefaultResponse(String.format(
                doneFormat, doneTaskList.getTask(taskNumber).toString()));
        Storage doneStorage = this.storage.writeTaskList(doneTaskList);
        return new Owen(this.isRunning, doneResponse, doneTaskList, doneStorage);
    }

    private Owen deleteTask(int taskNumber) throws OwenException {
        TaskList deleteTaskList = this.taskList.deleteTask(taskNumber);
        String deleteFormat = ""
                + "Noted. I've removed this task:\n"
                + "    %s\n"
                + "Now you have %d tasks in the list.";
        int newNumTasks = deleteTaskList.getNumTasks();
        Response deleteResponse = new DefaultResponse(String.format(
                deleteFormat, this.taskList.getTask(taskNumber), newNumTasks));
        Storage deleteStorage = this.storage.writeTaskList(deleteTaskList);
        return new Owen(this.isRunning, deleteResponse, deleteTaskList, deleteStorage);
    }
}
