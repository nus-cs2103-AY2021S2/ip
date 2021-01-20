import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hey yo, I'm Travis.\nI make you work. \n";
        String goodbye = "    Bye bye, catch you soon.";
        Scanner sc = new Scanner(System.in);
        String input = "";
        Task[] listOfTasks = new Task[100];
        int numberOfTasks = 0;

        System.out.println(greeting);
        input = sc.nextLine();

        while (!input.equals("bye")) {
            String command = input.split(" ")[0];
            if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 1; i <= numberOfTasks; i++) {
                    System.out.println("    " + i + ". " + listOfTasks[i - 1].getStatus());
                }
                System.out.println();
            } else if (command.equals("done")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                listOfTasks[taskNumber - 1].setDone(true);
                System.out.println("    Nice! I've marked this task as done: \n" + "      "
                        + listOfTasks[taskNumber - 1].getStatus());
            } else {
                System.out.println("    Got it. I've added this task: ");
                if (command.equals("todo")) {
                    listOfTasks[numberOfTasks] = new ToDo(input.substring(5));
                } else if (command.equals("deadline")) {
                    String description = input.split("/")[0].substring(9);
                    String deadlineTime = input.split("/")[1].substring(3);
                    listOfTasks[numberOfTasks] = new Deadlines(description, deadlineTime);
                } else if (command.equals("event")) {
                    String description = input.split("/")[0].substring(6);
                    String eventTime = input.split("/")[1].substring(3);
                    listOfTasks[numberOfTasks] = new Event(description, eventTime);
                } else {
                    listOfTasks[numberOfTasks] = new Task(input);
                }
                System.out.println("      " + listOfTasks[numberOfTasks].getStatus());
                numberOfTasks++;
                System.out.println("    Now you have " + numberOfTasks + " tasks in the list.\n");
            }

            input = sc.nextLine();
        }
        sc.close();
        System.out.println(goodbye);
    }
}
