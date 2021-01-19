import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Amanda :)\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        ArrayList<Task> list = new ArrayList<Task>();
        int index = 0;
        while (!command.equals("bye")) {
            String[] tokens = command.split(" ");
            String commandType = tokens[0];
            try {
                if (commandType.equals("list")) {
                    // SHOW LIST
                    System.out.println("Here are the tasks in your list:");
                    int i = 1;
                    for (Task x : list) {
                        System.out.printf("%d. " + x + "\n", i);
                        i++;
                    }
                } else if (commandType.equals("done")) {
                    // MARK TASK AS COMPLETE
                    int taskIndex = Integer.parseInt(tokens[1]) - 1;
                    list.get(taskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" +  list.get(taskIndex).toString());
                } else if (commandType.equals("todo"))  {
                    // ADD TODO TASK
                    try {
                        String todo = command.substring(5);
                        Task task = new Todo(todo);
                        list.add(task);
                        index++;
                        System.out.println("Got it. I've added this task: \n " + list.get(index - 1));
                        System.out.printf("Now you have %d task(s) in the list. \n", index);
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else  if (commandType.equals("deadline")) {
                    // ADD DEADLINE TASK
                    String description = command.substring(9);
                    String[] deadlineSplit = description.split("/by");
                    Task task = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                    list.add(task);
                    index++;
                    System.out.println("Got it. I've added this task: \n " + list.get(index - 1));
                    System.out.printf("Now you have %d task(s) in the list. \n", index);
                } else if (commandType.equals("event")) {
                    // ADD EVENT TASK
                    String description = command.substring(6);
                    String[] eventSplit = description.split("/at");
                    Task task = new Event(eventSplit[0], eventSplit[1]);
                    list.add(task);
                    index++;
                    System.out.println("Got it. I've added this task: \n " + list.get(index - 1));
                    System.out.printf("Now you have %d task(s) in the list. \n", index);
                } else if (commandType.equals("delete")) {
                    int taskIndex = Integer.parseInt(tokens[1]) - 1;
                    Task task = list.get(taskIndex);
                    list.remove(taskIndex);
                    index--;
                    System.out.println("Noted. I've removed this task: \n " + task.toString());
                    System.out.printf("Now you have %d tasks in the list.\n", index);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            command = scanner.nextLine();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}