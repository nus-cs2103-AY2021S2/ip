import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];
        int i = 0;

        System.out.println("------------------------------------------------");
        System.out.println("Hi! I'm Timmy!\nWhat can Timmy do for you today?");
        System.out.println("------------------------------------------------");

        while (!(input = sc.nextLine()).equals("bye")) {
            String[] tokens = input.split(" ", 2);
            if (tokens[0].equals("list")) {
                System.out.println("------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + "." + tasks[j].toString());
                }
                System.out.println("------------------------------------------------");
            } else if (tokens[0].equals("done")) {
                System.out.println("------------------------------------------------");
                int tasksIndex = Integer.parseInt(tokens[1]) - 1;
                tasks[tasksIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks[tasksIndex].toString());
                System.out.println("------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------");
                Task task = new Task();

                switch(tokens[0]) {
                case "todo":
                    {
                        task = new ToDo(tokens[1]);
                        break;
                    }
                case "deadline":
                    {
                        String[] nextTokens = tokens[1].split(" /by ", 2);
                        task = new Deadline(nextTokens[0], nextTokens[1]);
                        break;
                    }
                case "event":
                    {
                        String[] nextTokens = tokens[1].split(" /at ", 2);
                        task = new Event(nextTokens[0], nextTokens[1]);
                        break;
                    }
                default:
                    {
                        System.out.println("Invalid task!");
                        break;
                    }
                }

                tasks[i] = task;
                i++;
                System.out.println("Ok! I've added this task:\n" + task.toString());
                System.out.println("Currently, you have " + i + " task(s) in the list!");
                System.out.println("------------------------------------------------");
            }
        };

        sc.close();
        System.out.println("------------------------------------------------");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("------------------------------------------------");
    }
}
