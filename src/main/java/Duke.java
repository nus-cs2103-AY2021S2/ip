import java.io.IOException;

public class Duke {
  private Storage storage;
  private TaskList taskList;
  private Ui ui;

  public Duke(String filePath) throws IOException {
    storage = new Storage(filePath);
    ui = new Ui();
    try {
      taskList = storage.load();
    } catch (DukeException e) {
      ui.showLoadFileError();
      taskList = new TaskList();
    }
  }

  public void run() {
    ui.greeting();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.printLineBreak();
        Command c = Parser.parse(fullCommand);
        c.execute(taskList, ui, storage);
        isExit = c.isExit();
      } catch (DukeException e) {
        ui.showError(e);
      } finally {
        ui.printLineBreak();
      }
    }
  }

  public static void main(String[] args) throws IOException {
    new Duke("data/duke.txt").run();
  }
}
