import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String input;
        ArrayList<Task> arrayList = new ArrayList<Task>();
        int listNumber = 1;

        System.out.println("--------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();


            if (input.equals("bye")) {
                System.out.println("--------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");

            } else if (input.equals("list")) {
                System.out.println("--------------------------");
                System.out.println("Here are the tasks in your list:");
                for (Task s : arrayList) {
                    System.out.print(s.index + ". " + s.printTask());
                    System.out.print("\n");
                }
                System.out.println("--------------------------");

            } else if (input.contains("done")) {
                int x = Integer.parseInt(input.substring(5));
                System.out.println("--------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(arrayList.get(x-1).getStatusIcon()+ " " + arrayList.get(x-1).description);
                arrayList.set(x-1, arrayList.get(x-1).markAsDone());
                System.out.println("--------------------------");

            } else {
                Task task = new Task(input);
                task.index = listNumber;
                listNumber++;
                arrayList.add(task);
                System.out.println("added: "+ input);
                System.out.println("--------------------------");
            }

        }
    }
}


