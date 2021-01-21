import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static final String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "How can I assist you?\n" +
                "____________________________________________________________");
        while (true) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println("Bye. Till next time!");
                System.out.println(horizontalLine);
                break;
            }
            else if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int num = 1;
                for (Task task: list) {
                    System.out.println(String.format("%d. %s", num, task));
                    num++;
                }
                System.out.println(horizontalLine);
            }
            else if (text.split(" ")[0].equals("done")) {
                int num = Integer.parseInt(text.split(" ")[1]);
                list.get(num-1).done();
                System.out.println(horizontalLine);
            }
            else if (text.split(" ")[0].equals("todo")) {
                Task todo = new Todo(text.substring(text.indexOf(" ")+1));
                addAndPrintTask(todo);
            }
            else if (text.split(" ")[0].equals("deadline")) {
                Task deadline = new Deadline(text.substring(text.indexOf(" ")+1, text.indexOf("/")-1), text.substring(text.indexOf("/")+4));
                addAndPrintTask(deadline);
            }
            else if (text.split(" ")[0].equals("event")) {
                Task event = new Event(text.substring(text.indexOf(" ")+1, text.indexOf("/")-1), text.substring(text.indexOf("/")+4));
                addAndPrintTask(event);
            }
            else {}
        }
        sc.close();
    }

    public static void addAndPrintTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }
}
