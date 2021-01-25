import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  protected ArrayList<Task> tasklist;

  public Duke(ArrayList<Task> tasklist) {
    this.tasklist = tasklist;
  }

  public ArrayList<Task> getTasklist() {
    return this.tasklist;
  }

  public void list() {
    int i = 1;
    System.out.println(" ___________________________________________");
    System.out.println("Here are the tasks in your list:");
    for (Task task : tasklist) {
      System.out.println(i + ". " + task);
      i++;
    }
    System.out.println(" ___________________________________________");
  }

  public void addList(Task input) {
    this.tasklist.add(input);
  }

  public String inputEventDescription(String input) {
    String[] inputs = input.split(" ");
    String output = "";
    for (int i = 1; i < inputs.length; i++) {

      if (i == 1) {
        output += inputs[i];
      } else {
        output += " " + inputs[i];
      }
    }
    return output;
  }

  public void delete(int num) {
    Task task = this.tasklist.remove(num - 1);
    System.out.println("Noted. I've removed this task: ");
    System.out.println(task);
    System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
  }

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

  public void bye() {
    System.out.println(" ___________________________________________");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println(" ___________________________________________");
  }

  public void done(String input) {
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
      if (Integer.parseInt(input.split(" ")[1]) > this.tasklist.size()) {
        throw new DescriptionError("☹ OOPS!!! The task is not in the list.");
      }

      if (input.split(" ").length == 2 && Integer.parseInt(input.split(" ")[1])
              / Integer.parseInt(input.split(" ")[1]) == 1) {
        this.tasklist.get((Integer.parseInt(input.split(" ")[1]) - 1)).taskDone();
        System.out.println(" ___________________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.tasklist.get((Integer.parseInt(input.split(" ")[1]) - 1)));
        System.out.println(" ___________________________________________");
      }
    } catch (DescriptionError | UnknownInputError | NumberFormatException e) {
      if (e instanceof NumberFormatException) {
        System.out.println("☹ OOPS!!! The description of a done task needs to be an integer.");
      } else {
        System.out.println(e.getMessage());
      }
    }

  }

  public void todo(String input) throws DescriptionError {
    try {
      if (input.length() == 4) {
        throw new DescriptionError("☹ OOPS!!! The description of a todo cannot be empty.");
      }
    } catch (DescriptionError e) {
      System.out.println(e.getMessage());
    }
    if (input.length() != 4) {
      String inputDes = this.inputEventDescription(input);
      Todo task = new Todo(inputDes);
      System.out.println(" ___________________________________________");
      System.out.println("Got it. I've added this task: ");
      this.addList(task);
      System.out.println(task);
      System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
    }
  }

  public void deadline(String input) {
    try {
      if (input.length() == 8) {
        throw new DescriptionError("☹ OOPS!!! The description of a deadline cannot be empty.");
      }
    } catch (DescriptionError e) {
      System.out.println(e.getMessage());
    }
    if (input.length() != 8) {
      Deadline task = new Deadline(input.split(" ")[1] + " " + input.split(" ")[2],
              input.split("/by ")[1]);
      if (task.dateError) {
        return;
      } else {
        System.out.println("Got it. I've added this task: ");
        this.addList(task);
        System.out.println(task);
        System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
      }
    }
  }

  public void event(String input) {
    try {
      if (input.length() == 5) {
        throw new DescriptionError("☹ OOPS!!! The description of a event cannot be empty.");
      }
    } catch (DescriptionError e) {
      System.out.println(e.getMessage());
    }
    if (input.length() != 5) {
      System.out.println(input.split("/at")[1]);
      Event task = new Event(input.split(" ")[1] + " " + input.split(" ")[2],
              input.split("/at ")[1]);
      System.out.println("Got it. I've added this task: ");
      this.addList(task);
      System.out.println(task);
      System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
    }
  }

  public void deleteTask(String input) {
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
      if (Integer.parseInt(input.split(" ")[1]) > this.tasklist.size()) {
        throw new DescriptionError("☹ OOPS!!! The task is not in the list.");
      }
      if (input.split(" ").length == 2 && Integer.parseInt(input.split(" ")[1])
              / Integer.parseInt(input.split(" ")[1]) == 1) {
        this.delete(Integer.parseInt(input.split(" ")[1]));
      }
    } catch (DescriptionError | UnknownInputError | NumberFormatException e) {
      if (e instanceof  NumberFormatException) {
        System.out.println("☹ OOPS!!! The description of a done task needs to be an integer.");

      } else {
        System.out.println(e.getMessage());
      }
    }
  }

  public static void main(String[] args) throws DescriptionError, UnknownInputError, IOException {
    //ArrayList<Task> list = new ArrayList<>();
    //Duke duke = new Duke(list);
    Store store = new Store();
    ArrayList<Task> memTask = store.load();
    Duke duke = new Duke(memTask);
    duke.greeting();
    Scanner scan = new Scanner(System.in);
    while (scan.hasNext()) {
      String input = scan.nextLine();
      if (input.equals("bye")) {
        duke.bye();
      } else if (input.equals("list")) {
        duke.list();
      } else if (input.split(" ")[0].equals("done")) {
        duke.done(input);
        store.save(duke.getTasklist());
      } else if (input.split(" ")[0].equals("todo")) {
        duke.todo(input);
        store.save(duke.getTasklist());
      } else if (input.split(" ")[0].equals("deadline")) {
        duke.deadline(input);
        store.save(duke.getTasklist());
      } else if (input.split(" ")[0].equals("event")) {
        duke.event(input);
        store.save(duke.getTasklist());
      } else if (input.split(" ")[0].equals("delete")) {
        duke.deleteTask(input);
        store.save(duke.getTasklist());
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

