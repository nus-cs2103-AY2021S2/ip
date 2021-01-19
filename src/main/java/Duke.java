import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            String taskConfirmation = "Got it. I've added this task:\n";
            if (s.equals("list")) {
                int index = 1;
                for (Task task : list) {
                    System.out.println(index + "." + task);
                    index++;
                }
            } else if (arr[0].equals("done")) {
                int index = Integer.parseInt(arr[1]);
                list.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list.get(index - 1));
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (arr[0].equals("todo")) {
                ToDo t = new ToDo(s.substring(5));
                list.add(t);
                System.out.println(taskConfirmation + t
                        + "\nNow you have " + list.size()
                        + (list.size() < 2 ? " task " : " tasks ") + "in the list.");
            } else if (arr[0].equals("deadline")) {
                String[] arr2 = s.split(" /by ");
                Deadline d = new Deadline(arr2[0].substring(9), arr2[1]);
                list.add(d);
                System.out.println(taskConfirmation + d
                        + "\nNow you have " + list.size()
                        + (list.size() < 2 ? " task " : " tasks ") + "in the list.");
            } else if (arr[0].equals("event")) {
                String[] arr3 = s.split(" /at ");
                Event e = new Event(arr3[0].substring(6), arr3[1]);
                list.add(e);
                System.out.println(taskConfirmation + e
                        + "\nNow you have " + list.size()
                        + (list.size() < 2 ? " task " : " tasks ") + "in the list.");
            } else {
                Task newTask = new Task(s);
                list.add(newTask);
                System.out.println("added: " + s);
            }
        }
    }
}
