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
            String[] tokens = input.split(" ");
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
                Task t = new Task(input);
                tasks[i] = t;
                i++;
                System.out.println("added: " + input);
                System.out.println("------------------------------------------------");
            }
        };

        sc.close();
        System.out.println("------------------------------------------------");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("------------------------------------------------");
    }
}
