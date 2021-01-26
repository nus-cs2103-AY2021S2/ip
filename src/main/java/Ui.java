public class Ui {

  public Ui() {

  }

  /**
   * Outputs the customied bye message when user inputs bye command.
   */

  public void bye() {
    System.out.println(" ___________________________________________");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println(" ___________________________________________");
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



}
