import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> lst = new ArrayList<>();

    public static void initiate(Scanner s) {
        Duke.Greet();
        String input = s.nextLine();

        while(!input.equals("bye")){
            String[] arr = input.split(" ");
            if (input.equals("list")) {
                Duke.list();
            } else if (arr[0].equals("done")) {
                Duke.markAsDone(Integer.parseInt(arr[1]));
            } else {
                Duke.add(input);
            }
            input = s.nextLine();
        }
        Duke.exit();
    }

    public static void add(String msg) {
        Task newTask = new Task(msg, false);
        lst.add(newTask);
        System.out.println("---------------------------------------");
        System.out.println("added: " + msg);
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
