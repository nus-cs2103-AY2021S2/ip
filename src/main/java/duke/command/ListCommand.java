package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class ListCommand extends Command {
  public ListCommand() {
    super(false);
  }

  public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    ui.printIndented("Here are the tasks in your list:");
    for (int i = 0; i < taskList.size(); i++) {
      ui.printIndented(String.format("%d.%s", i + 1, taskList.get(i).toString()));
    }
  }
}
