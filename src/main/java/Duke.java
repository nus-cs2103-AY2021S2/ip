import CustomExceptions.*;
import Tasks.*;

import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<String> validActions
                = new ArrayList<>(Arrays.asList("todo", "deadline", "event", "done", "delete", "list", "bye"));

        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        while (true) {
            InputHandler handler = new InputHandler(sc.nextLine());

            String action = handler.getAction();
            String description = handler.getDescription();
            String by = handler.getBy();
            String at = handler.getAt();

            try {
                if (!validActions.contains(action)) {
                    throw new InvalidActionException(action);
                }
            } catch (InvalidActionException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (action.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (action.equals("list")) {
                if (tasks.getSize() == 0) {
                    System.out.println("You have no tasks in your list yet :)");
                } else {
                    System.out.println("Here are the task(s) in your list:");
                    tasks.printTasks();
                }
                continue;
            }

            try {
                if (description.length() == 0) {
                    throw new MissingDescriptionException(action);
                }
            } catch (MissingDescriptionException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (action.equals("done") || action.equals("delete")) {
                try {
                    if (!isInteger(description)) {
                        throw new InvalidTaskNumberException();
                    }

                    int taskNumber = Integer.parseInt(description);
                    if (taskNumber > tasks.getSize()) {
                        throw new TaskNumberDoesNotExistException(taskNumber);
                    }

                    if (action.equals("done")) {
                        Task doneTask = tasks.getTaskByIndex(taskNumber);
                        doneTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(doneTask.getStatusString());
                        continue;
                    } else {
                        Task deletedTask = tasks.popTaskByIndex(taskNumber);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedTask.getStatusString());
                        continue;
                    }

                } catch (InvalidTaskNumberException | TaskNumberDoesNotExistException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }


            Task newTask;

            if (action.equals("todo")) {
                newTask = new ToDo(description);
            } else if (action.equals("deadline")) {
                try {
                    if (by.length() == 0) {
                        throw new MissingDeadlineException();
                    }
                    newTask = new Deadline(description, by);
                } catch (MissingDeadlineException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else {
                try {
                    if (at.length() == 0) {
                        throw new MissingEventTimeException();
                    }
                    newTask = new Event(description, at);
                } catch (MissingEventTimeException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            tasks.addTask(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask.getStatusString());
            System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
        }
    }

    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
