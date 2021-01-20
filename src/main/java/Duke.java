import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  private ArrayList<Task> tasklist;

  public Duke() {
    this.tasklist = new ArrayList<>();
  }

  public void list() {
    int i = 1;
    System.out.println("Here are the tasks in your list:");
    for (Task task : tasklist) {
      System.out.println(i + ". " + task);
      i++;
    }
  }

  public void addList(Task input) throws DescriptionError {
    this.tasklist.add(input);
  }

  public String inputEventDescription(String input) {
    String[] inputs = input.split(" ");
    String output = "";
    for (int i = 1; i < inputs.length; i++) {
      output += " " + inputs[i];
    }
    return output;
  }

  private void delete(int num) {
    Task task = this.tasklist.remove(num - 1);
    System.out.println("Noted. I've removed this task: ");
    System.out.println(task);
    System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");


  }

  public static void main(String[] args) throws DescriptionError, UnknownInputError {
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

      } else if (input.split(" ")[0].equals("done")) {
        try {
          if (input.length() == 4) {
            throw new DescriptionError("☹ OOPS!!! The description of a done task cannot be empty.");
          }
          if (input.split(" ").length > 2) {
            throw new UnknownInputError("☹ OOPS!!! I'm sorry,"
                    + " but I don't know what that means :-(");
          }
          if (Integer.parseInt(input.split(" ")[1])
                  / Integer.parseInt(input.split(" ")[1]) != 1) {
            throw new NumberFormatException();
          }
          if (Integer.parseInt(input.split(" ")[1]) > duke.tasklist.size()) {
            throw new DescriptionError("☹ OOPS!!! The task is not in the list.");
          }

          if (input.split(" ").length == 2 && Integer.parseInt(input.split(" ")[1])
                  / Integer.parseInt(input.split(" ")[1]) == 1) {
            duke.tasklist.get((Integer.parseInt(input.split(" ")[1]) - 1)).taskDone();
            System.out.println(" ___________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(duke.tasklist.get((Integer.parseInt(input.split(" ")[1]) - 1)));
            System.out.println(" ___________________________________________");
          }
        } catch (DescriptionError | UnknownInputError | NumberFormatException e) {
          if (e instanceof  NumberFormatException) {
            System.out.println("☹ OOPS!!! The description of a done task needs to be an integer.");
            continue;
          } else {
            System.out.println(e.getMessage());
            continue;
          }
        }

      } else if (input.split(" ")[0].equals("todo")) {
        try {
          if (input.length() == 4) {
            throw new DescriptionError("☹ OOPS!!! The description of a todo cannot be empty.");
          }
        } catch (DescriptionError e) {
          System.out.println(e.getMessage());
          continue;
        }
        String inputDes = duke.inputEventDescription(input);
        Todo task = new Todo(inputDes);
        System.out.println(" ___________________________________________");
        System.out.println("Got it. I've added this task: ");
        duke.addList(task);
        System.out.println(task);
        System.out.println("Now you have " + duke.tasklist.size() + " tasks in the list.");
      } else if (input.split(" ")[0].equals("deadline")) {
        try {
          if (input.length() == 8) {
            throw new DescriptionError("☹ OOPS!!! The description of a deadline cannot be empty.");
          }
        } catch (DescriptionError e) {
          System.out.println(e.getMessage());
          continue;
        }
        Deadline task = new Deadline(input.split(" ")[1] + " " + input.split(" ")[2],
                input.split("/")[1]);
        System.out.println("Got it. I've added this task: ");
        duke.addList(task);
        System.out.println(task);
        System.out.println("Now you have " + duke.tasklist.size()  + " tasks in the list.");
      } else if (input.split(" ")[0].equals("event")) {
        try {
          if (input.length() == 5) {
            throw new DescriptionError("☹ OOPS!!! The description of a event cannot be empty.");
          }
        } catch (DescriptionError e) {
          System.out.println(e.getMessage());
          continue;
        }
        Event task = new Event(input.split(" ")[1] + " " + input.split(" ")[2],
                input.split("/")[1]);
        System.out.println("Got it. I've added this task: ");
        duke.addList(task);
        System.out.println(task);
        System.out.println("Now you have " + duke.tasklist.size()  + " tasks in the list.");

      } else if (input.split(" ")[0].equals("delete")) {
        try {
          if (input.length() == 4) {
            throw new DescriptionError("☹ OOPS!!! The description of "
                    + "a delete task cannot be empty.");
          }
          if (input.split(" ").length > 2) {
            throw new UnknownInputError("☹ OOPS!!! I'm sorry,"
                    + " but I don't know what that means :-(");
          }
          if (Integer.parseInt(input.split(" ")[1])
                  / Integer.parseInt(input.split(" ")[1]) != 1) {
            throw new NumberFormatException();
          }
          if (Integer.parseInt(input.split(" ")[1]) > duke.tasklist.size()) {
            throw new DescriptionError("☹ OOPS!!! The task is not in the list.");
          }
          if (input.split(" ").length == 2 && Integer.parseInt(input.split(" ")[1])
                  / Integer.parseInt(input.split(" ")[1]) == 1) {
            duke.delete(Integer.parseInt(input.split(" ")[1]));
          }
        } catch (DescriptionError | UnknownInputError | NumberFormatException e) {
          if (e instanceof  NumberFormatException) {
            System.out.println("☹ OOPS!!! The description of a done task needs to be an integer.");
            continue;
          } else {
            System.out.println(e.getMessage());
            continue;
          }
        }
      } else {
        try {
          throw new UnknownInputError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (UnknownInputError e) {
          System.out.println(e.getMessage());
        }
      }

    }

  }



}

