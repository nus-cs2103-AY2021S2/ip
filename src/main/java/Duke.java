import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String greet = "Hello! I'm Duke \n What can I do for you?";
        System.out.println(greet);
        String input = sc.nextLine();
        List<Task> list = new ArrayList<>();
        while (!(input.equals("bye")) && !(input.equals("Bye"))) {
            if(input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.size(); i++) {
                    Task current = list.get(i);
                    if (current.completed()) {
                        System.out.println(i+1 + ". [X] " + list.get(i));
                    } else {
                        System.out.println(i+1 + ". [ ] " + list.get(i));
                    }
                }
            } else if (input.contains("done")){
                String value = input.split(" ")[1];
                int val = Integer.parseInt(value);
                list.get(val - 1).isCompleted();
                System.out.println("Nice! I've marked this task as done:\n [X] " + list.get(val - 1));
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}

class Task {
    String task;
    Boolean completed;

    Task(String t) {
        this.task = t;
        this.completed = false;
    }

    public void isCompleted() {
        this.completed = true;
    }

    public boolean completed() {
        return this.completed;
    }

    @Override
    public String toString() {
        return this.task;
    }
}
