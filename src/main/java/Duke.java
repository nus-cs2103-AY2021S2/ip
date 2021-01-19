import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> lst = new ArrayList<>();

    public static void initiate(Scanner s) {
        Duke.Greet();

        while(s.hasNextLine()){
            String input = s.nextLine();
            String[] arr = input.split(" ", 2);
            String command = arr[0];
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                Duke.list();
            } else if (command.equals("done")) {
                String msg = arr[1];
                Duke.markAsDone(Integer.parseInt(msg));
            } else {
                String msg = arr[1];
                Duke.add(command, msg);
            }
        }
        Duke.exit();
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Duke.initiate(s);
    }

    public static void add(String typeOfTask, String msg) {
        Task newTask;
        String[] arr1;
        if (typeOfTask.equals("todo")) {
            newTask = new Todo(msg);
        } else if (typeOfTask.equals("deadline")) {
            arr1 = msg.split("/by ");
            newTask = new Deadline(arr1[0], arr1[1]);
        } else {
            arr1 = msg.split("/at ");
            newTask = new Event(arr1[0], arr1[1]);
        }
        lst.add(newTask);
        System.out.println("---------------------------------------");
        System.out.println("Got it. I 've added this task:\n" + newTask);
        Duke.status();
        System.out.println("---------------------------------------");
    }

    public static void markAsDone(int i) {
        lst.set(i - 1, lst.get(i - 1).setDone());
        System.out.println("---------------------------------------");
        System.out.println("Nice! I 've marked this task as done");
        System.out.println(lst.get(i - 1).toString());
        System.out.println("---------------------------------------");
    }

    public static void list() {
        System.out.println("---------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lst.size(); i++) {
           System.out.println((i + 1) + "." + lst.get(i).toString());
        }
        System.out.println("---------------------------------------");
    }

    public static void status() {
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    public static void Greet() {
        String instructions = "Hello! I'm Duke\nWhat can I do for you?";
        Duke.output(instructions);
    }

    public static void echo(String msg) {
        String instructions = msg;
        Duke.output(instructions);
    }

    public static void exit() {
        String instructions = "Bye. Hope to see you again soon!";
        Duke.output(instructions);
    }

    public static void output(String instructions) {
        System.out.println("---------------------------------------");
        System.out.println(instructions);
        System.out.println("---------------------------------------");
    }
}
