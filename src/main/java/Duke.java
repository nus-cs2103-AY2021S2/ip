import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke myDuke = new Duke();
        myDuke.run();
    }

    private void run() {
        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");
        Scanner input = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();

        while (input.hasNextLine()) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                System.out.println("---------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------------------");
                break;
            } else if (s.equals("list")) {
                System.out.println("---------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                int len = myList.size();
                for (int i = 1; i < len + 1; i++) {
                    Task curTask = myList.get(i - 1);
                    System.out.println(i + ".[" + curTask.getStatusIcon() + "] " + curTask.getDescription());
                }
                System.out.println("---------------------------------------------");
            } else {
                if (s.substring(0, 4).equals("done")) {
                    int taskNo = Integer.valueOf(s.substring(5, s.length()));
                    if (taskNo <= myList.size()) {
                        Task curTask = myList.get(taskNo - 1);
                        curTask.markAsDone();
                        System.out.println("---------------------------------------------");
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("["+curTask.getStatusIcon()+"] "+curTask.getDescription());
                        System.out.println("---------------------------------------------");
                    } else {
                        Task newTask = new Task(s);
                        myList.add(newTask);
                        System.out.println("---------------------------------------------");
                        System.out.println("added: " + s);
                        System.out.println("---------------------------------------------");
                    }
                } else {
                    Task newTask = new Task(s);
                    myList.add(newTask);
                    System.out.println("---------------------------------------------");
                    System.out.println("added: " + s);
                    System.out.println("---------------------------------------------");
                }
            }
        }
    }
}