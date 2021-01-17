import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> col = new ArrayList<>(100);

        System.out.println("----------------------------------------------");
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        System.out.println("----------------------------------------------");

        String x = sc.nextLine();
        String[] command = x.split(" ");
        while(!x.equals("bye")) {
            if(x.equals("list")) {
                System.out.println("----------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < col.size(); i++) {
                    System.out.println((i+1) + ". " + col.get(i));
                }
                System.out.println("----------------------------------------------");
            }
            else if(command[0].equals("done")) {
                System.out.println("----------------------------------------------");
                int index = Integer.parseInt(command[1]);
                Task t = col.get(index - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
                System.out.println("----------------------------------------------");
            }
            else { //must be add task
                col.add(new Task(x));
                System.out.println("----------------------------------------------");
                System.out.println("added: " + x);
                System.out.println("----------------------------------------------");
            }
            x = sc.nextLine();
            command = x.split(" ");
        }
        System.out.println("----------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------------------");
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";