import java.util.Scanner;
import java.util.ArrayList;

import task.ToDo;
import task.Deadline;
import task.Event;
import task.Task;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------");
        System.out.println("Hello! I'm Duke's friend, Ekud." +
                "\nDuke's dead, so I'm here to take his job." +
                "\nYou want to jot down some tasks?");
        run();
        System.out.println("Bye Bye. Please give me 5-star rating, I still need this job." +
                "\nMuch thanks.");
    }

    public static void run() {
        ArrayList<Task> listOfTasks = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                handleList(listOfTasks);
            } else {
                // handle the commands with arguments
                int spaceIndex = input.indexOf(" ");
                int cutOffPoint = spaceIndex == -1 ? input.length() : spaceIndex;
                String command = input.substring(0, cutOffPoint);

                switch (command) {
                    case "done":
                        handleDone(input, listOfTasks);
                        break;
                    case "todo":
                        handleTodo(input, listOfTasks);
                        break;
                    case "delete":
                        handleDelete(input, listOfTasks);
                        break;
                    case "deadline":
                    case "event":
                        handleTasksWithTime(command, input, listOfTasks);
                        break;
                    default:
                        System.out.println("I have no idea what that means, what do you want?");
                        break;
                }
            }
            input = sc.nextLine();
        }
    }

    public static void printUpdate(String preMessage, Task t, int size) {
        System.out.println(preMessage);
        System.out.println(t);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void handleList(ArrayList<Task> listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + ". " + listOfTasks.get(i));
        }
    }

    public static void handleDone(String input, ArrayList<Task> listOfTasks) {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            listOfTasks.get(index - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(listOfTasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You have " + listOfTasks.size() + " tasks in your list. Please check your input.");
        } catch (NumberFormatException e) {
            System.out.println("The input must be a positive integer!");
        }
    }

    public static void handleTodo(String input, ArrayList<Task> listOfTasks) {
        if (!input.contains(" ")) {
            System.out.println("The description of a todo cannot be empty.");
            return;
        }
        String task = input.substring(input.indexOf(" ") + 1);
        Task temp = new ToDo(task);
        listOfTasks.add(temp);
        printUpdate("Got it. I have added the following task:", temp, listOfTasks.size());
    }

    public static void handleTasksWithTime(String command, String input, ArrayList<Task> listOfTasks) {
        try {
            String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
            Task temp;
            String timing = input.substring(input.indexOf("/") + 4);
            if (command.startsWith("deadline")) {
                temp = new Deadline(taskName, timing);
            } else {
                temp = new Event(taskName, timing);
            }
            listOfTasks.add(temp);
            printUpdate("Got it. I have added the following task:", temp, listOfTasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("The timing of the task is not included. Please check your input.");
        }
    }

    public static void handleDelete(String input, ArrayList<Task> listOfTasks) {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            printUpdate(
                    "Noted. I have removed the following task:",
                    listOfTasks.remove(index - 1),
                    listOfTasks.size()
            );
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You have " + listOfTasks.size() + " tasks in your list. Please check your input.");
        } catch (NumberFormatException e) {
            System.out.println("The input must be a positive integer!");
        }
    }
}
