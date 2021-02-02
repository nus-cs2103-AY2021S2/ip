package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class EndHandler implements CommandHandler{
    private static String RESPONSE = "Bye. Hope to see you again soon!";

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.respond(RESPONSE);
    }
}
