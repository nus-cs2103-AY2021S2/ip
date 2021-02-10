package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface Command {
    boolean isExit();
    String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage);
}
