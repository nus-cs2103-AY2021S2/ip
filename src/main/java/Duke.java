import java.util.*;

public class Duke {
    static String input = " ";
    static List<Task> list = new ArrayList<>();
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greet = "Hello! I'm Duke \n What can I do for you?";
        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while (!(input.equals("bye")) && !(input.equals("Bye"))) {
            if(input.contains("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.size(); i++) {
                    Task current = list.get(i);
                    if (current.completed()) {
                        System.out.println(i+1 + ". " + list.get(i));
                    } else {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                }
            } else if (input.contains("done")){
                 System.out.println(Duke.done());
            } else {
                System.out.println(Duke.add());
            }
            input = sc.nextLine();
            input = input.stripLeading();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    static String done() {
        String value = input.split(" ")[1];
        int val = Integer.parseInt(value);
        list.get(val - 1).isCompleted();
        return "Nice! I've marked this task as done:\n " + list.get(val - 1);
    }

    static String addString(Task t) {
        return "Got it. I've added this task: \n  " + t.toString() + "\nNow you have " + list.size() + " tasks in the list.";
    }

    static String add() {
        if (input.contains("todo")) {
            String task = input.replaceFirst("todo", "");
            Todo t1 = new Todo(task);
            list.add(t1);
            return addString(t1);
        } else if(input.contains("deadline")) {
            String deadline = input.replaceFirst("deadline ", "");
            String[] deadlineArr = deadline.split("/");
            Deadline current = new Deadline(deadlineArr[0], deadlineArr[1].replaceFirst("by ", ""));
            list.add(current);
            return addString(current);
        } else if (input.contains("event")) {
            String eventDetails = input.replaceFirst("event ", "");
            String[] eventDeats = eventDetails.split("/");
            Event current = new Event(eventDeats[0], eventDeats[1].replaceFirst("at ", ""));
            list.add(current);
            return addString(current);
        } else {
            return "HALP";
        }

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

    public String completedBox() {
        if (this.completed) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    @Override
    public String toString() {
        return this.completedBox() + this.task;
    }
}

class Todo extends Task {
    Todo(String t) {
        super(t);
    }

    @Override
    public String toString() {
        return "[T]" + this.completedBox() + this.task;
    }

}

class Deadline extends Task {
    String date;

    Deadline(String t, String due) {
        super(t);
        this.date = due;
    }

    @Override
    public String toString() {
        return "[D]" + this.completedBox() + this.task + "(by: " + this.date + ")";
    }
}

class Event extends Task {
    String date;

    Event(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + this.completedBox() + this.task + "(at: " + this.date + ")";
    }
}
