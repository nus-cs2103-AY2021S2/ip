package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {

    private boolean isRunning;
    private TaskList taskList;
    private final Storage storageHandler;
    private final String path = "./data/TaskListData.txt";
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

    public void getResponse(String input) {
        Command command;
        ui.printLine();
        try {
            command = Parser.parse(input);
            isRunning = !command.shouldExit();
            taskList = command.execute(taskList);
            storageHandler.write(taskList);
            ui.printResponse(command.getResponse());
            ui.printLine();
        } catch (DukeException | IOException e) {
            ui.printResponse(e.toString());
        }
    }

}
