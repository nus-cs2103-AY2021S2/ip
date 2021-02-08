import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

  protected Storage store;
  protected TaskList tasklist;
  private Ui ui;

  public Parser(Storage storage, TaskList tasks) {
    this.ui = new Ui();
    store = storage;
    tasklist = tasks;
  }

  /**
   * Reads in the input by the user from the commandline and processes it.
   */

  public String userinput(String command) throws IOException, DescriptionError {
    String input = command;
    String outputMessage = "";
    if (input.equals("bye")) {
      outputMessage = ui.bye();
    } else if (input.equals("list")) {
      outputMessage = tasklist.listTask();
    } else if (input.split(" ")[0].equals("done")) {
      outputMessage = done(input, tasklist, outputMessage);
      store.save(tasklist.getTasklist());
    } else if (input.split(" ")[0].equals("find")) {
      outputMessage = tasklist.findTask(input.split(" ")[1]);
    } else if (input.split(" ")[0].equals("todo")) {
      outputMessage = todo(input, tasklist, outputMessage);
      store.save(tasklist.getTasklist());
    } else if (input.split(" ")[0].equals("deadline")) {
      outputMessage = deadline(input, tasklist, outputMessage);
      store.save(tasklist.getTasklist());
    } else if (input.split(" ")[0].equals("event")) {
      outputMessage = event(input, tasklist, outputMessage);
      store.save(tasklist.getTasklist());
    } else if (input.split(" ")[0].equals("delete")) {
      outputMessage = deleteTask(input, tasklist, outputMessage);
      store.save(tasklist.getTasklist());

    } else {
      try {
        throw new UnknownInputError("☹ OOPS!!! I'm sorry, "
                     + "but I don't know what that means :-(");
      } catch (UnknownInputError e) {
        outputMessage += e.getMessage();
      }
    }
    return outputMessage;
  }



  /**
   * Handles "done" command by the user by marking the tasks with "x".
   */

  public String done(String input, TaskList taskList, String output) {
    try {
      if (input.length() == 4) {
        output = "☹ OOPS!!! The description of a done task cannot be empty.";
        throw new DescriptionError("☹ OOPS!!! The description of a done task cannot be empty.");
      }
      if (input.split(" ").length > 2) {
        output = "☹ OOPS!!! I'm sorry,"
                + " but I don't know what that means :-(";
        throw new UnknownInputError("☹ OOPS!!! I'm sorry,"
                            + " but I don't know what that means :-(");
      }
      if (Integer.parseInt(input.split(" ")[1])
              / Integer.parseInt(input.split(" ")[1]) != 1) {
        throw new NumberFormatException();
      }
      if (Integer.parseInt(input.split(" ")[1]) > taskList.getTasklist().size()) {
        output = "☹ OOPS!!! The task is not in the list.";
        throw new DescriptionError("☹ OOPS!!! The task is not in the list.");
      }
      if (input.split(" ").length == 2 && Integer.parseInt(input.split(" ")[1])
              / Integer.parseInt(input.split(" ")[1]) == 1) {
        taskList.getTasklist().get((Integer.parseInt(input.split(" ")[1]) - 1)).taskDone();

        output = "Nice! I've marked this task as done: "
                + taskList.getTasklist().get((Integer.parseInt(input.split(" ")[1]) - 1));
      }
    } catch (DescriptionError | UnknownInputError | NumberFormatException e) {
      if (e instanceof NumberFormatException) {
        output = "☹ OOPS!!! The description of a done task needs to be an integer.";
      } else {
        output = e.getMessage();
      }
    }
    return output;
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

  public String todo(String input, TaskList taskList, String output) throws DescriptionError {
    try {
      if (input.length() == 4) {
        output += "☹ OOPS!!! The description of a todo cannot be empty.";
        throw new DescriptionError("☹ OOPS!!! The description of a todo cannot be empty.");
      }
    } catch (DescriptionError e) {
      output += e.getMessage();
    }
    if (input.length() != 4) {
      String inputDes = this.inputEventDescription(input);
      Todo task = new Todo(inputDes);
      if (detectDuplicate(task, taskList) == 1) {
        output += "This task is already inside!";
      }
      if (detectDuplicate(task, taskList) != 1) {
        output += "Got it. I've added this task: " + "\n";
        taskList.getTasklist().add(task);
        output += "Now you have " + taskList.getTasklist().size() + " tasks in the list.";
      }
    }
    return output;
  }


  /**
   * Handles deadline command by the user by creating a deadline object.
   */

  public String deadline(String input, TaskList taskList, String output) {
    try {
      if (input.length() == 8) {
        output += "☹ OOPS!!! The description of a "
                + "deadline cannot be empty.";
        throw new DescriptionError("☹ OOPS!!! The description of a "
                + "deadline cannot be empty.");
      }
    } catch (DescriptionError e) {
      output += e.getMessage();
    }
    if (input.length() != 8) {
      Deadline task = new Deadline(input.split(" ")[1] + " " + input.split(" ")[2],
              input.split("/by ")[1]);
      if (task.dateError) {
        return "";
      } else {
        if (detectDuplicate(task, taskList) == 1) {
          output += "This task is already inside!";
        }
        if (detectDuplicate(task, taskList) != 1) {
          output += "Got it. I've added this task: " + "\n";
          taskList.addList(task);
          output += task + "\n";
          output += "Now you have " + taskList.getTasklist().size() + " tasks in the list.";
        }
      }
    }
    return output;
  }

  /**
   * Handles event command by the user by creating event object and placing it into the arraylist.
   */

  public String event(String input, TaskList taskList, String output) {
    try {
      if (input.length() == 5) {
        output += "☹ OOPS!!! The description of a event cannot be empty.";
        throw new DescriptionError("☹ OOPS!!! The description of a event cannot be empty.");
      }
    } catch (DescriptionError e) {
      output += e.getMessage();
    }
    if (input.length() != 5) {
      Event task = new Event(input.split(" ")[1] + " " + input.split(" ")[2],
                      input.split("/at ")[1]);
      if (detectDuplicate(task, taskList) == 1) {
        output += "This task is already inside!";
      }
      if (detectDuplicate(task, taskList) != 1) {
        output += "Got it. I've added this task: " + "\n";
        taskList.getTasklist().add(task);
        output += task + "\n";
        output += "Now you have " + taskList.getTasklist().size() + " tasks in the list.";
      }
    }
    return output;
  }

  /**
   * Handles delete ommand by the user by removing the tasks inside the arraylist.
   */

  public String deleteTask(String input, TaskList taskList, String output) {
    try {
      if (input.length() == 4) {
        output += "☹ OOPS!!! The description of "
                + "a delete task cannot be empty.";
        throw new DescriptionError("☹ OOPS!!! The description of "
                          + "a delete task cannot be empty.");
      }
      if (input.split(" ").length > 2) {
        output += "☹ OOPS!!! I'm sorry,"
                + " but I don't know what that means :-(";
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
        output += "☹ OOPS!!! The description of a done task needs to be an integer.";
      } else {
        output += e.getMessage();
      }
    }
    return output;
  }

  /**
   * returns 1 if task is already inside the tasklist or 0 if it is absent.
   * @ param input The new task object that has been instantiated
   * @ param taskList  tasklist object which contains the list where all the task is stored
   */
  public Integer detectDuplicate(Task input, TaskList taskList) {
    for (Task t : taskList.getTasklist()) {
      if (t.toString().equals(input.toString())) {
        return 1;
      } else {
        return 0;
      }
    }
    return 0;
  }

}






