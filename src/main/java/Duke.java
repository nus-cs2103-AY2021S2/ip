import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("---------------------------------------\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        System.out.println("---------------------------------------" );

//        Initialize Task container of space 100
        Task[] tasks = new Task[100];
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
                for (int i = 0; i < taskNumber; i++) {
                    System.out.println(String.valueOf(i+1) + "." + tasks[i].toString());
                }
                System.out.println("---------------------------------------" );
                    break;

                case "done":
//                    Possible Error: index provided is out of bounds (NullPointerException)
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markDone();

                System.out.println("\n---------------------------------------" );
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("[" + tasks[index].getStatusIcon() + "] " + tasks[index].name);
                System.out.println("---------------------------------------" );
                    break;

                case "todo":
                    try {
                        String name = getTodoName(input);
                        Todo todo = new Todo(name);
                tasks[taskNumber] = todo;
                taskNumber++;

                System.out.println("\n---------------------------------------" );
                System.out.println("Got it. I've added this task: ");
                System.out.println(todo.toString());
                System.out.println("Now you have " + taskNumber + " tasks in the list");
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
                tasks[taskNumber] = deadline;
                taskNumber++;

                System.out.println("\n---------------------------------------" );
                System.out.println("Got it. I've added this task: ");
                System.out.println(deadline.toString());
                System.out.println("Now you have " + taskNumber + " tasks in the list");
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
                tasks[taskNumber] = event;
                taskNumber++;

                System.out.println("\n---------------------------------------" );
                System.out.println("Got it. I've added this task: ");
                System.out.println(event.toString());
                System.out.println("Now you have " + taskNumber + " tasks in the list");
                System.out.println("---------------------------------------" );
                    } catch (DukeException e) {
                        e.printError("Hmm... You are either lacking a name or /at details!");
                    }
                    break;


            } else {
//            Add to list
                tasks[taskNumber] = new Task(input);
                taskNumber++;

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
