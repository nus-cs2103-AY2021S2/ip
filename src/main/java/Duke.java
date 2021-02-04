import Tasks.*;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean appIsRunning = true;
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        while (appIsRunning) {
            InputHandler handler = new InputHandler(sc.nextLine());

            if (!handler.inputsAreValid()) {
                continue;
            }

            String action = handler.getAction();
            String description = handler.getDescription();
            String by = handler.getBy();
            String at = handler.getAt();

            switch (action) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    appIsRunning = false;
                    break;

                case "list":
                    if (tasks.getSize() == 0) {
                        System.out.println("You have no tasks in your list yet :)");
                    } else {
                        System.out.println("Here are the task(s) in your list:");
                        tasks.printTasks();
                    }
                    break;

                case "done":
                    Task doneTask = tasks.getTaskByIndex(Integer.parseInt(description));
                    if (null != doneTask) {
                        doneTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(doneTask.getStatusString());
                    }
                    break;

                case "delete":
                    Task deletedTask = tasks.popTaskByIndex(Integer.parseInt(description));
                    if (null != deletedTask) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedTask.getStatusString());
                    }
                    break;

                case "todo":
                case "deadline":
                case "event":
                    Task newTask;
                    if (action.equals("todo")) {
                        newTask = new ToDo(description);
                    } else if (action.equals("deadline")) {
                        newTask = new Deadline(description, by);
                    } else {
                        newTask = new Event(description, at);
                    }

                    tasks.addTask(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.getStatusString());
                    System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
                    break;
            }
        }
    }
}
