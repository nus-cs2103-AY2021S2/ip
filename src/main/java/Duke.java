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

                Task type = items.get(taskNum - 1);
                items.remove(taskNum - 1);
                if (type instanceof Todo){
                    Todo markDone = new Todo(name, true);
                    items.add(taskNum - 1, markDone);
                    System.out.println(markDone);
                } else if (type instanceof Deadline){
                    Deadline markDone = new Deadline(name, true);
                    items.add(taskNum - 1, markDone);
                    System.out.println(markDone);
                } else {
                    Event markDone = new Event(name, true);
                    items.add(taskNum - 1, markDone);
                    System.out.println(markDone);
                }

            } else if (input.equals("list")) {
                int n = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task item : items) {
                    System.out.println(n + ". " + item);
                    n++;
                }
            } else if (input.contains("deadline")) {
                items.add(new Deadline(input.substring(8)));
            } else if (input.contains("todo")) {
                items.add(new Todo(input.substring(4)));
            } else if (input.contains("event")) {
                items.add(new Event(input.substring(5)));
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
