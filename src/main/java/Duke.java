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

            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size() ; i++ ) {
                    System.out.println((i + 1) + "."
                            + taskList.get(i).getTypeIcon()
                            + taskList.get(i).getStatusIcon() + " "
                            + taskList.get(i).getDescription()
                    );
                }

            } else if(command.equals("done")) {
                Task task = taskList.get(Integer.parseInt(tokens[1]) - 1);
                task.markAsDone();

                System.out.println("Nice! I've marked this task as done: \n"
                        + task.getStatusIcon() + " "
                        + task.getDescription()
                );

            } else if (command.equals("todo")) {
                Task task = new Todo(tokens[1]);
                taskList.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                        + task.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            } else if (command.equals("deadline")) {
                String[] split = tokens[1].split("/by", 2);
                Task task = new Deadlines(split[0], split[1]);
                taskList.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                        + task.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            } else if (command.equals("event")) {
                String[] split = tokens[1].split("/at", 2);
                Task task = new Events(split[0], split[1]);
                taskList.add(task);

                System.out.println("Got it. I have added this task:");
                System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                        + task.getDescription());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            } else {
                Task task = new Task(input);
                taskList.add(task);
                System.out.println("added: " + task.getDescription());
            }
        }
    }
}
