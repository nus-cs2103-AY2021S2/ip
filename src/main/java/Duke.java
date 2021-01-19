import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  private ArrayList<String> tasklist;

  public Duke() {
    this.tasklist = new ArrayList<>();
  }

  public void list() {
    int i = 1;
    for (String task : tasklist) {
      System.out.println(i + ". " + task + "\n");
      i++;
    }
  }

  public void addList(String input) {
    this.tasklist.add(input);
    System.out.println(" ___________________________________________");
    System.out.println("added: " + input);
    System.out.println(" ___________________________________________");
  }



  public static void main(String[] args) {
    Duke duke = new Duke();
    String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println(" ___________________________________________");
    System.out.println("Hello! I'm Duke \n" + "What can I do for you today?");
    System.out.println(" ___________________________________________");
    Scanner scan = new Scanner(System.in);
    while (scan.hasNext()) {
      String input = scan.nextLine();
      if (input.equals("bye")) {
        System.out.println(" ___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" ___________________________________________");

      } else if (input.equals("list")) {
        System.out.println(" ___________________________________________");
        duke.list();
        System.out.println(" ___________________________________________");

      } else {
        duke.addList(input);

      }

    }

  }



}

