import task.*;
import storage.Storage;
import ui.Ui;

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

        boolean isExit = false;

        while(sc.hasNextLine()) {

            String input = sc.nextLine();

            String[] tokens = input.split(" ", 2);

            String command = tokens[0];
            String argument = tokens.length == 2 ? tokens[1] : null;

            if (command.equals("bye")) {
                isExit = executeByeCommand();

            } else if (command.equals("list")) {
                isExit = executeListCommand();

            } else if(command.equals("done")) {
                isExit = executeDoneCommand(argument);

            } else if (command.equals("todo")) {
                isExit = executeTodoCommand(argument);

            } else if (command.equals("deadline")) {
                isExit = executeDeadlineCommand(argument);

            } else if (command.equals("event")) {
                isExit = executeEventCommand(argument);

            } else if (command.equals("delete")) {
                isExit = executeDeleteCommand(argument);
            } else if (command.equals("find")) {
                isExit = executeFindCommand(argument);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            if (isExit) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        new Duke().run();

    }

    private boolean executeByeCommand() {
        System.out.println("Bye. Hope to see you again soon!");
        storage.save(taskList);
        return true;
    }

    private boolean executeListCommand() {
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

        return false;
    }

    private boolean executeDoneCommand(String argument) {
        // check for correct number of arguments
        if (argument == null) {
            System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
            return false;
        }

        // check if argument is an integer
        int taskId;
        try {
            taskId = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! The id of a done must be an integer.");
            return false;
        }

        // check if integer is within bounds
        if (taskId >= taskList.size() || taskId < 0) {
            System.out.println("☹ OOPS!!! That is an invalid task id.");
            return false;
        }

        Task task = taskList.get(taskId);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done: \n"
                + task.getStatusIcon() + " "
                + task.getDescription()
        );

        return false;
    }

    private boolean executeTodoCommand(String argument) {
        if (argument == null) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return false;
        }

        Task task = new Todo(argument);
        taskList.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription());
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        return false;
    }

    private boolean executeDeadlineCommand(String argument) {
        if (argument == null) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            return false;
        }

        String[] split = argument.split("/by", 2);
        String description = split[0];
        String by = split.length > 1 ? split[1].strip() : null;
        Task task = new Deadlines(description, by);
        taskList.add(task);

        System.out.println("Got it. I have added this task:");
        System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        return false;
    }

    private boolean executeEventCommand(String argument) {
        if (argument == null) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return false;
        }

        String[] split = argument.split("/at", 2);
        String description = split[0];
        String at = split.length > 1 ? split[1] : null;
        Task task = new Events(description, at);
        taskList.add(task);

        System.out.println("Got it. I have added this task:");
        System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        return false;
    }

    private boolean executeDeleteCommand(String argument) {
        if (argument == null) {
            System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
            return false;
        }

        int taskId;
        try {
            taskId = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! The id of a delete must be an integer.");
            return false;
        }

        if (taskId >= taskList.size() || taskId < 0) {
            System.out.println("☹ OOPS!!! That is an invalid task id.");
            return false;
        }

        Task task = taskList.remove(taskId - 1);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        return false;
    }

    private boolean executeFindCommand(String argument) {
        if (argument == null) {
            System.out.println("☹ OOPS!!! The description of a find cannot be empty.");
            return false;
        }

        TaskList subList = new TaskList();
        for (Task task: taskList.getList()) {
            String description = task.getDescription();
            if (description.contains(argument)) {
                subList.add(task);
            }
        }

        if (subList.size() == 0) {
            System.out.println("There are no tasks that matches your search");
        } else {
            System.out.println("Here are the tasks found:");
            for (int i = 0; i < subList.size() ; i++ ) {
                System.out.println((i + 1) + ". "
                        + subList.get(i).getTypeIcon()
                        + subList.get(i).getStatusIcon() + " "
                        + subList.get(i).getDescription()
                );
            }
        }

        return false;
    }
}
