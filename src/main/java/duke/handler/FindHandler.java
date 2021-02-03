package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class FindHandler implements CommandHandler{
    String findWord = "";
    String response ="";

    public FindHandler(String findWord) {
        this.findWord = findWord;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        response = taskList.findTaskWith(findWord);
        ui.respond(response);
    }
}
