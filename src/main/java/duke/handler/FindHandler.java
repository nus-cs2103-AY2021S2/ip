package duke.handler;

import duke.Storage;
import duke.Ui;
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
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        response = taskList.findTaskStringWith(findWord);
        ui.respond(response);
    }
}
