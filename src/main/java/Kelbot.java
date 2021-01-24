import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


import java.time.LocalDate;

public class Kelbot {
  public static void main(String[] args) throws KelbotException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Hello! I'm Kelbot\n" + "What can I do for you?");
    List<Task> taskList = new ArrayList<>();
  
    java.nio.file.Path path = java.nio.file.Paths.get("data", "Kelbot.txt");
    boolean fileExists = java.nio.file.Files.exists(path);
    if (fileExists) {
      try {
        FileInputStream fis = new FileInputStream(path.toString());
        ObjectInputStream ois = new ObjectInputStream(fis);
        taskList = (List<Task>) ois.readObject();
        ois.close();
        System.out.println("Here is your task list from your previous Kelbot usage");
        for (int i = 1; i <= taskList.size(); i++) {
          System.out.println(i + "." + taskList.get(i - 1));
        }
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
    String input = sc.nextLine();
    String[] commands = input.split(" ");
    while (true) {
      try {
        if (input.equals("bye")) {
          // Terminates programme
          break;
        } else if (input.equals("list")) {
          // List out all the tasks
          for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1));
          }
        } else if (commands[0].equals("done")) {
          // Marks a completed task as done
          try {
            if (commands.length == 1) {
              throw new KelbotException("Which task did you finish?");
            } else {
              int index = Integer.parseInt(commands[1]);
              Task taskToComplete = taskList.get(index - 1);
              taskToComplete.complete();
              System.out.println("Well done! You have completed this task!");
              System.out.println(taskToComplete);
            }
          } catch (KelbotException e) {
            System.out.println(e.getMessage());
          }
        } else if (commands[0].equals("delete")) {
          // Deletes selected task
          try {
            if (commands.length == 1) {
              throw new KelbotException("Which task do you want to delete?");
            } else {
              int index = Integer.parseInt(commands[1]);
              Task taskToDelete = taskList.remove(index - 1);
              System.out.println("Noted! You have deleted this task!");
              System.out.println(taskToDelete);
            }
          } catch (KelbotException e) {
            System.out.println(e.getMessage());
          } catch (IndexOutOfBoundsException e) {
            System.out.println("The list is empty");
          }
        } else if (commands[0].equals("todo")) {
          // Add todotask to list
          String name = "";
          try {
            for (int i = 1; i < commands.length; i++) {
              name += " " + commands[i];
            }
            if (name.equals("")) {
              throw new KelbotException("Todo name cannot be empty!");
            } else {
              TodoTask newTodoTask = new TodoTask(name);
              taskList.add(newTodoTask);
              System.out.println("Okay! I have added:");
              System.out.println(newTodoTask);
              System.out.println("Now there are " + taskList.size() + " tasks on the list");
            }
          } catch (KelbotException e) {
            System.out.println(e.getMessage());
          }
        } else if (commands[0].equals("deadline")) {
          // Add deadline task to list
          String name = "";
          try {
            for (int i = 1; i < commands.length; i++) {
              if (commands[i].equals("/by")) {
                break;
              }
              name += " " + commands[i];
            }
            LocalDate date = LocalDate.parse(commands[commands.length - 1]);
            if (name.equals("")) {
              throw new KelbotException("Deadline name cannot be empty!");
            } else if (date == null) {
              throw new KelbotException("Deadline cannot be empty!");
            } else {
              DeadlineTask newDeadlineTask = new DeadlineTask(name, date);
              taskList.add(newDeadlineTask);
              System.out.println("Okay! I have added:");
              System.out.println(newDeadlineTask);
              System.out.println("Now there are " + taskList.size() + " tasks on the list");
            }
          } catch (KelbotException e) {
            System.out.println(e.getMessage());
          }
        } else if (commands[0].equals("event")) {
          // Add event task to list
          String name = "";
          try {
            for (int i = 1; i < commands.length; i++) {
              if (commands[i].equals("/at")) {
                break;
              }
              name += " " + commands[i];
            }
            LocalDate date = LocalDate.parse(commands[commands.length - 1]);
            if (name.equals("")) {
              throw new KelbotException("Event name cannot be empty!");
            } else if (date == null) {
              throw new KelbotException("Event at cannot be empty!");
            } else {
              EventTask newEventTask = new EventTask(name, date);
              taskList.add(newEventTask);
              System.out.println("Okay! I have added:");
              System.out.println(newEventTask);
              System.out.println("Now there are " + taskList.size() + " tasks on the list");
            }
          } catch (KelbotException e) {
            System.out.println(e.getMessage());
          }
        } else {
          throw new KelbotException("Invalid Command");
        }
      } catch (KelbotException e) {
        System.out.println(e.getMessage());
      }
      File file = new File("data");
      try {
        FileOutputStream fos = new FileOutputStream(path.toString());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList);
        oos.close();
      } catch(Exception ex) {
        ex.printStackTrace();
      }
      input = sc.nextLine();
      commands = input.split(" ");
    }
    System.out.println("Bye le Bye!");
  }
}
