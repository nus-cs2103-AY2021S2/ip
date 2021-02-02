import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * main method to run the program
     *
     * @param args command line arguments taken in
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"+ "What can I do for you?");
        ArrayList<Task> items = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.contains("done")) {
                System.out.println("Nice! I've marked this task as done: ");

                int taskNum = Integer.parseInt(input.substring(5));
                String name = items.get(taskNum - 1).getDescription();

                items.remove(taskNum - 1);
                Task markDone = new Task(name, true);
                items.add(taskNum - 1, markDone);

                System.out.println(markDone);

            } else if (input.equals("list")) {
                int n = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task item : items) {
                    System.out.println( n + ". " + item);
                    n++;
                }
            } else {
                items.add(new Task(input));
            }
        }
    }

    /**
     * prints Goodbye message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
