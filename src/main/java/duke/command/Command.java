package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public abstract class Command {
  private boolean isExit;

  public Command(boolean isExit) {
    this.isExit = isExit;
  }

  
  /** 
   * Executes the command
   * s
   * @param taskList
   * @param ui
   * @param isExit(
   * @throws DukeException
   */
  public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
  
  
  /** 
   * Returns whether the current command is going to exit the programme
   * 
   * @return boolean
   */
  public boolean isExit() {
    return isExit;
  }
}
