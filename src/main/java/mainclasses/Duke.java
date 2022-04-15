package mainclasses;

import java.io.File;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        storage.loadFile(filePath);
        File file = new File(filePath);
        this.taskList = new TaskList(storage.initialiseStorage(file));
        this.parser = new Parser(this.taskList, storage);
        this.ui = new Ui(parser);

    }

    public String getResponse(String input) {
        return this.ui.executeInput(input);
    }

    public String getInitialGreeting() {
        return this.ui.initialStatements();
    }





}
