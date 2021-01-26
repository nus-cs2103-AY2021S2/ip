package duke;

/*
Code was refractored after week 2
Credit of light reuse: James Lee
 */
public class Duke {
    public boolean isOn = false;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    public static String line = "____________________________________________________________";

    public Duke(String filePath) {
        this.isOn = true;
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);

    }

    protected static void print(String s) {
        System.out.println(s);
    }

    public void start() {
        storage.getLastSave(taskList);
        parser.start(this, taskList, ui);
    }


}
