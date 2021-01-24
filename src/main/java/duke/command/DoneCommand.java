package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class DoneCommand extends Command {
  private int idx;

  public DoneCommand(int idx) {
    super(false);
    this.idx = idx - 1;
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    taskList.markDone(idx);
  }
}