import java.util.*;
import java.util.Scanner;


enum Call {
    DELETE, TODO, EVENT, DEADLINE, LIST, DONE
}

public class Duke {
    static String input = " ";
    static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
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
        cleanInput();
        Call call;
        while (!input.contains("bye")) {

            if(input.contains("list")) {
                call = Call.LIST;
            } else if (input.contains("done")){
                call = Call.DONE;
            } else if (input.contains("todo")) {
                call = Call.TODO;
            } else if (input.contains("event")) {
                call = Call.EVENT;
            } else if (input.contains("deadline")) {
                call = Call.DEADLINE;
            } else if (input.contains("delete")) {
                call = Call.DELETE;
            } else {
                if(sc.hasNextLine()) {
                    input = sc.nextLine();
                } else {
                    throw new Exception("Please enter a valid command!");
                }
                continue;
            }
            switch (call) {
                case LIST -> commandList();
                case DONE -> commandDone();
                case TODO -> commandTodo();
                case DEADLINE -> commandDeadline();
                case EVENT -> commandEvent();
                case DELETE -> commandDelete();
            }

            input = sc.nextLine();
            cleanInput();
        }

        System.out.println("input was: " + input);
        System.out.println("input is valid: " + validCommand());
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    static void commandDone() {
        try {
            String value = input.split(" ")[1];
            System.out.println(value);
            int val = Integer.parseInt(value);
            list.get(val - 1).isCompleted();
            System.out.println("Nice! I've marked this task as done:\n " + list.get(val - 1));
        } catch (Exception e) {
            throw e;
        }

    }

    static String addString(Task t) {
        return "Got it. I've added this task: \n  " + t.toString() + "\nNow you have " + list.size() + " tasks in the list.";
    }

    static void cleanInput() {
        input = input.replaceAll("\n", "");
        input = input.toLowerCase();
    }

    static void commandTodo() throws Exception{
        System.out.println(input);
        String task = input.replaceFirst("todo", "");
        System.out.println("task was: " + task);
        task = task.stripTrailing();
        if (task.isEmpty()) {
            throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo t1 = new Todo(task);
        list.add(t1);
        System.out.println(addString(t1));
    }

    static void commandDeadline() {
        String deadline = input.replaceFirst("deadline", "");
        deadline = deadline.stripLeading();
        String[] deadlineArr = deadline.split("/");
        Deadline current = new Deadline(deadlineArr[0], deadlineArr[1].replaceFirst("by ", ""));
        list.add(current);
        System.out.println(addString(current));
    }

    static void commandEvent() {
        String eventDetails = input.replaceFirst("event", "");
        eventDetails = eventDetails.stripLeading();
        String[] eventDeats = eventDetails.split("/");
        Event current = new Event(eventDeats[0], eventDeats[1].replaceFirst("at ", ""));
        list.add(current);
        System.out.println(addString(current));
    }

    static void commandDelete() {
        String value = input.replaceFirst("delete", "");
        value = value.strip();
        int val = Integer.parseInt(value);
        Task delete = list.get(val - 1);
        list.remove(val - 1);
        System.out.println("Noted. I've removed this task: \n " + delete + "\nNow you have " + list.size() + " tasks in the list.");
    }

    static void commandList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            Task current = list.get(i);
            System.out.println(i+1 + ". " + current);
        }
    }

    static boolean validCommand() {
        return input.contains("delete") || input.contains("event") || input.contains("done") ||
                input.contains("todo") || input.contains("deadline") || input.contains("list");
    }
}



