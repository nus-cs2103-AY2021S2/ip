import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Duke {

  protected ArrayList<Task> tasklist;
  protected Storage storage;
  protected TaskList tasks;
  private Ui ui;

  /**
   * Creates Duke with the file path.
   * @ param filePath The path storing all the past tasklist not deleted by the user
   */

  public Duke() throws IOException {
    ui = new Ui();
    storage = new Storage("duke.txt");
    try {
      tasks = new TaskList(storage.load());
      System.out.println(tasks.getTasklist());
    } catch (FileNotFoundException e) {
      System.out.println("No existing Storage File");
    }
  }

  public String getResponse(String input) throws IOException {
    assert(input != null);
    return ui.readUserInput(input, storage, tasks);
  }

  /**
   * Outputs the greeting and initiaties the userinput parase command to take in inputs from usher.
   */

  public void run() throws IOException, DescriptionError {
    ui.greeting();
    //Parser parse = new Parser();
    //parse.userinput(tasks, storage);
  }

  public void listtask() {
    tasks.list();
  }

  /**
   *  instantiate Duke class and starts the run command.
   */

  public static void main(String[] args) throws DescriptionError, UnknownInputError, IOException {
    Duke duke = new Duke();
    duke.run();

  }



}

