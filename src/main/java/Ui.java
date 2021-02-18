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
  public String greeting() {
    String logo = "Duke\n" + "How are you today?";
    StringBuilder output = new StringBuilder("Hello from\n" + logo);
    return  output.toString();
  }

  public String readUserInput(String command, Storage storage, TaskList tasks) throws IOException {
    assert (command != null);
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
