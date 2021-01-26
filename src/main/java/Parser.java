import java.io.IOException;
import java.util.Scanner;

public class Parser {

  public Parser() {

  }

  /**
   * Reads in the input by the user from the commandline and processes it.
   */

  public void userinput(TaskList tasklist, Storage store) throws IOException, DescriptionError {
    Scanner scan = new Scanner(System.in);
    while (scan.hasNext()) {
      String input = scan.nextLine();
      if (input.equals("bye")) {
        Ui ui = new Ui();
        ui.bye();
      } else if (input.equals("list")) {
        tasklist.list();
      } else if (input.split(" ")[0].equals("done")) {
        done(input, tasklist);
        store.save(tasklist.getTasklist());
      } else if (input.split(" ")[0].equals("find")) {
        tasklist.findtask(input.split(" ")[1]);
      } else if (input.split(" ")[0].equals("todo")) {
        todo(input, tasklist);
        store.save(tasklist.getTasklist());
      } else if (input.split(" ")[0].equals("deadline")) {
        deadline(input, tasklist);
        store.save(tasklist.getTasklist());
      } else if (input.split(" ")[0].equals("event")) {
        event(input, tasklist);
        store.save(tasklist.getTasklist());
      } else if (input.split(" ")[0].equals("delete")) {
        deleteTask(input, tasklist);
        store.save(tasklist.getTasklist());
      } else {
        try {
          throw new UnknownInputError("☹ OOPS!!! I'm sorry, "
                     + "but I don't know what that means :-(");
        } catch (UnknownInputError e) {
          System.out.println(e.getMessage());
        }
      }
    }

  }

  /**
   * Handles "done" command by the user by marking the tasks with "x".
   */

  public void done(String input, TaskList taskList) {
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
      if (Integer.parseInt(input.split(" ")[1]) > taskList.getTasklist().size()) {
        throw new DescriptionError("☹ OOPS!!! The task is not in the list.");
      }
      if (input.split(" ").length == 2 && Integer.parseInt(input.split(" ")[1])
              / Integer.parseInt(input.split(" ")[1]) == 1) {
        taskList.getTasklist().get((Integer.parseInt(input.split(" ")[1]) - 1)).taskDone();
        System.out.println(" ___________________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskList.getTasklist().get((Integer.parseInt(input.split(" ")[1]) - 1)));
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

  /**
   * Handles todo command by the user by creating a todo object and placing it into the arraylist.
   */

  public void todo(String input, TaskList taskList) throws DescriptionError {
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
      taskList.getTasklist().add(task);
      System.out.println("Now you have " + taskList.getTasklist().size() + " tasks in the list.");
    }
  }


  /**
   * Handles deadline command by the user by creating a deadline object.
   */

  public void deadline(String input, TaskList taskList) {
    try {
      if (input.length() == 8) {
        throw new DescriptionError("☹ OOPS!!! The description of a "
                + "deadline cannot be empty.");
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
        taskList.addList(task);
        System.out.println(task);
        System.out.println("Now you have " + taskList.getTasklist().size() + " tasks in the list.");
      }
    }
  }

  /**
   * Handles event command by the user by creating event object and placing it into the arraylist.
   */

  public void event(String input, TaskList taskList) {
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
      taskList.getTasklist().add(task);
      System.out.println(task);
      System.out.println("Now you have " + taskList.getTasklist().size() + " tasks in the list.");
    }
  }

  /**
   * Handles delete ommand by the user by removing the tasks inside the arraylist.
   */

  public void deleteTask(String input, TaskList taskList) {
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
      if (Integer.parseInt(input.split(" ")[1]) > taskList.getTasklist().size()) {
        throw new DescriptionError("☹ OOPS!!! The task is not in the list.");
      }
      if (input.split(" ").length == 2 && Integer.parseInt(input.split(" ")[1])
                      / Integer.parseInt(input.split(" ")[1]) == 1) {
        taskList.delete(Integer.parseInt(input.split(" ")[1]));
      }
    } catch (DescriptionError | UnknownInputError | NumberFormatException e) {
      if (e instanceof NumberFormatException) {
        System.out.println("☹ OOPS!!! The description of a done task needs to be an integer.");
      } else {
        System.out.println(e.getMessage());
      }
    }
  }






}






