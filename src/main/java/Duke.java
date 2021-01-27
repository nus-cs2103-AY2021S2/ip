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
            String[] words = text.split(" ");
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
            else if (words[0].equals("done")) {
                int num = Integer.parseInt(words[1]);
                list.get(num-1).done();
                System.out.println(horizontalLine);
            }
            else if (words[0].equals("delete")) {
                int num = Integer.parseInt(words[1]);
                deleteAndPrintTask(num);
            }
            else if (words[0].equals("todo")) {
                try {
                    emptyDesc(words);
                    Task todo = new Todo(text.substring(text.indexOf(" ") + 1));
                    addAndPrintTask(todo);
                }
                catch (DukeException e){
                    System.err.println("OOPS!!! The description of a todo cannot be empty.\n" + horizontalLine);
                }
            }
            else if (words[0].equals("deadline")) {
                try {
                    emptyDesc(words);
                    Task deadline = new Deadline(text.substring(text.indexOf(" ") + 1, text.indexOf("/") - 1), text.substring(text.indexOf("/") + 4));
                    addAndPrintTask(deadline);
                }
                catch (DukeException e){
                    System.err.println("OOPS!!! The description of a deadline cannot be empty.\n" + horizontalLine);
                }
            }
            else if (words[0].equals("event")) {
                try {
                    emptyDesc(words);
                    Task event = new Event(text.substring(text.indexOf(" ") + 1, text.indexOf("/") - 1), text.substring(text.indexOf("/") + 4));
                    addAndPrintTask(event);
                }
                catch (DukeException e){
                    System.err.println("OOPS!!! The description of an event cannot be empty.\n" + horizontalLine);
                }
            }
            else {
                try {
                    throw new DukeException();
                }
                catch (DukeException e){
                    System.err.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + horizontalLine);
                }
            }
        }
        sc.close();
    }

    private static void addAndPrintTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void emptyDesc(String[] words) throws DukeException {
        if (words.length == 1) {
            throw new DukeException();
        }
    }

    private static void deleteAndPrintTask(int num) {
        Task removedTask = list.remove(num-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }
}
