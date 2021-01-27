import java.util.*;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {

        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.dir") + "/data/", "duke.txt");
        this.taskList = storage.load();

    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        ui.printWelcomeMessage();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            String[] tokens = input.split(" ", 2);

            String command = tokens[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                storage.save(taskList);
                break;

            } else if (command.equals("list")) {
                if (taskList.size() == 0) {
                    System.out.println("There are no tasks in your task list");
                } else {
                    System.out.println("Here are the tasks in your task list:");
                    for (int i = 0; i < taskList.size() ; i++ ) {
                        System.out.println((i + 1) + ". "
                                + taskList.get(i).getTypeIcon()
                                + taskList.get(i).getStatusIcon() + " "
                                + taskList.get(i).getDescription()
                        );
                    }
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
                String description = split[0];
                String by = split.length > 1 ? split[1].strip() : null;
                Task task = new Deadlines(description, by);
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
                String description = split[0];
                String at = split.length > 1 ? split[1] : null;
                Task task = new Events(description, at);
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

    public static void main(String[] args) {

        new Duke().run();

    }
}
