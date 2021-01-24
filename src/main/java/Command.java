abstract class Command {
  private boolean isExit;

  public Command(boolean isExit) {
    this.isExit = isExit;
  }

  public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
  
  public boolean isExit() {
    return isExit;
  }
}
