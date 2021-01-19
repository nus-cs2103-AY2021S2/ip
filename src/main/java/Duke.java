import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> lst = new ArrayList<>();

    public static void initiate(Scanner s) {
        Duke.Greet();
        String input = s.nextLine();
        String[] arr = input.split(" ", 2);
        String command = arr[0];

        while(!command.equals("bye")){
            if (command.equals("list")) {
                Duke.list();
            } else if (command.equals("done")) {
                String msg = arr[1];
                Duke.markAsDone(Integer.parseInt(msg));
            } else {
                String msg = arr[1];
                Duke.add(command, msg);
            }
            input = s.nextLine();
            arr = input.split(" ", 2);
            command = arr[0];
        }
        Duke.exit();
    }


    public static void add(String typeOfTask, String msg) {
//        System.out.println(msg);
        Task newTask;
        String[] arr1;
        if (typeOfTask.equals("todo")) {
            newTask = new Todo(msg);
        } else if (typeOfTask.equals("deadline")) {
            arr1 = msg.split("/by ");
//            System.out.println(arr1[2]);
//            System.out.println(arr1[0]);
//            System.out.println(arr1[1]);
            newTask = new Deadline(arr1[0], arr1[1]);
        } else {
            arr1 = msg.split("/at ");
//            System.out.println(arr1[0]);
//            System.out.println(arr1[1]);
            newTask = new Event(arr1[0], arr1[1]);
        }
        lst.add(newTask);
        System.out.println("---------------------------------------");
        System.out.println("Got it. I 've added this task: \n" + newTask);
        Duke.status();
        System.out.println("---------------------------------------\n");
    }

    public static void markAsDone(int i) {
        lst.set(i - 1, lst.get(i - 1).setDone());
        System.out.println("---------------------------------------");
        System.out.println("Nice! I 've marked this task as done");
        System.out.println(lst.get(i - 1).toString());
        System.out.println("---------------------------------------\n");
    }

    public static void list() {
        System.out.println("---------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lst.size(); i++) {
           System.out.println((i + 1) + "." + lst.get(i).toString());
        }
        System.out.println("---------------------------------------\n");
    }

    public static void status() {
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public static void Greet() {
        System.out.println("---------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("---------------------------------------\n");
    }

    public static void echo(String msg) {
        System.out.println("---------------------------------------");
        System.out.println(msg);
        System.out.println("---------------------------------------\n");
    }

    public static void exit() {
        System.out.println("---------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------\n");
    }
}
