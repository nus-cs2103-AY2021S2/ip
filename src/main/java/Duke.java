import main.java.*;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private static final String indent = "         ";
    private static final String horizSep = indent + "________________________________________________";

    private static void logMessage(DukeCommand command, String message, Integer numTasks, Task relevantTask) {
        if (command == DukeCommand.BYE || command == DukeCommand.WELCOME) {
            System.out.println(horizSep + "\n" + message + horizSep + "\n");
        } else if (command == DukeCommand.ADD_TASK) {
            System.out.println(horizSep + "\n" +  indent + " Got it. I've added this task: ");
            System.out.println(indent + "   " + relevantTask);
            System.out.println(indent + " Now you have " + numTasks + " tasks in the list.");
            System.out.println(horizSep + "\n");
        } else if (command == DukeCommand.DELETE_TASK) {
            System.out.println(horizSep + "\n" +  indent + " Noted. I've removed this task ");
            System.out.println(indent + "   " + relevantTask);
            System.out.println(indent + " Now you have " + numTasks + " tasks in the list.");
            System.out.println(horizSep + "\n");
        } else if (command == DukeCommand.DONE) {
            System.out.println(horizSep + "\n" + indent + " Nice! I've marked this task as done:");
            System.out.println(indent + "   " + relevantTask);
            System.out.println(horizSep + "\n");
        }
    }

    public static void main(String[] args) {

        List<Task> taskList = new ArrayList<>();


        String greeting = indent + " Hello! I'm Duke\n" + indent + " What can I do for you?\n";
        String farewell = indent + " Bye. Hope to see you again soon!\n";
        Scanner sc = new Scanner(System.in);

        logMessage(DukeCommand.WELCOME, greeting, 0, null);

        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            String[] params = next.split(" ", 2);

            try {
                if (next.equals("bye")) {
                    logMessage(DukeCommand.BYE, farewell, 0, null);
                    sc.close();
                    return;

                } else if (params[0].equals("delete")) {
                    try {
                        Integer index = Integer.parseInt(params[1]) - 1;

                        if (index >= taskList.size()) {
                            throw new DukeException("No such task in the list");
                        }

                        Task removedTask = taskList.remove(Integer.parseInt(params[1]) - 1);
                        logMessage(DukeCommand.DELETE_TASK, "", taskList.size(), removedTask);

                    } catch (Exception err) {
                        throw new DukeException(err.getMessage());
                    }

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
                        throw new DukeException("No such task in the list");
                    }

                    taskList.set(index, taskList.get(index).markAsDone());

                    Task curr = taskList.get(index);
                    logMessage(DukeCommand.DONE, "", 0, curr);


                } else if (params[0].equals("todo")) {
                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
                    }

                    Todo newTask = new Todo(params[1]);
                    taskList.add(newTask);
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);


                } else if (params[0].equals("deadline")) {

                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty");
                    }

                    String[] deadlineParams = params[1].split(" /by ");

                    if (deadlineParams.length == 1) {
                        throw new DukeException("deadline not given for this Deadline!");
                    }

                    Pattern pt = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                    Matcher mt = pt.matcher(deadlineParams[1]);

                    if (!mt.find()) {
                        throw new DukeException("OOPS!!! Please enter '/by YYYY-MM-DD' after description");
                    }


                    Deadline newTask = new Deadline(deadlineParams[0], LocalDate.parse(deadlineParams[1]));
                    taskList.add(newTask);
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);

                } else if (params[0].equals("event")) {

                    if (params.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
                    }

                    String[] eventParams = params[1].split(" /at ");

                    if (eventParams.length == 1) {
                        throw new DukeException("no date and time given for this Event!");
                    }

                    String[] timeParams = eventParams[1].split(" ");

                    if (timeParams.length == 1) {
                        throw new DukeException("time of Event was not specified!");
                    }

                    Pattern datePt = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                    Matcher dateMt = datePt.matcher(timeParams[0]); // timeParams[0] refers to the date

                    if (!dateMt.find()) {
                        throw new DukeException("OOPS!!! Please enter '/by YYYY-MM-DD {time range}' after description");
                    }


                    Pattern timePt = Pattern.compile("\\d{1,2}-\\d{1,2}p?a?m");
                    Matcher timeMt = timePt.matcher(timeParams[1]); // timeParams[1] refers to the time

                    if (!timeMt.find()) {
                        throw new DukeException("OOPS!!! Please enter a valid time range in this format \"{start}-{end}\"" +
                                " and include am/pm after");
                    }

                    Event newTask = new Event(eventParams[0], LocalDate.parse(timeParams[0]), timeParams[1]);
                    taskList.add(newTask);
                    logMessage(DukeCommand.ADD_TASK, "", taskList.size(), newTask);

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                }


            } catch(DukeException exp) {
                System.out.println(horizSep);
                System.out.println(indent + " " + exp);
                System.out.println(horizSep + "\n");
            }


        }

    }
}
