import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public enum Tasktype {
        DEADLINE,
        EVENT,
        TODO
    }
    public static void main(String[] args) {
        System.out.println("Hello from Gambit, how may I assist you today?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while(sc.hasNext()) {
            String word = sc.nextLine();
            if (word.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                break;
            } else if (word.equals("list")) {
                int size = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + list.get(i - 1).toString());
                }
            } else if (word.contains("done")) {
                String strArray[] = word.split(" ");
                int value =Integer.parseInt(strArray[1]);
                list.get(value - 1).markAsDone();
            } else {
                Tasktype newTask = null;
                if (word.contains("deadline")) {
                    newTask = Tasktype.DEADLINE;
                } else if (word.contains("todo")) {
                    newTask = Tasktype.TODO;
                } else if (word.contains("event")) {
                    newTask = Tasktype.EVENT;
                }
                System.out.println("Got it. I've added this task:");
                switch (newTask)
                {
                    case(DEADLINE) :
                        String[] input = word.split("/by");
                        input[0] = input[0].replaceAll("deadline","");
                        Deadline d = new Deadline(input[0],input[1]);
                        System.out.println(d.toString());
                        break;
                    case(EVENT) :
                        String[] info = word.split("/at");
                        input[0] = input[0].replaceAll("event","");
                        Event e = new Event(input[0],input[1]);
                        System.out.println(e.toString());
                        break;
                    case(TODO) :
                        word.replaceAll("todo","");
                        ToDo t = new ToDo(word);
                        System.out.println(t.toString());
                        break;
                }
                System.out.println("Now you have " + list.size() + "tasks in the list");
            }
        }
    }
}
