package duke;

/*
cmd + alt + l reformats code
Code was refractored after week 2
Credit of light reuse: James Lee
 */

/**
 * Represents the task manager known as Duke.
 **/
public class Duke {
    private boolean isJustOn;
    public boolean isOn = false;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    public static String line = "___________________________";

    /**
     * Duke's only constructor that sets in place its components. Namely: the ui,
     * parser, taskList and storage.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        this.isOn = true;
        this.isJustOn = true;
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
    }

    /**
     * Shorthand printer
     *
     * @param s
     */
    protected static void print(String s) {
        System.out.println(s);
    }

    /**
     * Starts the Duke instance. Retreives data from the text file of past tasks.
     */
    public String getResponse(String input){
        if(isJustOn) {
            storage.getLastSave(taskList);
        }
        isJustOn = false;
        return parser.activate(input,this, taskList, ui, storage);
    }




}
