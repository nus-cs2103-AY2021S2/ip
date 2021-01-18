import java.util.Scanner;

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

        Task[] tasks = new Task[101];
        int index = 1;

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(lines);
            if (input.equals("list")) {
                System.out.println(indentation + "Here are the tasks in your list:");
                for (int i = 1; i < index; i++) {
                    Task currTask = tasks[i];
                    String output = indentation + i + "." + currTask.toString();
                    System.out.println(output);
                }
            } else if (input.contains("done")){
                int number = Integer.parseInt(input.substring(5));
                tasks[number].markDone();
                System.out.println(indentation + "Nice! I've marked this task as done:");
                System.out.println(indentation + "  " + tasks[number].toString());
            } else if (input.contains("todo")) {
                String task = input.substring(5);
                System.out.println(indentation + "Got it. I've added this task:");
                Task newTask = new Task(task, 0);
                System.out.println(indentation + "  " + newTask);
                tasks[index] = newTask;
                System.out.println(indentation + "Now you have " + index + " tasks in the list.");
                index++;
            } else if (input.contains("deadline")) {
                String task = input.substring(9);
                System.out.println(indentation + "Got it. I've added this task:");
                Task newTask = new Task(task, 1);
                System.out.println(indentation + "  " + newTask);
                tasks[index] = newTask;
                System.out.println(indentation + "Now you have " + index + " tasks in the list.");
                index++;
            } else if (input.contains("event")) {
                String task = input.substring(6);
                System.out.println(indentation + "Got it. I've added this task:");
                Task newTask = new Task(task, 2);
                System.out.println(indentation + "  " + newTask);
                tasks[index] = newTask;
                System.out.println(indentation + "Now you have " + index + " tasks in the list.");
                index++;
            } else {
                System.out.println(indentation + "Please specify task type!");
            }
            System.out.println(lines);
            input = sc.nextLine();
        }
        System.out.println(lines);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(lines);
    }
}
