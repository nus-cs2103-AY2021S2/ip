package seashell;

import seashell.command.Command;
import seashell.exception.SeashellException;
import seashell.parser.Parser;
import seashell.storage.SaveHandlerTest;

public class SeashellStub {

    public final SaveHandlerTest saveHandlerTest;
    private final TaskList taskListObj;

    /**
     * Create a new Seashell instance
     */
    public SeashellStub() {
        this.saveHandlerTest = new SaveHandlerTest();
        this.taskListObj = new TaskList();
    }

    public SeashellStub(TaskList tasklist) {
        this.saveHandlerTest = new SaveHandlerTest();
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
            this.saveHandlerTest.loadSave();
            String response = command.execute(this.taskListObj);
            this.saveHandlerTest.updateSaveFile(this.taskListObj);
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
