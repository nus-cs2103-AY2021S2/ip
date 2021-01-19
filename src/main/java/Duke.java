import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {
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
      String input = scan.next();
      if (input.equals("bye")) {
        System.out.println(" ___________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" ___________________________________________");
      } else {
        System.out.println(" ___________________________________________");
        System.out.println(input);
        System.out.println(" ___________________________________________");
      }

    }

  }



}

