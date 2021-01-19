import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            if (s.equals("list")) {
                int index = 1;
                for (Task task : list) {
                    System.out.println(index + "." + task);
                    index++;
                }
            } else if (arr[0].equals("done")) {
                int index = Integer.parseInt(arr[1]);
                Task doneTask = list.get(index - 1).markAsDone();
                list.set(index - 1, doneTask);
                System.out.println("Nice! I've marked this task as done:\n" + doneTask);
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                Task newTask = new Task(s);
                list.add(newTask);
                System.out.println("added: " + s);
            }
        }
    }
}
