import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  protected ArrayList<Task> tasklist;
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Duke(String filePath) throws IOException {
    storage = new Storage(filePath);
    try {
      tasks = new TaskList(storage.load());
      System.out.println(tasks.getTasklist());
    } catch (FileNotFoundException e) {
      System.out.println("No existing Storage File");
    }
  }

  public void run() throws IOException, DescriptionError {
    ui = new Ui();
    ui.greeting();
    Parser parse = new Parser();
    parse.userinput(tasks, storage);
  }

  public void listtask() {
    tasks.list();
  }

  public static void main(String[] args) throws DescriptionError, UnknownInputError, IOException {
    Duke duke = new Duke("src/main/java/duke.text");
    duke.run();

  }



}

