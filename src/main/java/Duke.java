import java.io.IOException;

public class Duke {

    private boolean isRunning;
    private TaskList taskList;
    private final Storage storageHandler;
    private final String path = "ip/src/main/java/data/TaskListData.txt";
    private Ui ui;

    public Duke() {
        isRunning = true;
        storageHandler = new Storage(path);
        ui = new Ui();
        try {
            taskList = storageHandler.open();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
        ui.displayWelcomeMessage();
    }


    public boolean isRunning() {
        return isRunning;
    }

    public String getResponse(String input) {
        Command command;
        ui.printLine();
        try {
            command = Parser.parse(input);
            isRunning = !command.shouldExit();
            taskList = command.execute(taskList);
            storageHandler.write(taskList);
            ui.printResponse(command.getResponse());
            ui.printLine();
            return command.getResponse();
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

}
