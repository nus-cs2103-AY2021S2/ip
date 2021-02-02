import java.io.IOException;

public class Ui {

  public Ui() {

  }

  /**
   * Outputs the customied bye message when user inputs bye command.
   */

  public String bye() {
    String msg = "Bye. Hope to see you again soon!";
    return msg;
  }

  /**
   * Outputs the customised greeting message when Duke object is instiatied.
   */
  public void greeting() {
    String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println(" ___________________________________________");
    System.out.println("Hello! I'm Duke \n" + "What can I do for you today?");
    System.out.println(" ___________________________________________");
  }

  public String readUserInput(String command, Storage storage, TaskList tasks) throws IOException {
    Parser parser = new Parser(storage, tasks);
    String output = "";
    try {
      output = parser.userinput(command);
    } catch (IOException | DescriptionError e) {
      output += e.getMessage();
    }
    return output;
  }

}
