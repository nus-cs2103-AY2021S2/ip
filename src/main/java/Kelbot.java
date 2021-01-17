import java.util.*;

public class Kelbot {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Hello! I'm Kelbot\n" + "What can I do for you?");
    List<Task> taskList = new ArrayList<>();
    String input = sc.nextLine();
    String[] commands = input.split(" ");
    while (true) {
      if (input.equals("bye")) {
        break;
      } else if (input.equals("list")) {
        // List out all the tasks
        for (int i = 1; i <= taskList.size(); i++) {
          System.out.println(i + "." + taskList.get(i - 1));
        }
      } else if (commands[0].equals("done")) {
        // Marks a completed task as done
        int index = Integer.parseInt(commands[1]);
        Task taskToComplete = taskList.get(index - 1);
        taskToComplete.complete();
        System.out.println("Well done! You have completed this task!");
        System.out.println(taskToComplete);
      } else if (commands[0].equals("todo")) {
        // Add todotask to list
        String name = "";
        for (int i = 1; i < commands.length; i++) {
          name += commands[i] + " ";
        }
        TodoTask newTodoTask = new TodoTask(name);
        taskList.add(newTodoTask);
        System.out.println("Okay! I have added:");
        System.out.println(newTodoTask);
        System.out.println("Now there are " + taskList.size() + " tasks on the list");
      } else if (commands[0].equals("deadline")) {
        // Add deadline task to list
        String name = "";
        String by = "";
        for (int i = 1; i < commands.length; i++) {
          if (commands[i].equals("/by")) {
            for (int j = i + 1; j < commands.length; j++) {
              by += " " + commands[j];
            }
            break;
          }
          name += commands[i] + " ";
        }
        DeadlineTask newDeadlineTask = new DeadlineTask(name, by);
        taskList.add(newDeadlineTask);
        System.out.println("Okay! I have added:");
        System.out.println(newDeadlineTask);
        System.out.println("Now there are " + taskList.size() + " tasks on the list");
      } else if (commands[0].equals("event")) {
        // Add event task to list
        String name = "";
        String at = "";
        for (int i = 1; i < commands.length; i++) {
          if (commands[i].equals("/at")) {
            for (int j = i + 1; j < commands.length; j++) {
              at += " " + commands[j];
            }
            break;
          }
          name += commands[i] + " ";
        }
        EventTask newEventTask = new EventTask(name, at);
        taskList.add(newEventTask);
        System.out.println("Okay! I have added:");
        System.out.println(newEventTask);
        System.out.println("Now there are " + taskList.size() + " tasks on the list");
      }
      input = sc.nextLine();
      commands = input.split(" ");
    }
    System.out.println("Bye le Bye!");
  }
}
