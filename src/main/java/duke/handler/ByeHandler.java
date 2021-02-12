package duke.handler;


import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ByeHandler implements CommandHandler {
    private static String response = "Bye. Hope to see you again soon!";

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.respond(response);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ByeHandler) {
            return true;
        } else {
            return false;
        }
    }
}
