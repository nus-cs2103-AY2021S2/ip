package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DummyCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException { }
}
