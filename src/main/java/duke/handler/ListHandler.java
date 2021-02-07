package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ListHandler implements CommandHandler {
    private String response ="";

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        response += taskList.toString();
        ui.respond(response);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ListHandler) {
            return true;
        } else {
            return false;
        }
    }
}
