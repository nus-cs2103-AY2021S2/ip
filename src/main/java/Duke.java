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

        ArrayList<Task> listOfTasks = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    System.out.println((i + 1) + ". " + listOfTasks.get(i));
                }
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                try {
                    listOfTasks.get(index - 1).setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listOfTasks.get(index - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You have " + listOfTasks.size() + " tasks in your list. Please check your input.");
                }
            } else if (input.startsWith("todo")) {
                String task = input.substring(input.indexOf(" ") + 1);
                Task temp = new ToDo(task);
                listOfTasks.add(temp);
                printUpdate(temp, listOfTasks.size());
            } else {
                try {
                    String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                    Task temp = null;
                    if (input.startsWith("deadline ")) {
                        String deadline = input.substring(input.indexOf("/by") + 4);
                        temp = new Deadline(taskName, deadline);
                    } else if (input.startsWith("event ")) {
                        String eventTime = input.substring(input.indexOf("/at") + 4);
                        temp = new Event(taskName, eventTime);
                    }
                    listOfTasks.add(temp);
                    printUpdate(temp, listOfTasks.size());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("The timing of the task is not included. Please check your input.");
                }
            }
            input = sc.nextLine();
        }
        System.out.println("Bye Bye. Please give me 5-star rating, I still need this job." +
                "\nMuch thanks.");
    }

    public static void printUpdate(Task t, int size) {
        System.out.println("Got it. I have added this task:");
        System.out.println(t);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
