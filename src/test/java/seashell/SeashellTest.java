package seashell;

import seashell.command.Command;
import seashell.exception.SeashellException;
import seashell.parser.Parser;
import seashell.storage.SaveHandler;

public class SeashellTest {

    public final SaveHandler saveHandler;
    private final TaskList taskListObj;

    /**
     * Create a new Seashell instance
     */
    public SeashellTest() {
        this.saveHandler = new SaveHandler();
        this.taskListObj = new TaskList();
    }

    public SeashellTest(TaskList tasklist) {
        this.saveHandler = new SaveHandler();
        this.taskListObj = tasklist;
    }

    /**
     * Take in user input and executes the command, then return the response from Seashell as a string
     * @param input command input by user
     * @return response to user input from Seashell
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(this.taskListObj, this.saveHandler);
            return response;
        } catch (SeashellException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public TaskList getTaskList() {
        return this.taskListObj;
    }
}
