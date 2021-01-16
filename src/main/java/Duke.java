import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        Task[] tasks = new Task[100];
        int k, i = 0;

        while (!string.equals("bye")) {

            if (string.equals("list")) {

                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    k = j + 1;
                    System.out.println(k + ".[" + tasks[j].getStatusIcon()
                            + "] " + tasks[j].description);
                }

            } else {

                String front = " ", back = " ";
                if (string.contains(" ")) {
                    String[] str = string.split(" ");
                    front = str[0];
                    back = str[1];
                }

                if (front.equals("done")) {
                    k = Integer.parseInt(back) - 1;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + tasks[k].getStatusIcon()
                            + "] " + tasks[k].description);
                    tasks[k].markAsDone();
                } else {
                    System.out.println("added: " + string);
                    tasks[i++] = new Task(string);
                }

            }
            string = scan.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon");

    }

}
