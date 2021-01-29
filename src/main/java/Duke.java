import java.util.*;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        List<Task> taskList = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            String[] tokens = input.split(" ", 2);

            String command = tokens[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your task list:");
                for (int i = 0; i < taskList.size() ; i++ ) {
                    System.out.println((i + 1) + "."
                            + taskList.get(i).getTypeIcon()
                            + taskList.get(i).getStatusIcon() + " "
                            + taskList.get(i).getDescription()
                    );
                }

            } else if(command.equals("done")) {

                // check for correct number of arguments
                if (tokens.length < 2) {
                    System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
                    continue;
                }

                // check if argument is an integer
                int taskId;
                try {
                    taskId = Integer.parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! The id of a done must be an integer.");
                    continue;
                }

                // check if integer is within bounds
                if (taskId >= taskList.size() || taskId < 0) {
                    System.out.println("☹ OOPS!!! That is an invalid task id.");
                    continue;
                }

                Task task = taskList.get(taskId);
                task.markAsDone();

                System.out.println("Nice! I've marked this task as done: \n"
                        + task.getStatusIcon() + " "
                        + task.getDescription()
                );

            } else if (command.equals("todo")) {
                if (tokens.length < 2) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }

                Task task = new Todo(tokens[1]);
                taskList.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                        + task.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list");

            } else if (command.equals("deadline")) {
                if (tokens.length < 2) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                }

                String[] split = tokens[1].split("/by", 2);
                Task task = new Deadlines(split[0], split[1]);
                taskList.add(task);

                System.out.println("Got it. I have added this task:");
                System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                        + task.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            } else if (command.equals("event")) {
                if (tokens.length < 2) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    continue;
                }

                String[] split = tokens[1].split("/at", 2);
                Task task = new Events(split[0], split[1]);
                taskList.add(task);

                System.out.println("Got it. I have added this task:");
                System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                        + task.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            } else if (command.equals("delete")) {
                if (tokens.length < 2) {
                    System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
                    continue;
                }

                int taskId;
                try {
                    taskId = Integer.parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! The id of a delete must be an integer.");
                    continue;
                }

                if (taskId >= taskList.size() || taskId < 0) {
                    System.out.println("☹ OOPS!!! That is an invalid task id.");
                    continue;
                }

                Task task = taskList.remove(taskId - 1);

                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                        + task.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

}
