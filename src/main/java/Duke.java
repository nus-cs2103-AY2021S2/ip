import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("---------------------------------------\n");
        System.out.println("What's up! I'm Duke\nPlease feed be commands :)" );
        System.out.println("---------------------------------------" );

        Scanner scan = new Scanner(System.in);
        String input = " ";

//        Initialize Task container
        ArrayList<Task> tasks = new ArrayList<>();
        int taskNumber = 0;


        while(!input.equals("bye")) {
            input = scan.nextLine().trim();
            String command = input.split(" ")[0];

            switch (command) {
                case "bye":
                    System.out.println("\n---------------------------------------" );
                    System.out.println("Bye. Sayonara and goodbye!");
                    System.out.println("---------------------------------------" );
                    break;

                case "list":
//                    Numbers should change accordingly when deleted (For future Ref.)
                    System.out.println("\n---------------------------------------" );
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.valueOf(i+1) + "." + tasks.get(i).toString());
                    }
                    System.out.println("---------------------------------------" );
                    break;

                case "done":
//                    Possible Error: index provided is out of bounds (NullPointerException)
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(index).markDone();

                    System.out.println("\n---------------------------------------" );
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).name.trim());
                    System.out.println("---------------------------------------" );
                    break;

                case "todo":
                    try {
                        String name = getTodoName(input);
                        Todo todo = new Todo(name);
                        tasks.add(todo);

                        System.out.println("\n---------------------------------------" );
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        System.out.println("---------------------------------------" );
                    } catch(DukeException e) {
                        e.printError("Come On Fella! Your ToDo description cannot be empty!");
                    }
                    break;

                case "deadline":
                    try{
                        String name = getEventOrDeadlineName(input);
                        String by = getEventOrDeadlineAttribute(input);
                        Deadline deadline = new Deadline(name, by);
                        tasks.add(deadline);

                        System.out.println("\n---------------------------------------" );
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        System.out.println("---------------------------------------" );
                    } catch (DukeException e) {
                        e.printError("Hmm... You are either lacking a name or /by details!");
                    }
                    break;

                case "event":
                    try {
                        String name = getEventOrDeadlineName(input);
                        String at = getEventOrDeadlineAttribute(input);

                        Event event = new Event(name,at);
                        tasks.add(event);

                        System.out.println("\n---------------------------------------" );
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        System.out.println("---------------------------------------" );
                    } catch (DukeException e) {
                        e.printError("Hmm... You are either lacking a name or /at details!");
                    }
                    break;

                case "delete":
                    int i = Integer.parseInt(input.split(" ")[1]) - 1;

                    System.out.println("\n---------------------------------------" );
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(i).toString());
                    tasks.remove(i);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    System.out.println("---------------------------------------" );
                    break;

                default:
//                    Does not exactly throw an exception*
                    System.out.println("\n---------------------------------------" );
                    System.out.println("Hol'up, I don't know what that means :-(");
                    System.out.println("---------------------------------------" );
                    break;
            }
        }
    }

    public static String getTodoName(String input) throws DukeException {
        try {
            String name = input.split(" ",2)[1].trim();
            return name;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static String getEventOrDeadlineName(String input) throws DukeException {
        try {
            String name = input.split("/")[0].split(" ",2)[1].trim();
            return name;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static String getEventOrDeadlineAttribute(String byDate) throws DukeException {
        try {
            String atBy = byDate.split("/")[1].split(" ",2)[1].trim();
            return atBy;
        } catch (Exception e) {
            throw new DukeException();
        }
    }
}
