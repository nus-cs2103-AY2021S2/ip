package views;

public class Greeting {
  /**
   * Outputs the standard greeting with Duke Logo
   */
  public void greet() {
    String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
  }

  /**
   * Outputs the standard bye greeting for a user
   */
  public void bye() {
    String byeText = "Bye. Hope to see you again soon!";
    System.out.println(byeText);
  }
}
