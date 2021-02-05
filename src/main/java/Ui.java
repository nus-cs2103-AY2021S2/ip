import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Ui {

    public TaskList run(TaskList tasks) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            Parser parser = new Parser(sc.nextLine());

            if (!parser.inputsAreValid()) {
                continue;
            }

            String action = parser.getAction();
            String description = parser.getDescription();
            LocalDateTime by = parser.getBy();
            LocalDateTime at = parser.getAt();

            switch (action) {
                case "bye":
                    handleBye();
                    return tasks;
                case "list":
                    handleList(tasks);
                    break;
                case "done":
                    handleDone(tasks, description);
                    break;
                case "delete":
                    handleDelete(tasks, description);
                    break;
                case "todo":
                    handleToDo(tasks, description);
                    break;
                case "deadline":
                    handleDeadline(tasks, description, by);
                    break;
                case "event":
                    handleEvent(tasks, description, at);
                    break;
            }
        }
    }

    private static void handleBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no tasks in your list yet :)");
        } else {
            System.out.println("Here are the task(s) in your list:");
            tasks.printTasks();
        }
    }

    private static void handleDone(TaskList tasks, String description) {
        Task doneTask = tasks.getTaskByIndex(Integer.parseInt(description));
        if (null != doneTask) {
            doneTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(doneTask.getStatusString());
        }
    }

    private static void handleDelete(TaskList tasks, String description) {
        Task deletedTask = tasks.popTaskByIndex(Integer.parseInt(description));
        if (null != deletedTask) {
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask.getStatusString());
        }
    }

    private static void handleToDo(TaskList tasks, String description) {
        ToDo toDo = new ToDo(description);
        addTask(tasks, toDo);
    }

    private static void handleDeadline(TaskList tasks, String description, LocalDateTime by) {
        Deadline deadline = new Deadline(description, by);
        addTask(tasks, deadline);
    }

    private static void handleEvent(TaskList tasks, String description, LocalDateTime at) {
        Event event = new Event(description, at);
        addTask(tasks, event);
    }

    private static void addTask(TaskList tasks, Task newTask) {
        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getStatusString());
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
    }
}
