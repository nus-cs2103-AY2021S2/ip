package duke.handler;

import duke.Storage;
import duke.tasks.TaskList;

/**
 * FindHandler handles the command find.
 */
public class FindHandler implements CommandHandler {
    private String findWord = "";
    private String response = "";

    public FindHandler(String findWord) {
        this.findWord = findWord;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        response = taskList.findTaskStringWith(findWord);
        return response;
    }
}
