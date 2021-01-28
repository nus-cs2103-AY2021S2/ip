import java.util.ArrayList;
import java.util.List;

public class Ui {

    public static List<Task> list = new ArrayList<>();
    public static void greeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addTasks(String input) {
        if(input.equals("todo")){
            throw new DukeEmptyCommandException("OOPS!!! The description of a todo cannot be empty.");
        } else if (input.equals("deadline")){
            throw new DukeEmptyCommandException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (input.equals("event")){
            throw new DukeEmptyCommandException("OOPS!!! The description of an event cannot be empty.");
        } else {
            Task t = new Task(input);
            list.add(t);
            System.out.println("Got it. I've added this task: ");
            System.out.println(t.toString());

        }
    }
}