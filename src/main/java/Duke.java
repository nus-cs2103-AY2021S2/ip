import main.java.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {


    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        List<Task> taskList = new ArrayList<>();


        String indent = "         ";
        String horizSep = indent + "________________________________________________";

        String greeting = indent + " Hello! I'm Duke\n" + indent + " What can I do for you?\n";
        String farewell = indent + " Bye. Hope to see you again soon!\n";
        Scanner sc = new Scanner(System.in);



        System.out.println(horizSep + "\n" + greeting + horizSep + "\n");


        while (sc.hasNextLine()) {

            String next = sc.nextLine();
            String[] params = next.split(" ", 2);
            // System.out.println(params);
            try {

                if (next.equals("bye")) {

                    System.out.println(horizSep + "\n" + farewell + horizSep + "\n");
                    sc.close();
                    return;
                } else if (next.equals("list")) {
                    ListIterator<Task> taskIter = taskList.listIterator();

                    System.out.println(horizSep);
                    System.out.println(indent + " Here are the tasks in your list:");
                    while (taskIter.hasNext()) {

                        Task curr = taskIter.next();

                        System.out.println(indent + " " + String.valueOf(taskIter.nextIndex()) + "." + curr);
                    }
                    System.out.println(horizSep + "\n");

                } else if (params[0].equals("done")) {

                    Integer index = Integer.parseInt(params[1]) - 1;

                    if (index >= taskList.size()) {
                        throw new IllegalArgumentException("No such task in the list");
                    }

                    taskList.set(index, taskList.get(index).markAsDone());
                    System.out.println(horizSep + "\n" + indent + " Nice! I've marked this task as done:");
                    Task curr = taskList.get(index);
                    System.out.println(indent + "   " + curr);
                    System.out.println(horizSep + "\n");

                } else if (params[0].equals("todo")) {
                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
                    }

                    Todo newTask = new Todo(params[1]);
                    taskList.add(newTask);
                    System.out.println(horizSep + "\n" +  indent + " Got it. I've added this task: ");
                    System.out.println(indent + "   " + newTask);
                    System.out.println(indent + " Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(horizSep + "\n");


                } else if (params[0].equals("deadline")) {

                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty");
                    }

                    String[] deadlineParams = params[1].split(" /by ");

                    if (deadlineParams.length == 1) {
                        throw new DukeException("deadline not given for this Deadline!");
                    }



                    Deadline newTask = new Deadline(deadlineParams[0], deadlineParams[1]);
                    taskList.add(newTask);
                    System.out.println(horizSep + "\n" +  indent + " Got it. I've added this task: ");
                    System.out.println(indent + "   " + newTask);
                    System.out.println(indent + " Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(horizSep + "\n");

                } else if (params[0].equals("event")) {
                    System.out.println("params: " + Arrays.toString(params));
                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
                    }

                    String[] eventParams = params[1].split(" /at ");
                    System.out.println("eventParams: " + Arrays.toString(eventParams));
                    if (eventParams.length == 1) {
                        throw new DukeException("no date and time given for this Event!");
                    }

                    String[] timeParams = eventParams[1].split(" ");

                    if (timeParams.length == 1) {
                        throw new DukeException("time of Event was not specified!");
                    }

                    Pattern pt = Pattern.compile("\\d-\\dp?a?m");
                    Matcher mt = pt.matcher(timeParams[1]);

                    if (!mt.find()) {
                        throw new DukeException("Invalid time range for the event!");
                    }

                    Event newTask = new Event(eventParams[0], eventParams[1]);
                    taskList.add(newTask);
                    System.out.println(horizSep + "\n" +  indent + " Got it. I've added this task: ");
                    System.out.println(indent + "   " + newTask);
                    System.out.println(indent + " Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(horizSep + "\n");

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                }


            } catch(Exception exp) {
                System.out.println(horizSep);
                System.out.println(indent + " " + exp);
                System.out.println(horizSep + "\n");
            }



        }

    }
}
