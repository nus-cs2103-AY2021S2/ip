import java.io.IOException;

public class Duke {

    private boolean isRunning;
    private TaskList taskList;
    private final StorageHandler storageHandler;
    private final String path = "ip/src/main/java/data/TaskListData.txt";

    public Duke() {
        isRunning = true;
        storageHandler = new StorageHandler(path);
        displayWelcomeMessage();
        try {
            taskList = storageHandler.open();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }


    public boolean isRunning() {
        return isRunning;
    }

    public String getResponse(String input) {
        Command command;
        try {
            command = InputHandler.parse(input);
            isRunning = !command.shouldExit();
            taskList = command.execute(taskList);
            storageHandler.write(taskList);
            return command.getResponse();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("v2    Hello! I'm Duke\n    What can I do for you?");
    }
}
