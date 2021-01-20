import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hey yo, I'm List.\nI store your words. \n";
        String goodbye = "    Bye bye, catch you soon.";
        Scanner sc = new Scanner(System.in);
        String input = "";
        Task[] listOfTasks = new Task[100];
        int numberOfTasks = 0;

        System.out.println(greeting);
        input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 1; i <= numberOfTasks; i++) {
                    System.out.println("    " + i + ". " + listOfTasks[i - 1].getStatus());
                }
                System.out.println();
            } else if (input.split(" ")[0].equals("done")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                listOfTasks[taskNumber - 1].setDone(true);
                System.out.println(
                        "    Nice! I've marked this task as done: \n" + "      " +
                                listOfTasks[taskNumber - 1].getStatus());
            } else {
                listOfTasks[numberOfTasks] = new Task(input);
                System.out.println("    added: " + input + "\n");
                numberOfTasks++;
            }

            input = sc.nextLine();
        }

        System.out.println(goodbye);
    }
}
