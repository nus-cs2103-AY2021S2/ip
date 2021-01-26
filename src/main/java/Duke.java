import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lines = "    ____________________________________________________________";
        String indentation = "     ";
        System.out.println("Hello from\n" + logo);
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(lines);

        LinkedList<Task> tasks = new LinkedList<>();

        System.out.println(lines);
        ImpAndExp ine = new ImpAndExp(tasks);
        try {
            ine.importData();
        } catch (IOException e) {
            System.err.println("Hope you won't see this :p");
        }
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(lines);
            if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println(indentation + "There are currently no tasks! :)");
                } else {
                    System.out.println(indentation + "Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task currTask = tasks.get(i);
                        String output = indentation + (i + 1) + "." + currTask.toString();
                        System.out.println(output);
                    }
                }
            } else if (input.contains("done")){
                int number;
                try {
                    number = (Integer.parseInt(input.substring(5))) - 1;
                    tasks.get(number).markDone();
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(indentation + "  " + tasks.get(number));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(indentation + "☹ OOPS!!! No task was input to be marked as done.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + "☹ OOPS!!! No task has been stored at that index yet.");
                } catch (NumberFormatException e) {
                    System.out.println(indentation + "☹ OOPS!!! Input index was not recognised.");
                }
            } else if (input.contains("delete")) {
                int number;
                try {
                    number = (Integer.parseInt(input.substring(7))) - 1;
                    Task removed = tasks.remove(number);
                    System.out.println(indentation + "Noted. I've removed this task: ");
                    System.out.println(indentation + "  " + removed);
                    System.out.println(indentation + "Now you have " + tasks.size() + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(indentation + "☹ OOPS!!! No task was input to be deleted.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + "☹ OOPS!!! No task has been stored at that index yet.");
                } catch (NumberFormatException e) {
                    System.out.println(indentation + "☹ OOPS!!! Input index was not recognised.");
                }
            } else if (input.contains("todo")) {
                String task = input.substring(4);
                if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
                    System.out.println(indentation + "☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Task newTask = new Task(task, 0);
                    System.out.println(indentation + "Got it. I've added this task:");
                    System.out.println(indentation + "  " + newTask);
                    tasks.add(newTask);
                    System.out.println(indentation + "Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (input.contains("deadline")) {
                String task = input.substring(8);
                if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
                    System.out.println(indentation + "☹ OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    Task newTask;
                    try {
                        newTask = new Task(task, 1);
                        System.out.println(indentation + "Got it. I've added this task:");
                        System.out.println(indentation + "  " + newTask);
                        tasks.add(newTask);
                        System.out.println(indentation + "Now you have " + tasks.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(indentation + "☹ OOPS!!! The description or date of a deadline cannot be empty.");
                    }
                }
            } else if (input.contains("event")) {
                String task = input.substring(5);
                if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
                    System.out.println(indentation + "☹ OOPS!!! The description of an event cannot be empty.");
                } else {
                    Task newTask;
                    try {
                        newTask = new Task(task, 2);
                        System.out.println(indentation + "Got it. I've added this task:");
                        System.out.println(indentation + "  " + newTask);
                        tasks.add(newTask);
                        System.out.println(indentation + "Now you have " + tasks.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(indentation + "☹ OOPS!!! The description or date of an event cannot be empty.");
                    }
                }
            } else {
                System.out.println(indentation + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(lines);
            input = sc.nextLine();
        }
        try {
            ine.exportData();
        } catch (IOException e) {
            System.err.println("Hope you won't see this :p");
        }
        System.out.println(lines);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(lines);
    }
}